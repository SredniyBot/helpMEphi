<#import "parts/common.ftl" as c >
<#import "parts/block.ftl" as b>

<#if prevDoc??>
    <#assign
       htmlStr ="${prevDoc.getComment()}"
    >
    <#else >
    <#assign
    htmlStr =""
    >
</#if>


<@c.page >
    <@b.flag>
        <form class="max_w form-add" action="/docs/create" method="post" enctype="multipart/form-data" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <#if prevDoc??>
                <div>
                    <input class="input" placeholder="document name" type="text" name="name" value="${prevDoc.getName()}">
                    <#if nameError??><div class="error">${nameError}</div></#if>
                </div>
                <div>
                    <div>
                        <textarea id="summernote" name="comment"></textarea>
                    </div>
                    <#if commentError??><div class="error">${commentError}</div></#if>
                </div>
                <#else >
                    <div>
                        <input class="input" placeholder="document name" type="text" name="name">
                    </div>
                    <div class="max_w">
                    <div>
                        <textarea id="summernote" name="comment"></textarea>
                    </div>
                </div>
            </#if>




            <section class="files-uploader">
                <div class="files-uploader__list"></div>
                <div class="files-uploader__actions">
                    <input type="button" class="button is-light" data-files="add" value="Add file"><i class="fa fa-plus" aria-hidden="true"></i>
                </div>
            </section>


    <#--            <div class="files-uploader__item">-->
    <#--                <label for="file-uploader-" class="button is-primary">-->
    <#--                    <i class="fa fa-upload" aria-hidden="true"></i> Pick a file…-->
    <#--                </label>-->
    <#--                <input id="file-uploader-1" class="is-sr-only" type="file" name="file1"/>-->
    <#--                <input type="text" name="comment1">-->
    <#--                <div class="files-uploader__file-name">No file selected</div>-->
    <#--                <button data-files="remove" class="button is-danger">'<i class="fa fa-trash"></i> Remove'</button>-->
    <#--            </div>-->

            <div><input class="custom-btn btn-7" type="submit" value="Add doc" ></div>
        </form>


    </@b.flag>


    <script type="text/javascript">
        let htmlStr='${htmlStr}'
        console.log(htmlStr)
        $('#summernote').summernote({
            toolbar: [
                // [groupName, [list of button]]
                ['style', ['italic', 'underline', 'clear']],
                ['font', ['strikethrough', 'superscript', 'subscript']],
                ['fontsize', ['fontsize']],
                ['color', ['color']],
                ['para', [ 'ol', 'paragraph']],
            ],
            disableDragAndDrop: true,
            shortcuts: false,
            codeviewFilter: false,
            codeviewIframeFilter: true
        });
         $('#summernote').summernote('pasteHTML', htmlStr);

    </script>
    <script type="text/javascript">

        const filesList = document.querySelector('.files-uploader__list')
        const addButton = document.querySelector('[data-files="add"]')
        let count=[5,4,3,2,1]


        addButton.addEventListener('click', createNewRow)

        function random () {
            return Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
        }

        function createNewRow () {
            if(count.length!==0) {
                let curVar=count[count.length-1]
                count.pop()
                const fileItem = document.createElement('div')
                fileItem.setAttribute('class', 'files-uploader__item')

                const label = document.createElement('label')
                label.setAttribute('class', 'button is-primary')
                label.setAttribute('for', 'file-uploader-' + curVar)
                label.innerHTML = '<i class="fa fa-upload" aria-hidden="true"></i> Pick a file…'

                const input = document.createElement('input')
                input.setAttribute('id', 'file-uploader-' + curVar)
                input.setAttribute('class', 'is-sr-only')
                input.setAttribute('type', 'file')
                input.setAttribute('name', 'file'+curVar)
                input.addEventListener('change', event => {
                    const file = event.target.files[0]
                    let maxSize=1000000 //500KB
                    if(file.size>maxSize){
                        fileName.innerHTML="Размер файла слишком большой"
                        input.value=""
                    }
                    else fileName.innerHTML = file.name
                    // fileName.innerHTML = file.name + ' • ' + file.size

                })


                const comment = document.createElement('input')
                comment.setAttribute('type', 'text')
                comment.setAttribute('name', 'comment'+curVar)


                const fileName = document.createElement('div')
                fileName.setAttribute('class', 'files-uploader__file-name')
                fileName.innerHTML = 'No file selected'

                const remove = document.createElement('button')
                remove.setAttribute('class', 'button is-danger')
                remove.setAttribute('data-files', 'remove')
                remove.innerHTML = '<i class="fa fa-trash"></i> Remove'
                remove.addEventListener('click', () => {
                    count.push(curVar)
                    filesList.removeChild(fileItem)
                    addButton.setAttribute('type', 'button')
                })

                filesList.appendChild(fileItem)
                fileItem.appendChild(label)
                fileItem.appendChild(fileName)
                fileItem.appendChild(input)
                fileItem.appendChild(comment)
                fileItem.appendChild(remove)

                if (count.length===0)
                    addButton.setAttribute('type','hidden')
            }
        }



        //    const fileReader = new FileReader();
        //     fileReader.readAsDataURL(files);
        //     fileReader.addEventListener("load", function () {
        //         imgPreview.style.display = "block";
        //         imgPreview.innerHTML = '<img src="' + this.result + '" />';
        //     });



    </script>
</@c.page>















<#--<script type="text/javascript">-->
<#--    const filesList = document.querySelector('.files-uploader__list')-->
<#--    const addButton = document.querySelector('[data-files="add"]')-->
<#--    let count=[5,4,3,2,1]-->


<#--    addButton.addEventListener('click', createNewRow)-->

<#--    function random () {-->
<#--        return Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)-->
<#--    }-->

<#--    function createNewRow () {-->
<#--        if(count.length!=0) {-->
<#--            let curVar=count[count.length-1]-->
<#--            count.pop()-->
<#--            const fileItem = document.createElement('div')-->
<#--            fileItem.setAttribute('class', 'files-uploader__item')-->

<#--            const label = document.createElement('label')-->
<#--            label.setAttribute('class', 'button is-primary')-->
<#--            label.setAttribute('for', 'file-uploader-' + curVar)-->
<#--            label.innerHTML = '<i class="fa fa-upload" aria-hidden="true"></i> Pick a file…'-->

<#--            const input = document.createElement('input')-->
<#--            input.setAttribute('id', 'file-uploader-' + curVar)-->
<#--            input.setAttribute('class', 'is-sr-only')-->
<#--            input.setAttribute('type', 'file')-->
<#--            input.setAttribute('name', 'file'+curVar)-->
<#--            input.addEventListener('change', event => {-->
<#--                const file = event.target.files[0]-->
<#--                let maxSize=1000000 //500KB-->
<#--                if(file.size>maxSize){-->
<#--                    fileName.innerHTML="Размер файла слишком большой"-->
<#--                    input.value=""-->
<#--                }-->
<#--                else fileName.innerHTML = file.name-->
<#--                // fileName.innerHTML = file.name + ' • ' + file.size-->

<#--            })-->


<#--            const comment = document.createElement('input')-->
<#--            comment.setAttribute('type', 'text')-->
<#--            comment.setAttribute('name', 'comment'+curVar)-->


<#--            const fileName = document.createElement('div')-->
<#--            fileName.setAttribute('class', 'files-uploader__file-name')-->
<#--            fileName.innerHTML = 'No file selected'-->

<#--            const remove = document.createElement('button')-->
<#--            remove.setAttribute('class', 'button is-danger')-->
<#--            remove.setAttribute('data-files', 'remove')-->
<#--            remove.innerHTML = '<i class="fa fa-trash"></i> Remove'-->
<#--            remove.addEventListener('click', () => {-->
<#--                count.push(curVar)-->
<#--                filesList.removeChild(fileItem)-->
<#--                addButton.setAttribute('type', 'button')-->
<#--            })-->

<#--            filesList.appendChild(fileItem)-->
<#--            fileItem.appendChild(label)-->
<#--            fileItem.appendChild(fileName)-->
<#--            fileItem.appendChild(input)-->
<#--            fileItem.appendChild(comment)-->
<#--            fileItem.appendChild(remove)-->

<#--            if (count.length==0)-->
<#--                addButton.setAttribute('type','hidden')-->
<#--        }-->
<#--    }-->



<#--    //    const fileReader = new FileReader();-->
<#--    //     fileReader.readAsDataURL(files);-->
<#--    //     fileReader.addEventListener("load", function () {-->
<#--    //         imgPreview.style.display = "block";-->
<#--    //         imgPreview.innerHTML = '<img src="' + this.result + '" />';-->
<#--    //     });-->



<#--</script>&lt;#&ndash;&ndash;&gt;-->