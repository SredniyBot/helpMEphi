<#import "parts/common.ftl" as c>
<#import "parts/block.ftl" as b>
<@c.page>
    <div class="bg">
        <@b.flag>
            Количество пользователей = ${users?size}
        </@b.flag>
        <@b.container>
            <#list users as user>
                    <@b.flag>
                        <div>User name: ${user.getUsername()?if_exists}  </div>
                        <div>User id: ${user.getId()}</div>
                        <div>User role: ${user.getRole()}</div>
                        <div><a href="/user/${user.getId()}/edit">edit</a></div>
                    </@b.flag>
            </#list>
        </@b.container>
    </div>
</@c.page>