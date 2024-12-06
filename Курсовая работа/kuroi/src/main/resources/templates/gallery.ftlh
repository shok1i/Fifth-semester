<#import "blocks/template.ftlh" as t>
<@t.template currentUser>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10">
                <div class="row justify-content-between align-items-center pb-3">
                    <div class="col-auto justify-content-lg-start">
                        <h3>Галерея стикеров
                            <span class="bi bi-info-circle text-muted" data-bs-toggle="tooltip"
                                  style="font-size: 1.5rem"
                                  data-bs-title="Это пространство, в которое пользователи сайта добавляют свои стикеры для продажи."
                                  data-bs-placement="right">
                            </span>
                        </h3>
                        <h5>Популярные стикеры</h5>

                        <a class="text-danger text-decoration-none" href="/gallery">За неделю </a> /
                        <a class="text-danger text-decoration-none" href="/gallery">За месяц </a> /
                        <a class="text-danger text-decoration-none" href="/gallery">За год </a> /
                        <a class="text-danger text-decoration-none" href="/gallery">За всё время</a>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10">

                <div id="app" data-v-app="">
                        <form class="input-group w-100 mb-2" action="/gallery" method="get">
                            <input type="text" name="searchWord" class="form-control text-center shadow-sm"
                                   placeholder="Поиск интересной штуки" value="<#if searchWord??>${searchWord}<#else></#if>"/>
                            <button type="submit" class="btn btn-outline-primary" data-mdb-ripple-init>Найти</button>
                        </form>

                    <div class="posts ">
                        <div class="row">
                            <#list arts as art>
                            <div class="col-6 col-md-6 col-lg-4 col-xl-3 posts__post post mt-2 post_active">
                                <div class="card">
                                    <div class="card-header position-relative">
                                        <div class="row justify-content-start align-items-center">
                                            <div class="col-auto px-1">
                                                <a href="/${art.author.userNickName}">
                                                    <img alt="" style="border-radius: 50%; height: 30px; width: 30px;"
                                                          src=<#if currentUser.image??>"/images/${currentUser.image.id}"<#else>"/static/images/profile.svg"</#if>>
                                                </a>
                                            </div>
                                            <div class="col-auto px-0">
                                                <a href="/${art.author.userNickName}" class="text-decoration-none text-dark">${art.author.userNickName}</a>
                                            </div>
                                        </div>
                                    </div>

                                    <a href="/${art.author.userNickName}/${art.id}">
                                        <img src="/images/${art.image.id}" class="card-img-bottom no-repeat" style="">
                                    </a>

                                    <div class="card-body">
                                        <div class="row justify-content-between">
                                            <div class="col">
                                                <div class="row justify-content-between">
                                                    <div class="col-8">
                                                        <span>${art.title}</span>
                                                    </div>
                                                    <div class="col-4 text-end align-self-start">
                                                        <div class="" id="likesDiv">
                                                            <span class="d-block" style="white-space: nowrap;">
                                                                <span class="text-like me-1" id="likesCount">${art.likes?size}</span>
                                                                <svg class="heart" viewBox="0 0 1546.02 1347.49" style="width: 20px;">
                                                                    <path d="M1515.78,257.58A412.62,412.62,0,0,1,1425.06,705L789.31,1340.73a23,23,0,0,1-32.61,0L121,705A413,413,0,1,1,705,121l68,68,68-68a413.2,413.2,0,0,1,674.75,136.62Z"></path>
                                                                </svg>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </#list>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</@t.template>


