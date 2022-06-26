<#import "parts/common.ftl" as c>
<#import "parts/block.ftl" as b>
<@c.page>
    <div class="searchFormDivClass">
        <form class="searchContainer" method="post">
            <div class="searchDiv">
                <input type="text" class="search">
                <span></span>
            </div>
            <div class="searchParams">
                <div><label><input type="radio" name="doc_type"> </label></div>
                <div><label><input type="radio" name="doc_type"> </label></div>
                <div><label><input type="radio" name="doc_type"> </label></div>
            </div>
        </form>
    </div>

        <@b.container>
            <#list documents as document>
                <@b.flag>
                    <div>Название документа: ${document.getName()}</div>
                    <div>id документа: ${document.getId()}</div>
                    <div>Комментарий: ${document.getComment()}</div>
                    <#if document.getAuthor()??>
                    <div>Author: ${document.getAuthor().getUsername()}</div>
                        <#else >
                    <div>Author:DELETED</div>
                    </#if>
                    <div>
                        <#list document.getContent() as content>
                        <#if content.isImage()>
                            <@b.img_fancybox "/doc/${content.getFileName()}" "" ""/>
                            <#else>
                                Скачать файл:<a href="/doc/${content.getFileName()}"><img src="/doc/${content.getImagePath()}"></a>
                        </#if>
                        </#list>
                    </div>
                    <div><a href="/docs/${document.getId()}/edit">edit</a></div>

                </@b.flag>
                <#else >
                No documents
            </#list>
        </@b.container>
</@c.page>