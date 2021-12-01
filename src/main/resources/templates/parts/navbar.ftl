<#import "security.ftl" as s>
<#macro header selected>
    <section class="navigation">
        <div class="nav-container">
            <div class="brand">
                <span class="logoFlag">
                    <div class="flag_left"></div>
                    <h1 class="logo">
                        <a href="/">help<span>ME</span>phi</a>
                    </h1>
                 </span>
            </div>
            <nav>
                <div class="nav-mobile">
                    <a id="nav-toggle" href="#"><span></span></a>
                </div>
                <ul class="nav-list">

                    <#if s.isComrade>
                    <li <#if selected?matches(1)>class="selected"</#if> >
                        <a href="/user">Пользователи</a></li>
                    </#if>


                    <li <#if selected?matches(3)>class="selected"</#if> >
                        <a href="#">Учебные материалы</a>
                        <ul class="nav-dropdown">
                            <li <#if selected?matches(4)>class="selected"</#if>>
                                <a href="/docs">Документы</a></li>
                            <li <#if selected?matches(4)>class="selected"</#if>>
                                <a href="/docs/create">Добавить документ</a></li>
                        </ul>
                    </li>
                    <#if s.user??>
                        <li <#if selected?matches(8)>class="selected"</#if>>
                            <a href="/user/profile">Личный кабинет</a></li>
                        <#else>
                        <li <#if selected?matches(8)>class="selected"</#if>>
                            <a href="/login">Войти</a></li>
                    </#if>
                </ul>
            </nav>
        </div>
    </section>
</#macro>

<#macro footer>
    <footer>
        <div>
            <h3>+79663589026</h3>
        </div>
        <div>
            <h3>pia012.work@mail.ru</h3>
        </div>
    </footer>
</#macro>











