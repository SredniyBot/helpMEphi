<#import "parts/common.ftl" as c>
<@c.page>
    <div class="form-container">
        <#if prevEmail??>
            <form action="/restore" method="post" class="login-form">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <label class="log">Restore password</label>


                <div><label>
                        <input value="${prevEmail}" class="input" type="email" placeholder="your-email@mail.ru" name="email"/>
                    </label></div>
                <#if emailError??><div class="error">${emailError}</div></#if>

                <input class="custom-btn btn-7" type="submit" value="Восстановить пароль"/>
            </form>
        <#else >
            <form action="/restore" method="post" class="login-form">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <label class="log">Restore password</label>

                <div><label>
                        <input class="input" type="email" placeholder="your-email@mail.ru" name="email"/>
                    </label></div>

                <input class="custom-btn btn-7" type="submit" value="Восстановить пароль"/>
            </form>

        </#if>
    </div>

</@c.page>