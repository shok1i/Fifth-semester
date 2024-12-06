<#import "blocks/template.ftlh" as t>
<@t.template currentUser>
    <div class="row justify-content-center">
        <div class="col-12 col-md-12 col-xl-8">
            <div class="row justify-content-between mx-auto">

                <div class="col-lg-6 col-md-8 col-12">
                    <p class="text-start" style="font-size: x-large; font-weight: initial">Регистрация</p>
                    <p class="text-start lead">Уже есть аккаунт в KuroiStickers, не беда
                        <a href="/login" style="color: #6cabd9; text-decoration: none;">войди!</a>
                    </p>
                </div>

                <div class="col-lg-6 col-md-8 col-12">
                    <div class="card rounded" style="border: solid #6cabd9 2px" id="register_div">
                        <form action="/registration" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">

                            <div class="card-body">
                                <div class="mb-3">
                                    <label for="userBIO" class="form-label">Имя пользователя:</label>
                                    <input type="text" name="userBIO" class="form-control shadow-sm" id="userBIO">
                                </div>

                                <div class="mb-3">
                                    <label for="userNickName" class="form-label">Никнейм:</label>
                                    <input type="text" name="userNickName" class="form-control shadow-sm" id="userNickName">
                                    <div class="d-none text-danger">Такой никнейм уже используется!</div>
                                </div>

                                <div class="mb-3">
                                    <label for="id_email" class="form-label">Email:</label>
                                    <input type="email" name="userEmail" class="form-control shadow-sm" id="id_email">
                                    <div class="d-none text-danger">Возможно вы ошиблись в почте!</div>
                                </div>

                                <div class="mb-3">
                                    <label for="password" class="form-label">Пароль:</label>
                                    <input type="password" name="password" class="form-control shadow-sm" id="password">
                                    <div class="d-none text-danger">Неверный пароль!</div>
                                </div>

                                <div class="row justify-content-end">
                                    <div class="col-auto" id="button_div">
                                        <button type="submit" class="btn btn-outline-danger">Зарегистрироваться</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@t.template>
