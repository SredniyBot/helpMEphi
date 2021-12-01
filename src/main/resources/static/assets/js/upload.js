const filesList = document.querySelector('.files-uploader__list')
const addButton = document.querySelector('[data-files="add"]')

addButton.addEventListener('click', createNewRow)

function random () {
    return Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)
}

function createNewRow () {
    const key = random()
    const fileItem = document.createElement('div')
    fileItem.setAttribute('class', 'files-uploader__item')

    const label = document.createElement('label')
    label.setAttribute('class', 'button is-primary')
    label.setAttribute('for', 'file-uploader-' + key)
    label.innerHTML = '<i class="fa fa-upload" aria-hidden="true"></i> Pick a file…'

    const input = document.createElement('input')
    input.setAttribute('id', 'file-uploader-' + key)
    input.setAttribute('class', 'is-sr-only')
    input.setAttribute('type', 'file')
    input.addEventListener('change', event => {
        const file = event.target.files[0]
        fileName.innerHTML = file.name + ' • ' + file.size
    })

    const fileName = document.createElement('div')
    fileName.setAttribute('class', 'files-uploader__file-name')
    fileName.innerHTML = 'No file selected'

    const remove = document.createElement('button')
    remove.setAttribute('class', 'button is-danger')
    remove.setAttribute('data-files', 'remove')
    remove.innerHTML = '<i class="fa fa-trash"></i> Remove'
    remove.addEventListener('click', () => {
        filesList.removeChild(fileItem)
    })

    filesList.appendChild(fileItem)
    fileItem.appendChild(label)
    fileItem.appendChild(fileName)
    fileItem.appendChild(input)
    fileItem.appendChild(remove)
}