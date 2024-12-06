<#import "blocks/template.ftlh" as t>
<@t.template currentUser>
    <div class="container-fluid mx-0 px-0">


        <div class="rounded-top"
             style="height: 333px; width: 100%; background: url('/static/images/profile-back.jpg') no-repeat center center; background-size: cover;)">
        </div>


    </div>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10">
                <div class="row justify-content-start align-items-start mb-0 pb-0">
                    <div class="col-auto" style="transform: translate3d(0,-50%,0); max-height: 10.5rem">


                        <img width="180" height="180" class="rounded-circle"
                             style="border: 0.3rem solid #6cabd9; background-color: #6cabd9;"
                             src=<#if findUser.image??>"/images/${findUser.image.id}"<#else>"/static/images/profile.svg"</#if>>


                        <h3 style="transform: translateX(5%);">${findUser.userBIO}<br><span
                                    style="font-weight: 300; font-size: 0.75em">@${findUser.userNickName}</span></h3>
                    </div>
                </div>
                <p class="ms-1">Дата регистрации: ${findUser.createAt}</p>

            </div>
        </div>
    </div>

    <div class="container pt-4">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10">

                <div id="app" data-v-app="">
                    <div class="posts">
                        <div class="row">
                            <#if findUser.arts??>
                                <#list findUser.arts as art>
                                    <div class="col-6 col-md-6 col-lg-4 col-xl-3 posts__post post mt-2 post_active">
                                        <div class="card">
                                            <div class="card-header position-relative">
                                                <div class="row justify-content-start align-items-center">
                                                    <div class="col-auto px-1">
                                                        <a href="/${art.author.userNickName}">
                                                            <img alt=""
                                                                 style="border-radius: 50%; height: 30px; width: 30px;"
                                                                 src=<#if art.author.image??>"/images/${art.author.image.id}"<#else>"/static/images/profile.svg"</#if>
                                                            >
                                                        </a>
                                                    </div>
                                                    <div class="col-auto px-0">
                                                        <a href="/${art.author.userNickName}"
                                                           class="text-decoration-none text-dark">${art.author.userNickName}</a>
                                                    </div>
                                                </div>
                                            </div>

                                            <a href="/${art.author.userNickName}/${art.id}">
                                                <img src="/images/${art.image.id}" class="card-img-bottom no-repeat"
                                                     style="">
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
                                                                <span class="text-like me-1"
                                                                      id="likesCount">${art.likes?size}</span>
                                                                <svg class="heart" viewBox="0 0 1546.02 1347.49"
                                                                     style="width: 20px;">
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
                            <#else>
                                <div class="container" style="">
                                    <div class="card bg-light">
                                        <div class="card-body"><h4 class="text-center">К сожалению у данный пользователь
                                                еще не добавил свои штуки на сайт.</h4></div>
                                    </div>
                                </div>
                            </#if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@t.template>
