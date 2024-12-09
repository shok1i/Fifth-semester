const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
function toggleCollection(action, userID, artID) {
    const isAdding = action === 'POST';
    fetch(`/api/collection`, {
        method: action,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-TOKEN': csrfToken
        },
        body: new URLSearchParams({
            'user': userID,
            'art': artID
        }).toString()
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            const button = document.getElementById('btn_collection');
            button.textContent = isAdding ? "Удалить штуку с полки" : "Добавить штуку на полку";
            button.setAttribute('onclick', `toggleCollection('${isAdding ? 'DELETE' : 'POST'}', ${userID}, ${artID})`);
        })
        .catch(error => console.error('Error:', error));
}
function toggleLike(action, userID, artID) {
    const isAdding = action === 'POST';
    fetch(`/api/like`, {
        method: action,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'X-CSRF-TOKEN': csrfToken
        },
        body: new URLSearchParams({
            'user': userID,
            'art': artID
        }).toString()
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            const card = document.getElementById('CARD' + artID)

            const like = card.querySelector('#likesDiv');
            like.classList.toggle('liked', isAdding);
            like.setAttribute('onclick', `toggleLike('${isAdding ? 'DELETE' : 'POST'}', ${userID}, ${artID})`);

            const likesCount = card.querySelector('#likesCount');
            const value = parseInt(likesCount.textContent);
            likesCount.textContent = isAdding ? value + 1 : value - 1;
        })
        .catch(error => console.error('Error:', error));
}


// Код для формирования заказа
let orderItems = [];
let totalPrice = parseFloat('0');

function addSticker(artID) {
    const artElement = document.getElementById('SID' + artID);
    if (!artElement) {
        console.error("Card with ID SID" + artID + " not found");
        return;
    }
    const artImageID = artElement.querySelector('img').src;

    let existingItem = orderItems.find(item => item.artID === artID);
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        orderItems.push({
            artID: artID,
            imageID: artImageID,
            width: 50,
            height: 50,
            quantity: 100,
            price: 500
        });
    }
    updateOrderList();
    callProcessItem(artID)
}

function updateOrderList() {
    const orderList = document.getElementById('order-list');
    orderList.innerHTML = '';

    orderItems.forEach(item => {
        const orderItemDiv = document.createElement('div');
        orderItemDiv.classList.add('mb-3');
        orderItemDiv.innerHTML = `
            <div class="sticker-card form-group mb-3" id="cart-${item.artID}">
                <div class="card">
                    <div class="card-body">
                        <div class="row justify-content-between g-2 mb-2">
                            <div class="col-12 col-sm-3 col-md-4">
                                <img class="card-img img-fluid" src="${item.imageID}" style="max-height: 300px; object-fit: contain; alt="...">
                            </div>
                            <div class="col-12 col-sm-9 col-md-8">
                                <div class="col-8 col-sm-5 col-md-6">
                                    <div class="container mb-2" style="white-space: nowrap;">
                                        <div class="row justify-content-center justify-content-md-start form-h-div g-1">
                                            <label for="id_form-${item.artID}-height">Высота: (мм)</label>
                                            <div class="col-auto">
                                                <button type="button" class="btn btn-sm dark-blue-lutra-btn-outline h-100" onclick="changeField(this, 'h', '-', '${item.artID}')">-</button>
                                            </div>
                                            <div class="col col-sm-6 col-md">
                                                <input type="number" name="form-${item.artID}-height" value="${item.height}" min="10" max="500" onchange="callProcessItem(${item.artID})" class="text-center form-control" step="any" id="id_form-${item.artID}-h">
                                            </div>
                                            <div class="col-auto">
                                                <button type="button" class="btn btn-sm dark-blue-lutra-btn-outline h-100" onclick="changeField(this, 'h', '+', '${item.artID}')">+</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container mb-2" style="white-space: nowrap;">
                                        <div class="row justify-content-center justify-content-md-start g-1 form-w-div">
                                            <label for="id_form-${item.artID}-width">Ширина: (мм)</label>
                                            <div class="col-auto">
                                                <button type="button" class="btn btn-sm dark-blue-lutra-btn-outline h-100 width-plus" onclick="changeField(this, 'w', '-', '${item.artID}')">-</button>
                                            </div>
                                            <div class="col col-sm-6 col-md">
                                                <input type="number" name="form-${item.artID}-width" value="${item.width}" min="10" max="500" onchange="callProcessItem(${item.artID})" class="text-center form-control" step="any" id="id_form-${item.artID}-w">
                                            </div>
                                            <div class="col-auto">
                                                <button type="button" class="btn btn-sm dark-blue-lutra-btn-outline h-100 width-minus" onclick="changeField(this, 'w', '+', '${item.artID}')">+</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container " style="white-space: nowrap;">
                                        <div class="row justify-content-center justify-content-md-start g-1 form-quantity-div">
                                            <label class="" for="id_form-${item.artID}-quantity">Количество:</label>
                                            <div class="col-auto">
                                                <button type="button" class="btn btn-sm dark-blue-lutra-btn-outline h-100" onclick="changeField(this, 'quantity', '-', '${item.artID}')">-</button>
                                            </div>
                                            <div class="col col-sm-6 col-md">
                                                <input type="number" name="form-${item.artID}-quantity" value="${item.quantity}" min="1" class="text-center form-control" onchange="callProcessItem(${item.artID})" max="100000" step="any" id="id_form-${item.artID}-quantity">
                                            </div>
                                            <div class="col-auto">
                                                <button type="button" class="btn btn-sm dark-blue-lutra-btn-outline h-100" onclick="changeField(this, 'quantity', '+', '${item.artID}')">+</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row justify-content-between">
                            <div class="col align-self-baseline">
                                <button type="button" class="btn btn-sm btn-outline-danger" onclick="removeItem(${item.artID})">Удалить</button>
                            </div>
                            <div class="col align-self-center text-end"><span class="" id="item_price">${item.price}</span></div>
                        </div>
                    </div>
                </div>
            </div>
        `;
        orderList.appendChild(orderItemDiv);
    });
}

function removeItem(artID) {
    orderItems = orderItems.filter(item => item.artID !== artID);
    updateOrderList();
}

function changeField(button, type, operation, formID) {
    const inputField = document.querySelector(`#id_form-${formID}-${type}`);
    let value = parseFloat(inputField.value);

    if (operation === '+') {
        value += 1;
    } else if (operation === '-') {
        value -= 1;
    }

    const item = orderItems.find(item => item.artID === parseInt(formID));

    if (type === 'h' && value >= 10 && value <= 500) {
        inputField.value = value;
        item.height = value;

    } else if (type === 'w' && value >= 10 && value <= 500) {
        inputField.value = value;
        item.width = value;
    } else if (type === 'quantity' && value >= 1 && value <= 100000) {
        inputField.value = value;
        item.quantity = value;
    }

    callProcessItem(formID, formID);
}

function callProcessItem(ID) {
    const form = document.getElementById(`cart-${ID}`);

    const item = orderItems.find(item => item.artID === parseInt(ID));


    const height = item.height;
    const width = item.width;
    const quantity = item.quantity;

    const costPerUnit = 20;
    const baseCost = (height * width * costPerUnit) / 10000;
    let totalCost = baseCost * quantity;

    item.price = totalCost.toFixed(2);

    const priceElement = form.querySelector(`#item_price`);
    priceElement.innerHTML = `Стоимость: ${item.price} ₽`;

    totalPrice = parseFloat('0');
    orderItems.forEach(temp => totalPrice += parseFloat(temp.price));

    const total = document.getElementById(`itemsPrice`);
    total.textContent = totalPrice;
}


function makeOrder(userID) {
    // Собрать все artID из orderItems
    const artIDs = orderItems.map(item => item.artID);

    // Отправить данные на сервер
    fetch(`/api/make-order`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // Используем JSON
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify({
            user: userID,
            price: totalPrice,
            arts: artIDs // Передаем массив artID
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            window.location.href = "/order/list";
        })
        .then(data => {
            console.log('Order created successfully:', data);
        })
        .catch(error => console.error('Error:', error));
}
