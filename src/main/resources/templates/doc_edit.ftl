<#import "parts/common.ftl" as c>
<#import "parts/block.ftl" as b>
<@c.page>
    <@b.flag>
        <div>Document id: ${document.getId()}</div>
        <#if document.getAuthor()??>
                <div>Document author: ${document.getAuthor().getUsername()}</div>
            <#else >
                <div>Document author: DELETED</div>
        </#if>
        <form action="/docs/edit" method="post" enctype="multipart/form-data">
            <div><input type="text" name="name" value="${document.getName()}"></div>
            <div><input type="text" name="comment" value="${document.getComment()}"></div>
            <div><input type="file" name="file"/></div>
            <div><#list document.getContent() as content>
                    <#if content.isImage()>
                        <div>
                            <@b.img_fancybox "/doc/${content.getFileName()}" "1" "${content.getComment()}"/>
                            <div>
                                ${content.getComment()}
                            </div>
                        </div>
                    <#else>
                        Скачать файл:<a href="/doc/${content.getFileName()}"><img src="/doc/${content.getImagePath()}"></a>
                    </#if>
                </#list>
                </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${document.getId()}"/>
            <div>
            <input type="submit" class="btn" value="Save"/></div>
        </form>

        <form action="/docs/del" method="post">
            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" value="${document.getId()}" name="id">
                <input type="submit" class="btn" value="DELETE"/>
            </div>
        </form>
    </@b.flag>

</@c.page>