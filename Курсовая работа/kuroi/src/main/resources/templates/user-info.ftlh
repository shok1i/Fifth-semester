<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuroi</title>
</head>
<body>
<h1>Kuroi</h1>
<h3>Подробная инофрмация</h3>
<b>email: </b>${user.email}
</br>
<b>Дата регистрации: </b>${user.createdAt}
</br>

<h4>Товары пользователя ${user.name}</h4>

<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} | <a href="/product/${product.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>

</body>
</html>