<#import "parts/common.ftl" as c>
<#import "parts/block.ftl" as b>
<@c.page>
    <@b.flag>
<#--        <div>${name}</div>-->
<#--        <div>${comment}</div>-->
<#--        <div>${listOfFiles}</div>-->
<#--        <div><a href="#">download</a></div>&lt;#&ndash;or buy&ndash;&gt;-->

<#--            data-fancybox="gallery" data-caption="Optional caption" -->
<#--            <img data-fancybox="gallery" data-caption="Optional caption" src="/img/mephi/ava.png" alt="Изображение потеряно :(" >-->
<#--            <img data-fancybox="gallery" data-caption="Optional caption" src="/img/mephi/question.png" alt="Изображение потеряно :(">-->
<#--            <img data-fancybox="gallery" data-caption="Optional caption" src="/img/mephi/folder.png" alt="Изображение потеряно :(">-->
<#--            <img data-fancybox="gallery" data-caption="Optional caption" src="/img/mephi/pdf.png" alt="Изображение потеряно :(">-->
<#--            <img data-fancybox="gallery" data-caption="Optional caption" src="/img/mephi/photo.png" alt="Изображение потеряно :(">-->
<#--            <img data-fancybox="gallery" data-caption="Optional caption" src="/img/mephi/doc.png" alt="Изображение потеряно :(">-->


<#--        <a href="/img/mephi/question.png" data-fancybox="gallery" data-caption="Optional caption">Image</a>-->
<#--        <a href="/img/mephi/folder.png" data-fancybox="gallery" data-caption="Optional caption">Image</a>-->
<#--        <a href="/img/mephi/pdf.png" data-fancybox="gallery" data-caption="Optional caption">Image</a>-->
<#--        <a href="/img/mephi/photo.png" data-fancybox="gallery" data-caption="Optional caption">Image</a>-->
<#--        <a href="/img/mephi/doc.png" data-fancybox="gallery" data-caption="Optional caption">Image</a>-->
        <div >
            <@b.img_fancybox "/doc/mephi/unknown.png" "1" ""/>
<#--            <@b.img_fancybox "/doc/mephi/folder.svg" "1" ""/>-->
<#--            <@b.img_fancybox "/doc/mephi/pdf.svg" "1" "no"/>-->
<#--            <@b.img_fancybox "/doc/mephi/photo.svg" "2" "no"/>-->
<#--            <@b.img_fancybox "/doc/mephi/doc.svg" "2" "no"/>-->
        </div>
    </@b.flag>

</@c.page>