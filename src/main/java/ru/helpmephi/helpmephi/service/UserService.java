package ru.helpmephi.helpmephi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.helpmephi.helpmephi.entity.Role;
import ru.helpmephi.helpmephi.entity.User;
import ru.helpmephi.helpmephi.repos.UserRepo;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, MailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepo.findByUsername(username);
        if (user==null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    public Map<String,String> addUserOrGetErrors(User user){
        Map<String,String> errors = new HashMap<>();

        User userFromDb = userRepo.findByUsername(user.getUsername());
        User userFromDbEm = userRepo.findByEmail(user.getEmail());
        if (userFromDb!=null){
            errors.put("usernameError", "Пользователь с таким ником уже существует");
        }
        if(userFromDbEm!=null){
            errors.put("emailError", "Пользователь с таким email уже существует");
        }
        if(errors.isEmpty()) {
            user.setActive(false);
            user.setRoles(Collections.singleton(Role.STRANGER));
            user.setActivationCode(UUID.randomUUID().toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            sendWelcomeMessage(user);
        }
        return errors;
    }

    public boolean activateUser(String code) {
        User user =userRepo.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void delete(User user) {
        if(userRepo.existsById(user.getId())){
            userRepo.delete(user);
        }
    }

    public void save(User user, String name, String email, String role) {
        user.setUsername(name);
        user.setEmail(email);
        user.setRoles(Role.valueOf(role).getRoles());
        userRepo.save(user);
    }

    public void updateProfile(User user, String password, int course) {
        user.setPassword(password);
        user.setCourse(course);
        userRepo.save(user);
    }

    public Map<String,String> restorePswdOrGetErrors(String email) {
        Map<String,String> errors = new HashMap<>();
        User user = userRepo.findByEmail(email);
        if(user==null){
            errors.put("emailError","Пользователя с таким email не существует");
        }else{
            Integer newPswd= getNewPswd();
            user.setPassword(passwordEncoder.encode(String.valueOf(newPswd)));
            userRepo.save(user);
            sendGenPswdMessage(user,newPswd);
        }
        return errors;
    }

    private Integer getNewPswd(){
        return new Random().nextInt(8999999)+10000000;
    }

    private void sendWelcomeMessage(User user) {
        if(!(user.getEmail()==null||user.getEmail().equals(""))){
            String message =String.format(
                    "Привет, дорогой %s!\n" +
                            "Рад видеть тебя на helpMEphi!\n" +
                            "Надеюсь, что ты найдешь здесь то, что так долго искал!\n" +
                            "Для подтверждения твоего аккаунта перейди пожалуйста по ссылке:\n" +
                            "http://localhost:8080/activate/" +
                            "%s",
                    user.getUsername(),
                    user.getActivationCode());
            mailSender.send(user.getEmail(),"Код активации", message);
        }
    }

    private void sendGenPswdMessage(User user,Integer newPswd) {
        if(!(user.getEmail()==null||user.getEmail().equals(""))){
            String message =String.format(
                    "Дорогой %s!\n" +
                            "Жаль, что вы забыли ваш пароль\n" +
                            "Но само собой, это совсем не проблема\n" +
                            "Вот вам новый: %d\n" +
                            "Не забудьте изменить его на более удобный в вашем личном кабинете!",
                    user.getUsername(),
                    newPswd);
            mailSender.send(user.getEmail(),"Код активации", message);
        }
    }

}
