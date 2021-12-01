package ru.helpmephi.helpmephi.entity;

import org.springframework.security.core.GrantedAuthority;
import java.util.HashSet;
import java.util.Set;

public enum Role implements GrantedAuthority {
    STRANGER,
    COMRADE,
    PEACEMAKER,
    HERO,
    ANGEL;

    @Override
    public String getAuthority() {
        return name();
    }

    public static Role getMaxRole(Set<Role> roles){
        if(roles.contains(ANGEL)) return ANGEL;
        if(roles.contains(HERO)) return HERO;
        if(roles.contains(PEACEMAKER)) return PEACEMAKER;
        if(roles.contains(COMRADE)) return COMRADE;
        return STRANGER;
    }

    public Set<Role> getRoles(){
        HashSet<Role> set = new HashSet<>();
        set.add(STRANGER);
        if(this.equals(STRANGER)) return set;
        set.add(COMRADE);
        if(this.equals(COMRADE)) return set;
        set.add(PEACEMAKER);
        if(this.equals(PEACEMAKER)) return set;
        set.add(HERO);
        if(this.equals(HERO)) return set;
        set.add(ANGEL);
        return set;
    }

    public boolean isComrade(){
        return getRoles().contains(COMRADE);
    }
    public boolean isPeacemaker(){
        return getRoles().contains(PEACEMAKER);
    }
    public boolean isHero(){
        return getRoles().contains(HERO);
    }
    public boolean isAngel(){
        return getRoles().contains(ANGEL);
    }

}
