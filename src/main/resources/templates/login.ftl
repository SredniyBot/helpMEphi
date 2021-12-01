<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="form-container">
        <form action="/login" method="post" class="login-form">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <label class="log">Login</label>
            <label class="log">${message!}</label>

            <hr style="margin: 15px 5px">

            <div><label>
                    <input class="input" type="text" placeholder="username" name="username"/>
                </label></div>

            <div><label>
                    <input class="input" type="password" placeholder="password" name="password"/>
                </label></div>

            <#if Session??&&Session.SPRING_SECURITY_LAST_EXCEPTION??><div class="error">
                ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}</div></#if>

            <label class="reg"> <a href="/restore">Забыл(а) пароль?</a></label>

            <input class="custom-btn btn-7" type="submit" value="Войти"/>

            <hr style="margin: 15px 5px">
            <label class="reg"><a href="/registration">Зарегистрируйся!</a></label>
        </form>
    </div>
</@c.page>
