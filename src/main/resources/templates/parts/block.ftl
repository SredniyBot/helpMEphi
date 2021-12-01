<#macro flag>
    <div class="flag_block">
        <div class="cover"></div>
        <div class="text">
            <#nested>
        </div>
        <div class="flag"></div>
    </div>
</#macro>

<#macro container>
    <div class="doc-container">
        <#nested >
    </div>
</#macro>

<#macro img_fancybox href group description>
    <a href="${href}" data-fancybox="${group}" data-caption="${description}">
        <img src="${href}" alt="Изображение потеряно :(" />
    </a>
</#macro>

<#macro fancybox href group description>
    <a href="${href}" data-fancybox="${group}" data-caption="${description}" data>
        <#nested>
    </a>
</#macro>

<#macro pdf href>
    <embed src="${href}" width="500px" height="375px" type="application/pdf">
</#macro>
