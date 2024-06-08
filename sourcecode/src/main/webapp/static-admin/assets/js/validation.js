var isImage = false;
let isChooseImage = false;
let countIndex = 1;
function validation(inputsToValidate, submitBtn) {
// for each item in array input check when blur and when enter in input again clear show error
    inputsToValidate.forEach(function (item) {
        if (item.type != 'ckeditor') {
            item.element.addEventListener('blur', function () {
                checkInput(item);
            })
            item.element.addEventListener('input', function () {
                const parentNode = item.element.parentElement;
                parentNode.classList.remove("valid");
                parentNode.classList.remove("invalid");
                parentNode.querySelector('.message_error').innerHTML = "";
            })
        }
    })

// check error of element if no match with regex call function show error and return false
// else return true and call function show success
    function checkInput(item) {
        let messageError = '';
        if (item.type === "text") {
            if(item.element.value == "" && item.isEmpty == true) {
                messageError = "";
            } else
            if (item.element.value === "" && item.isEmpty == false) {
                messageError = "Please enter information for this field";
            } else
            if (!item.element.value.match(item.regex)) {
                messageError = item.message;
            } else {
                messageError = "";
            }
        } else if (item.type === "select") {
            if (item.element.value === "" && item.isEmpty == false) {
                messageError = item.message;
            } else {
                messageError = "";
            }
        } else if (item.type === "date") {
            if (item.element.value === "" && item.isEmpty == false) {
                messageError = item.message;
            } else {
                messageError = "";
            }
        } else if (item.type === "image") {
            if (item.isEmpty == true && countIndex == 1 && isChooseImage == false) {
                isImage = true;
                countIndex++;
            }
            if ((item.isEmpty == true && !isChooseImage) || (item.isEmpty == false && isChooseImage)) {
                if (isImage) {
                    messageError = "";
                } else {
                    messageError = item.message;
                }
            }
            if (item.isEmpty == false) {
                if (isImage) {
                    messageError = "";
                } else {
                    messageError = item.message;
                }
            }
        } else if (item.type == "ckeditor") {
            const ck = item.regex;
            let editorData = CKEDITOR.instances[ck].getData().trim();
            if (editorData === "") {
                messageError = item.message;
            } else {
                messageError = "";
            }
        } else if (item.type === "radio") {

        } else if (item.type === "checkbox") {

        } else if (item.type === "select") {
            if (item.element.value === "") {
                messageError = item.message;
            } else {
                messageError = "";
            }
        }
        if (messageError.trim().length != 0) {
            showErrorMessage(item.element, messageError);
            return false;
        } else {
            handleSuccess(item.element);
            return true;
        }
    }

//  function show message on each input when have a error
    function showErrorMessage(element, message) {
        const parentNode = element.parentElement;
        parentNode.classList.add("invalid");
        parentNode.classList.remove("valid");
        parentNode.querySelector('.message_error').style.display = 'block';
        parentNode.querySelector('.message_error').innerHTML = message;
    }
//  function handle message when input is valid
    function handleSuccess(element) {
        const parentNode = element.parentElement;
        parentNode.classList.remove("invalid");
        parentNode.classList.add("valid");
        parentNode.querySelector('.message_error').style.display = 'none';
        parentNode.querySelector('.message_error').innerHTML = "";
    }
//  handle submit event check if all input pass. send data else not send
    submitBtn.addEventListener('click', function (e) {
        const price = document.querySelector("#price"),
                priceSale = document.querySelector("#priceSale");
        let isValid = true;
        inputsToValidate.forEach(function (item) {
            if (!checkInput(item)) {
                isValid = false;
            }
        })
        if (price != null && priceSale != null) {
            if (parseInt(price.value, 10) < parseInt(priceSale.value)) {
                showErrorMessage(priceSale, "Price sale must be less than price");
                isValid = false;
            }
        }
        if (!isValid) {
            e.preventDefault();
        }
        if (!isValid) {
            e.preventDefault();
        }
    })
}

let objectURLs = [];
let isClearCache = false;
let allowedExtension = ['image/jpeg', 'image/jpg', 'image/png', 'image/gif', 'image/bmp'];
const previewImage = (event, elementPreview) => {
    countIndex = 2;
    isChooseImage = true;
    let boxShowImgPreview = document.querySelector(elementPreview);
    if (!isClearCache) {
        isChooseImage = false;
        isImage = true;
        objectURLs.forEach(url => URL.revokeObjectURL(url));
        objectURLs = [];
        isClearCache = true;
    }
    const lengthOfImage = event.target.files.length;
    let htmlRenderPreview = "";
    for (let i = 0; i < lengthOfImage; i++) {
        isClearCache = false;
        const srcImg = event.target.files[i];
        if (allowedExtension.indexOf(srcImg.type) <= -1) {
            isImage = false;
            return;
        }
        const src = URL.createObjectURL(srcImg);
        objectURLs.push(src);
        htmlRenderPreview += `<img class="preview-image" src="${src}" alt="image-preview"/>`;
    }
    boxShowImgPreview.innerHTML = htmlRenderPreview;
};