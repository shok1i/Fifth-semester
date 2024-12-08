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
                                    <h3>Полочка штук добавленных на полку</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-12 col-md-10">

                            <div class="posts ">
                                <div class="row">
                                    <#if arts?size != 0>
                                        <#list arts as art>
                                            <div class="col-6 col-md-6 col-lg-4 col-xl-3 posts__post post mt-2 post_active">
                                                <div class="card">
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
                                                             style="">
                                                    </a>

                                                    <div class="card-body">
                                                        <div class="row justify-content-between">
                                                            <div class="col">
                                                                <div class="row justify-content-between">
                                                                    <div class="col-8">
                                                                        <span>${art.title}</span>
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
                                                    <div class="card-body">
                                                        <h3 class="text-center"> Как-то тут пусто...</h3>
                                                        <p class="text-center">Давайте добавим парочку стикеров</p>
                                                    </div>
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
</@t.template>
