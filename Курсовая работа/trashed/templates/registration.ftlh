<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuroi</title>
</head>
<body>
<h1>Kuroi</h1>
<h4>Регистрация</h4>

<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name"/>
    </br>
    Электронная почта: <input type="email" name="email"/>
    </br>
    Пароль: <input type="password" name="password"/>
    </br>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Зарегистрироваться"/>
</form>

<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>

</body>
</html>