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
<form action="/gallery" method="get">
    Поиск по названию: <input type="text" name="title"/>
    <input type="submit" value="Поиск"/>
</form>

<#list coffee_arts as art>
    <div>
        <img src="/images/${art.image.id}" width="64" alt="${art.title}">
        <p>
            <b>${art.title}</b>
            <#list art.tagTESTS as tag>
                ${tag}<#sep>,
            </#list>
            <br>
            <a href="/${art.user.name}/${art.id}">Подробнее...</a>
        </p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>

<#if user.email??>
    <h3>Создать новый арт</h3>
    <form action="/addart" method="post" enctype="multipart/form-data">
        Название арта: <input type="text" name="title"/>
        <br>
        Фото первое: <input type="file" name="file"/>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Добавить товар"/>
    </form>
</#if>
</body>
</html>