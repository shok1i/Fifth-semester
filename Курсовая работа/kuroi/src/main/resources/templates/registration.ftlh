<#import "blocks/template.ftlh" as t>
<@t.template currentUser>

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

<#--    <form action="/registration" method="post">-->
<#--        <div class="form-group">-->
<#--            <label for="userName">Имя пользователя</label>-->
<#--            <input type="text" class="form-control" required id="name" name="userName">-->
<#--        </div>-->

<#--        <div class="form-group">-->
<#--            <label for="userEmail">Электронная почта</label>-->
<#--            <input type="email" class="form-control" id="userEmail" required name="userEmail">-->
<#--        </div>-->

<#--        <div class="form-group">-->
<#--            <label for="userNickName">Никнейм</label>-->
<#--            <input type="text" class="form-control" id="userNickName" required name="userNickName">-->
<#--        </div>-->

<#--        <div class="form-group">-->
<#--            <label for="password">Пароль</label>-->
<#--            <input type="password" class="form-control" id="password" required name="password">-->
<#--        </div>-->

<#--        <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--        <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>-->
<#--    </form>-->
<#--    <#if errorMessage??>-->
<#--        <h2 style="color: red">${errorMessage}</h2>-->
<#--    </#if>-->
</@t.template>
