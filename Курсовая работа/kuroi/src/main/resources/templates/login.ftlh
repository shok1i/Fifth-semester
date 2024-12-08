<#import "blocks/template.ftlh" as t>
<@t.template currentUser>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-12 col-xl-8">
                <div class="row justify-content-between mx-auto">
                    <div class="col-lg-6 col-md-8 col-12">
                        <p class="text-start" style="font-size: x-large; font-weight: initial">Авторизация</p>
                        <p class="text-start lead">Еще нет аккаунта в KuroiStickers,<br>не беда
                            <a href="/registration" style="color: #6cabd9; text-decoration: none;">зарегистрируйся!</a>
                        </p>
                    </div>
                    <div class="col-lg-6 col-md-8 col-12">
                        <div class="card rounded" style="border: solid #6cabd9 2px" id="register_div">
                            <form action="/login" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">

                                <div class="card-body">
                                    <div class="mb-3">
                                        <label for="id_email" class="form-label">Email:</label>
                                        <input type="email" name="username" class="form-control shadow-sm"
                                               id="id_email">
                                        <div class="d-none text-danger" id="invalid_email">Возможно вы ошиблись в
                                            почте!
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="password" class="form-label">Пароль:</label>
                                        <input type="password" name="password" class="form-control shadow-sm"
                                               id="password">
                                        <div class="d-none text-danger" id="invalid_email">Неверный пароль!</div>
                                    </div>

                                    <div class="row justify-content-end">
                                        <div class="col-auto" id="button_div">
                                            <button type="submit" class="btn btn-outline-danger">Войти</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@t.template>
