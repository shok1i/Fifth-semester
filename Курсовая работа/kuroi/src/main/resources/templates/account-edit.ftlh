<#import "blocks/template.ftlh" as t>
<@t.template currentUser>
    <div class="col-sm-12 col-md-8 col-lg-6">
        <div class="card shadow p-0 rounded-3">
            <div class="container rounded-top position-relative"
                 style="background: url('/static/images/profile-back.jpg') no-repeat center center; background-size: cover">
                <div class="mb-3 text-center py-5">
                    <div class="container" id="avatar"
                         style="display: inline-block; border-radius: 50%; border: 10px solid #6cabd9; width: 20rem; height: 20rem;
                                 background: <#if currentUser.image??> url('/images/${currentUser.image.id}')
                                 <#else>url('/static/images/profile.svg')</#if> no-repeat center center; background-size: cover">
                    </div>
                </div>
            </div>

            <form action="/account/edit" method="post" enctype="multipart/form-data">
                <div class="container p-3">
                    <h3>
                        ${currentUser.userBIO}<br>
                        <span style="font-weight: 300; font-size: 0.75em;">${currentUser.userNickName}</span>
                    </h3>

                    <p>
                        <span class="h5" style="color: #6cabd9">Почта</span>
                        <br>
                        @${currentUser.userEmail}
                    </p>

                    <p>
                        <span class="h5" style="color: #6cabd9">Аватар</span>
                        <br>
                        <input type="file" name="file" class="form-control form-control-sm" id="id_avatar">
                    </p>

                    <p>
                        <span class="h5" style="color: #6cabd9">Имя пользователя</span>
                        <br>
                        <input type="text" name="userBIO" value="${currentUser.userBIO}" class="form-control mb-3"
                               maxlength="64" id="id_userBIO">
                    </p>

                    <p>
                        <span class="h5" style="color: #6cabd9">Никнейм</span>
                        <br>
                        <input type="text" name="userNickName" value="${currentUser.userNickName}"
                               class="form-control mb-3"
                               maxlength="64" id="id_userNickName">
                    <div class="<#if errorMessage??>d-flex<#else>d-none</#if> text-danger">Такой никнейм уже
                        используется!
                    </div>
                    </p>

                    <div class="row justify-content-between pt-3">
                        <div class="col">
                            <div class="row justify-content-end g-2">
                                <div class="col-auto">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-sm btn-outline-warning text-dark">
                                        Сохранить
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>

</@t.template>
