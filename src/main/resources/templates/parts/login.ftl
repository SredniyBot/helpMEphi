<#macro login>
<div class="form-container">
    <form action="/login" method="post" class="login-form">

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <label class="log">Login</label>
        <hr style="margin: 15px 5px">

        <div><label>
                <input class="input" type="text" placeholder="username" name="username"/>
        </label></div>

        <div><label>
                <input class="input" type="password" placeholder="password" name="password"/>
        </label></div>

        <label class="reg"> <a href="/registration">Забыл(а) пароль?</a></label>

        <div class="submit"><input class="custom-btn btn-7" type="submit" value="Войти"/></div>

        <hr style="margin: 15px 5px">
        <label class="reg"><a href="/registration">Зарегистрируйся!</a></label>
    </form>
</div>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" class="custom-btn btn-7" value="Выйти"/>
    </form>
</#macro>

<#-- /registration-->
<#macro registr>
    <div class="form-container">
        <form action="/registration" method="post" class="login-form">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <label class="log">Login</label>

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

            <div class="submit"><input class="custom-btn btn-7" type="submit" value="Зарегистрироватся"/></div>
        </form>
    </div>
</#macro>