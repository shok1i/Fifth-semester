<#macro template user>
    <!doctype html>
    <html lang="ru">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
              integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
              crossorigin="anonymous">

        <title>BUYSELL</title>
    </head>
    <body>

    <nav class="navbar navbar-expand-md navbar-light mx-auto sticky-top shadow-sm h-auto" role="navigation" id="navbar">
        <div class="container align-self-center align-content-center navbar-lutra d-flex align-items-baseline">
            <a class="navbar-brand p-0" href="/"><img src="/static/logo.svg" height="50px" style="max-height: 50px" alt=""></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse navbar-expand-lg justify-content-between" id="navbarText">
                <ul class="navbar-nav me-auto mx-auto mb-2 mb-lg-0 d-flex align-items-baseline">
                    <li class="nav-item">
                        <a class="nav-link" href="/goods/">Магазин</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/gallery?s=popular&amp;d=90">Галерея</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/my">Моя полка</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/order/list">Заказы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/info/">Помощь</a>
                    </li>
                </ul>


                <div class="dropdown">
                    <a href="/account/" data-bs-toggle="dropdown" aria-expanded="true" class="show">
                        <img src="/media/users_uploads/user3528/avatars/profile_KSELwN5.png" alt="" class="circle" height="40px" width="40px" style="max-height: 40px; max-width: 40px; border: darkgoldenrod; border-radius: 50%">
                    </a>
                    <ul class="dropdown-menu dropdown-menu-lg-end show" data-bs-popper="static">
                        <li><a class="dropdown-item" href="/account/">Аккаунт</a></li>
                        <li><a class="dropdown-item" href="/shokii">Мои публикации</a>
                        </li>
                        <li><a class="dropdown-item" href="/notifications/">Уведомления</a></li>
                        <li><a class="dropdown-item" href="/account/balance">Баланс: 0 FS</a>
                        </li>

                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="/account/logout">Выйти</a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>