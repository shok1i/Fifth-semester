<#macro template currentUser>
    <!doctype html>
    <html lang="ru">
    <head>
        <!-- Bootstrap CSS -->
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="/static/bootstrap/css/bootstrap-reboot.css" rel="stylesheet">
        <link href="/static/css/animated_text.css" rel="stylesheet">
        <link href="/static/css/my_css.css" rel="stylesheet">
        <link href="/static/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
        <link href="/static/tt_norms_font/stylesheet.css" rel="stylesheet">

        <!-- Bootstrap JavaScript и Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="/static/css/some-css.css">

        <title>KuroiStickers</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

    <nav class="navbar navbar-expand-md navbar-light mx-auto sticky-top shadow-sm h-auto" role="navigation" id="navbar"
    style="font-size: 1.2em; backdrop-filter: blur(8px); -webkit-backdrop-filter: blur(8px); background-color: rgba(255, 255, 255, 0.55);}">
        <div class="container align-self-center align-content-center d-flex align-items-baseline">
            <a class="navbar-brand p-0" href="/">
                <img src="/static/images/logo.svg" height="50px" style="max-height: 50px" alt=""></a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                    aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse navbar-expand-lg justify-content-between" id="navbarText">
                <ul class="navbar-nav me-auto mx-auto mb-2 mb-lg-0 d-flex align-items-baseline">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Главная</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/gallery">Галерея</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/collection">Моя полка</a>
                    </li>
                </ul>

                <#if currentUser.userEmail??>
                    <div class="dropdown">
                        <a href="" data-bs-toggle="dropdown" aria-expanded="false">

                            <img alt="" class="circle" height="40px" width="40px"
                                 style="max-height: 40px; max-width: 40px; border-radius:50%"

                                 src=<#if currentUser.image??>"/images/${currentUser.image.id}"<#else>"/static/images/profile.svg"</#if>>
                        </a>

                        <ul class="dropdown-menu dropdown-menu-lg-end">
                            <li><a class="dropdown-item" href="/account">Аккаунт</a></li>
                            <li><a class="dropdown-item" href="/${currentUser.userNickName}">Мои публикации</a></li>

                            <li>
                                <hr class="dropdown-divider">
                            </li>

                            <li>
                                <form action="/logout" method="post">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="dropdown-item"
                                            style=" background: none; border: none;">
                                        Выйти
                                    </button>
                                </form>
                            </li>
                        </ul>

                    </div>
                <#else>
                    <span class="ms-2"><a href="/login" class="text-decoration-none" style="color: #6cabd9">Войти / Зарегистрироваться</a></span>
                </#if>

            </div>
        </div>
    </nav>

    <div class="container-fluid mx-0 mb-2 py-1" style="display:block">


    </div>

    <div>
        <#nested/>
    </div>

    </body>
    </html>
</#macro>