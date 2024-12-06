<#import "blocks/template.ftlh" as t>
<@t.template currentUser>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10">
                <div class="row justify-content-between align-items-center pb-3">
                    <div class="col-auto justify-content-lg-start">
                        <h3>Панель администратора</h3>

                        <h5>Список пользователей</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10">
                <div id="app" data-v-app="">
                    <form class="input-group w-100 mb-2" action="/admin-panel" method="get">
                        <input type="text" name="searchWord" class="form-control text-center shadow-sm"
                               placeholder="Поиск пользователя по никнейму или почте"
                               value="<#if searchWord??>${searchWord}<#else></#if>"/>
                        <button type="submit" class="btn btn-outline-primary shadow-sm" data-mdb-ripple-init>Найти
                        </button>
                    </form>

                    <div class="list-group">
                        <div class="list-group-item list-group-horizontal active">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">Id</h5>
                            </div>

                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">Email</h5>
                            </div>

                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">Никнейм</h5>
                            </div>

                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">Активность</h5>
                            </div>

                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">Роли</h5>
                            </div>
                        </div>

                        <#list users as user>
                            <div class="list-group-item list-group-horizontal">
                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${user.id}</h5>
                                </div>

                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${user.userEmail}</h5>
                                </div>

                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">${user.userNickName}</h5>
                                </div>

                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">
                                        <#if user.active>
                                            Активный
                                        <#else>
                                            Забанен
                                        </#if>
                                    </h5>
                                </div>

                                <div class="d-flex w-100 justify-content-between">
                                    <h5 class="mb-1">
                                        <#list user.roles as role>
                                            ${role}<#sep>,
                                        </#list>
                                    </h5>
                                </div>
                            </div>

                        </#list>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@t.template>