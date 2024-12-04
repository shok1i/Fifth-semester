<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuroi</title>
</head>
<body>
<h1>Kuroi</h1>
<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Выйти"/>
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Панель администратора</a>
    </#if>
<#else>
    <a href="/login">Войти</a>
</#if>

<h4>Коффе в ресторане</h4>
<form action="/" method="get">
    Поиск по названию: <input type="text" name="title"/>
    <input type="submit" value="Поиск"/>
</form>

<#list products as product>
    <div>
        <img src="/images/${product.previewImageId}">
        <p><b>${product.title}</b> ${product.price} | <a href="/product/${product.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>

<#if user.email??>
    <h3>Создать новый товар</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Название товара: <input type="text" name="title"/>
        <br>
        Описание товара: <input type="text" name="description"/>
        <br>
        Цена товара: <input type="text" name="price"/>
        <br>
        Фото первое: <input type="file" name="file1"/>
        <br>
        Фото второе: <input type="file" name="file2"/>
        <br>
        Фото третье: <input type="file" name="file3"/>
        <br>

        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Добавить товар"/>
    </form>
</#if>
</body>
</html>