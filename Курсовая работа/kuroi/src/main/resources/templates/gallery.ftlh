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
                    <div><input type="text" class="form-control w-100 mb-2 text-center bg-light shadow-sm"
                                placeholder="Поиск 🔍" style=""></div>
                    <div class="posts">
                        <div class="row">
                            <#list arts as art>

                                <div class="col-6 col-md-6 col-lg-4 col-xl-3 posts__post post mt-2 post_active">
                                    <div class="card">
                                        <div class="card-header position-relative">
                                            <div class="row justify-content-start align-items-center">
                                                <div class="col-auto px-1"><a href="/${art.author.userNickName}">
                                                        <img src="/${art.author.image.id}" alt=""
                                                             style="border-radius: 50%; height: 30px; width: 30px;"></a>
                                                </div>
                                                <div class="col-auto px-0"><a href="${art.author.userNickName}"
                                                                              class="text-decoration-none text-dark">${art.author.userNickName}</a><span
                                                            style="display: none;"> +0</span></div>
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
</@t.template>


