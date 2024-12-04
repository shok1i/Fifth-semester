<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuroi</title>
</head>
<body>
<h1>Kuroi</h1>
<h4>Авторизация</h4>

<form action="/login" method="post">
    Электронная почта: <input type="email" name="username"/>
    </br>
    Пароль: <input type="password" name="password"/>
    </br>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" value="Вход"/>
</form>

<a href="/registration">Зарегистрироваться</a>

</body>
</html>