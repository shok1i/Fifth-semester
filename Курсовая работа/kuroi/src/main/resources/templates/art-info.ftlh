<#import "blocks/template.ftlh" as t>
<@t.template currentUser>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-12 col-xl-8">

                <nav class="breadcrumb">
                    <oi class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a class="text-secondary" href="/gallery">Галерея</a>

                        </li>

                        <li class="breadcrumb-item">
                            <a class="text-secondary" href="/${author.userNickName}">${author.userNickName}</a>
                        </li>

                        <li class="breadcrumb-item">
                            <a class="text-secondary" href="/${author.userNickName}/${art.artID}">Обзор штуки</a>
                        </li>
                    </oi>
                </nav>

                <div class="row g-3">
                    <div class="col-12 col-md-6" style="align-content: center;">
                        <div class="card">
                            <img src="/images/${art.image.imageID}" class="card-img img-fluid" alt="">
                        </div>
                    </div>

                    <div class="col-lg-6 col-md-8 col-12">
                        <div class="card rounded" style="border: solid #6cabd9 2px" id="register_div">

                            <div class="container-fluid mx-0 px-0">
                                <div class="rounded"
                                     style="height: 160px; width: 100%; background: url(<#if author.banner??>'/images/${author.banner.imageID}'<#else>'/static/images/profile-back.jpg'</#if>) no-repeat center center; background-size: cover;)">
                                </div>
                            </div>

                            <div class="container">
                                <div class="row justify-content-center" style="margin-bottom: 3rem">
                                    <div class="col-12 col-md-10">
                                        <div class="row justify-content-start align-items-start mb-0 pb-0">
                                            <div class="col-auto"
                                                 style="transform: translate3d(0,-100%,0); max-height: 5rem">

                                                <img width="180" height="180" class="rounded-circle"
                                                     style="border: 0.3rem solid #6cabd9; background-color: #6cabd9;"
                                                     src=<#if author.image??>"/images/${author.image.imageID}"<#else>"/static/images/profile.svg"</#if>
                                                >

                                            </div>

                                            <h3 class="col-auto" style="transform: translateX(5%);">${author.userBIO}
                                                <br>
                                                <span style="font-weight: 300; font-size: 0.75em">@${author.userNickName}</span>
                                            </h3>
                                        </div>

                                    </div>
                                </div>
                            </div>


                            <div class="container p-3">
                                <p>
                                    <span class="h5" style="color: #6cabd9">Название штуки</span>
                                    <br>
                                    ${art.title}
                                </p>

                                <p>
                                    <span class="h5" style="color: #6cabd9">Описание штуки</span>
                                    <br>
                                    <#if art.description?length != 0>
                                        ${art.description}
                                    <#else>
                                        Кажется у этой штуки нет описания.
                                    </#if>
                                </p>

                                <p>
                                    <span class="h5" style="color: #6cabd9">Дата создания</span>
                                    <br>
                                    ${art.publishDate}
                                </p>

                                <div class="container">
                                    <div class="row align-items-center justify-content-between">
                                        <div class="col-9">
                                            <div class="row align-items-center mb-0 pb-0">
                                                <div class="col-10">
                                                    <div class="row justify-content-between">
                                                        <#if currentUser.userID??>
                                                            <#assign isAdding = !isContain>
                                                            <#assign actionUrl = isAdding?string("toggleCollection('POST'", "toggleCollection('DELETE'")>
                                                            <#assign buttonText = isAdding?string("Добавить штуку на полку", "Удалить штуку с полки")>

                                                            <button id="btn_collection" onclick="${actionUrl}, ${currentUser.userID}, ${art.artID})" type="submit" class="btn btn-outline-danger">${buttonText}</button>
                                                        <#else>
                                                            <a href="/login" class="btn btn-outline-danger">Добавить
                                                                штуку на полку</a>
                                                        </#if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-3 text-end align-self-center">
                                            <span class="d-block" style="white-space: nowrap;">
                                                <span class="text-like me-1" id="likesCount">${art.likes?size}</span>
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
            </div>
        </div>
    </div>


</@t.template>