<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuroi</title>
</head>
<body>
<h1>Kuroi</h1>
<h4>Подробная информация о товаре</h4>

<#list images as img>
    <img src="/images/${img.id}" height="256px"/><br><br>
</#list>

<b>Название товара: </b>${product.title}
<br>
<b>Описание товара: </b>${product.description}
<br>
<b>Цена товара: </b>${product.price}
<br>
<b>Автор: </b><a href="/user/${product.user.id}">${product.user.email}</a>
<br>

</body>
</html>