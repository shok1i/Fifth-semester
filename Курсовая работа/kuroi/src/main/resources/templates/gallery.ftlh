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
                                    <h3>Галерея стикеров</h3>

                                    <h5>Популярные стикеры</h5>

                                    <a class="text-danger text-decoration-none"
                                       href="/gallery?searchWord=<#if searchWord??>${searchWord}<#else></#if>&days=7">За
                                        неделю </a> /
                                    <a class="text-danger text-decoration-none"
                                       href="/gallery?searchWord=<#if searchWord??>${searchWord}<#else></#if>&days=30">За
                                        месяц </a> /
                                    <a class="text-danger text-decoration-none"
                                       href="/gallery?searchWord=<#if searchWord??>${searchWord}<#else></#if>&days=365">За
                                        год </a> /
                                    <a class="text-danger text-decoration-none"
                                       href="/gallery?searchWord=<#if searchWord??>${searchWord}<#else></#if>">За всё
                                        время</a>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-12 col-md-10">

                            <div data-v-app="">
                                <form class="input-group w-100 mb-2" action="/gallery" method="get">
                                    <input type="text" name="searchWord" class="form-control text-center shadow-sm"
                                           placeholder="Поиск интересной штуки"
                                           value="<#if searchWord??>${searchWord}<#else></#if>"/>
                                    <button type="submit" class="btn btn-outline-primary shadow-sm"
                                            data-mdb-ripple-init>Найти
                                    </button>
                                </form>

                                <div class="posts ">
                                    <div class="row">
                                        <#if arts?size != 0>
                                            <#list arts as art>
                                                <div class="col-6 col-md-6 col-lg-4 col-xl-3 posts__post post mt-2 post_active">
                                                    <div id="CARD${art.artID}" class="card">
                                                        <div class="card-header position-relative">
                                                            <div class="row justify-content-start align-items-center">
                                                                <div class="col-auto px-1">
                                                                    <a href="/${art.author.userNickName}">
                                                                        <img alt=""
                                                                             style="border-radius: 50%; height: 30px; width: 30px;"
                                                                             src=<#if art.author.image??>"/images/${art.author.image.imageID}"<#else>"/static/images/profile.svg"</#if>
                                                                        >
                                                                    </a>
                                                                </div>
                                                                <div class="col-auto px-0">
                                                                    <a href="/${art.author.userNickName}"
                                                                       class="text-decoration-none text-dark">${art.author.userNickName}</a>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <a href="/${art.author.userNickName}/${art.artID}">
                                                            <img src="/images/${art.image.imageID}"
                                                                 class="card-img-bottom no-repeat"
                                                                 style="max-height: 230px; object-fit: contain;">
                                                        </a>

                                                        <div class="card-body">
                                                            <div class="row justify-content-between">
                                                                <div class="col">
                                                                    <div class="row justify-content-between">
                                                                        <div class="col-8"><span>${art.title}</span>
                                                                        </div>
                                                                        <div class="col-4 text-end align-self-start">

                                                                                    <span
                                                                                            class="d-block"
                                                                                            style="white-space: nowrap;">
                                                                                        <span class="text-like me-1"
                                                                                              id="likesCount">${art.likes?size}</span>
                                <#if currentUser.userID??>
                                    <#assign isAdding = true>
                                    <#if currentUser.likes??>
                                        <#list currentUser.likes as likesArray>
                                            <#list likesArray.liked_arts as temp>
                                                <#if temp.artID == art.artID>
                                                    <#assign isAdding = false>
                                                    <#break>
                                                </#if>
                                            </#list>
                                        </#list>
                                    </#if>

                                    <#assign actionUrl = isAdding?string("toggleLike('POST'", "toggleLike('DELETE'")>
                                    <#assign likeStyle = isAdding?string(" ", "liked")>

                                    <svg id="likesDiv" onclick="${actionUrl}, ${currentUser.userID}, ${art.artID})"
                                         class="heart ${likeStyle}" viewBox="0 0 1546.02 1347.49">
                                        <path d="M1515.78,257.58A412.62,412.62,0,0,1,1425.06,705L789.31,1340.73a23,23,0,0,1-32.61,0L121,705A413,413,0,1,1,705,121l68,68,68-68a413.2,413.2,0,0,1,674.75,136.62Z"></path>
                                    </svg>
                                <#else>
                                    <a href="/login">
                                        <svg class="heart" viewBox="0 0 1546.02 1347.49">
                                            <path d="M1515.78,257.58A412.62,412.62,0,0,1,1425.06,705L789.31,1340.73a23,23,0,0,1-32.61,0L121,705A413,413,0,1,1,705,121l68,68,68-68a413.2,413.2,0,0,1,674.75,136.62Z"></path>
                                        </svg>
                                    </a>
                                </#if>
                                                                                    </span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </#list>
                                        <#else>
                                            <div class="container mt-2" style="">
                                                <div class="card bg-light">
                                                    <div>
                                                        <div class="card-body"><h3 class="text-center"> Такие стикеры не
                                                                найдены... </h3>
                                                            <p class="text-center">Попробуйте ввести другое название
                                                                стикера, тег
                                                                или имя автора</p></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </#if>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@t.template>
