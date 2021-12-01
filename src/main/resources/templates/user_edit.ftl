<#import "parts/common.ftl" as c>
<#import "parts/block.ftl" as b>
<@c.page>
    <@b.flag>
        <div>User ID: ${user.getId()} </div>
        <form action="/user" method="post">
            <div> User name: <input type="text" name="username" value="${user.getUsername()}"></div>
            <div> User email: <input type="email" name="email" value="${user.getEmail()?if_exists}"></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" value="${user.getId()}" name="id">
            <div>
                User Role:
            <#list roles as role>
                <div>
                    <input type="radio" name="new_role" id="${role}" value="${role}" ${user.getRole()?matches(role)?string("checked","")} >${role}
                </div>
            </#list>
            </div>
            <input type="submit" class="btn" value="Save"/>
        </form>
        <form action="/user/del" method="post">
            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" value="${user.getId()}" name="id">
                <input type="submit" class="btn" value="DELETE"/>
            </div>
        </form>
    </@b.flag>
</@c.page>