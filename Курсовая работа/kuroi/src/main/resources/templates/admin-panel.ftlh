<#import "blocks/template.ftlh" as t>
<@t.template currentUser>
    <div class="row justify-content-center">
        <div class="col-12 col-md-12 col-xl-8">
            <div class="container mt-4">
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

                <div class="container mt-2">
                    <div class="row justify-content-center">
                        <div class="col-12 col-md-10">
                            <div id="app" data-v-app="">
                                <form class="input-group w-100 mb-2" action="/admin_panel" method="get">
                                    <input type="text" name="searchWord" class="form-control text-center shadow-sm"
                                           placeholder="Поиск пользователя по id, никнейму или почте"
                                           value="<#if searchWord??>${searchWord}<#else></#if>"/>
                                    <button type="submit" class="btn btn-outline-primary shadow-sm"
                                            data-mdb-ripple-init>Найти
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container mt-2">
                    <div class="row justify-content-center">
                        <div class="col-12 col-md-10">
                            <table class="table table-striped table-hover">
                                <thead class="table-info">
                                <tr>
                                    <th>Id</th>
                                    <th>Email</th>
                                    <th>Никнейм</th>
                                    <th>Активность</th>
                                    <th>Роли</th>
                                    <th>Изменить статус</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list users as user>
                                    <tr>
                                        <td>${user.userID}</td>
                                        <td>${user.userEmail}</td>
                                        <td>${user.userNickName}</td>
                                        <td>
                                            <#if user.active>
                                                Активный
                                            <#else>
                                                Забанен
                                            </#if>
                                        </td>
                                        <td>
                                            <div class="btn-group dropend" role="group">
                                                <button type="button" class="dropdown-toggle"
                                                        style=" background: none; border: none;"
                                                        data-bs-toggle="dropdown"
                                                        aria-expanded="false">
                                                    <#list user.roles as role>
                                                        ${role}
                                                    </#list>
                                                </button>

                                                <ul class="dropdown-menu">
                                                    <#list roles as role>
                                                        <li class="dropdown-item">
                                                            <form action="/admin_panel/edit" method="post">
                                                                <input type="hidden" name="_csrf"
                                                                       value="${_csrf.token}"/>

                                                                <input type="hidden" value="${user.userEmail}"
                                                                       name="editUser">
                                                                <input type="hidden" value="${role}" name="newRole">

                                                                <button type="submit" class="dropdown-item"
                                                                        style=" background: none; border: none;">${role}</button>
                                                            </form>
                                                        </li>
                                                    </#list>
                                                </ul>
                                            </div>


                                        </td>
                                        <td>
                                            <form action="/admin_panel/ban" method="post">
                                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                                                <input type="hidden" value="${user.userID}" name="editUser">

                                                <button type="submit" class="dropdown-item"
                                                        style=" background: none; border: none;">
                                                    <#if user.active>Забанить
                                                    <#else>Разбанить
                                                    </#if>
                                                </button>
                                            </form>
                                        </td>

                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@t.template>