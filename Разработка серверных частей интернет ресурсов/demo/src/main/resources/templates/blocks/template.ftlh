<#macro template>
    <!doctype html>
    <html lang="ru">
    <head>
        <title>JavaSpring</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>

    <body>
        <div>
            <a href="/">main</a>
            <a href="/coffee">coffee</a>
            <a href="/order">order</a>
            <a href="/redis">redis</a>
        </div>

        <#nested/>
    </body>

</#macro>