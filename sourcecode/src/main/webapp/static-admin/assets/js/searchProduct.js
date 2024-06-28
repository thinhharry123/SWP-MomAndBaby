
document.getElementById('searchProductInput').addEventListener('input', function () {
    const query = document.getElementById('searchProductInput').value;
    fetch("/SWP391-MomAndBaby/staff/search-product?query=" + query)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                const searchResults = document.getElementById('searchResults');
                searchResults.innerHTML = '';
                data.forEach(product => {
                    const div = document.createElement('div');
                    div.className = 'product-item';
                    div.innerHTML = `
<span>${product.name} - ${product.newPrice}</span>
            <img src="${product.mainImg}" wiwith="30px" height="30px"/>
<button type="button" onclick="addProduct(${product.ID}, '${product.name}', ${product.newPrice})">Add</button>
`;
                    searchResults.appendChild(div);
                });
            });
});

function addProduct(id, name, price) {
    const existingProduct = document.getElementById(`product_${id}`);
    if (existingProduct) {
        const quantityInput = existingProduct.querySelector(`input[name="quantity_${id}"]`);
        quantityInput.value = parseInt(quantityInput.value) + 1;
    } else {
        const selectedProducts = document.getElementById('selectedProducts');
        const div = document.createElement('div');
        div.className = 'product-item';
        div.id = `product_${id}`;
        div.innerHTML = `
                    <input type="hidden" name="productID" value="${id}">
                    <span>${name} - ${price}</span><======>
                    <input type="number" name="quantity_${id}" placeholder="Quantity" min="1" value="1"><======>
                    <button type="button" onclick="removeProduct(${id})">Remove</button>
                `;
        selectedProducts.appendChild(div);
    }
}

function removeProduct(id) {
    const productDiv = document.getElementById(`product_${id}`);
    if (productDiv) {
        productDiv.remove();
    }
}

document.getElementById('customerSelect').addEventListener('change', function () {
    const selectedOption = this.options[this.selectedIndex];
    const email = selectedOption.getAttribute('data-email');
    const phone = selectedOption.getAttribute('data-phone');
    const fullname = selectedOption.getAttribute('data-fullname');

    document.getElementById('email').value = email;
    document.getElementById('phone').value = phone;
    document.getElementById('fullname').value = fullname;
});