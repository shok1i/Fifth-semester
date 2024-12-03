<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuroi</title>
</head>

<body>
<h1>Kuroi</h1>
<h4>Панель администратора</h4>

<table border="1">
    <tr>
        <th>Email</th>
        <th>Имя пользователя</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>БАН</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.name}</th>
            <th>
                <#if user.active>
                    true
                <#else >
                    false
                </#if>
            </th>
            <th>
                <#list user.roles as role>
                    ${role}<#sep>, 
                </#list>
            </th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="submit"
                           <#if user.active>value="Забанить"<#else> value="Разбанить"</#if>
                    />
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактирование</a></th>
            <th><a href="/admin/user/${user.id}">Подробнее</a></th>
        </tr>
    <#else>
        <h3>Пользователи не найдены</h3>
    </#list>
</table>

</body>
</html>