<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
            user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
            name=user.getUsername()
            isComrade=user.getRole().isComrade()
            isPeacemaker=user.getRole().isPeacemaker()
            isHero=user.getRole().isHero()
            isAngel=user.getRole().isAngel()
    >
    <#else>
    <#assign
        name="Stanger"
        isComrade=false
        isPeacemaker=false
        isHero=false
        isAngel=false
    >
</#if>