<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
  <div class="form-container">
<#if prevUser??>
    <form action="/registration" method="post" class="login-form">

      <input type="hidden" name="_csrf" value="${_csrf.token}"/>
      <label class="log">Registration</label>

      <div><label>
          <input value="${prevUser.getUsername()}" class="input" type="text" placeholder="username" name="username"/>
        </label></div>
      <#if usernameError??><div class="error">${usernameError}</div></#if>

      <div><label>
          <input value="${prevUser.getEmail()}" class="input" type="email" placeholder="your-email@mail.ru" name="email"/>
        </label></div>
      <#if emailError??><div class="error">${emailError}</div></#if>

      <div><label>
          <input value="${prevUser.getPassword()}" class="input" type="password" placeholder="password" name="password"/>
        </label></div>
      <#if passwordError??><div class="error">${passwordError}</div></#if>


      <div><label>
          <input value="${prevUser.getPassword2()}"class="input" type="password" placeholder="password" name="password2"/>
        </label></div>
      <#if password2Error??><div class="error">${password2Error}</div></#if>
        <input class="custom-btn btn-7" type="submit" value="Зарегистрироватся"/>
    </form>

  <#else >

    <form action="/registration" method="post" class="login-form">

      <input type="hidden" name="_csrf" value="${_csrf.token}"/>
      <label class="log">Registration</label>

      <div><label>
          <input class="input" type="text" placeholder="username" name="username"/>
        </label></div>

      <div><label>
          <input class="input" type="email" placeholder="your-email@mail.ru" name="email"/>
        </label></div>

      <div><label>
          <input class="input" type="password" placeholder="password" name="password"/>
        </label></div>
      <div><label>
          <input class="input" type="password" placeholder="password" name="password2"/>
        </label></div>

      <input class="custom-btn btn-7" type="submit" value="Зарегистрироватся"/>
    </form>
    </div>
</#if>
</@c.page>