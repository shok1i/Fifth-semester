<#import "blocks/template.ftlh" as t>
<@t.template currentUser>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10 col-xl-9">
                <h3>Оформление заказа:</h3>
                <#if currentUser.userCollection.collections_arts?size != 0 || currentUser.arts?size != 0>
                    <div class="card">
                        <#if currentUser.userCollection.collections_arts?size != 0>
                            <div class="card-body pb-0 mb-0">
                                <h5 class="pb-1">Стикеры с твоей полки</h5>
                            </div>
                        </#if>
                        <div class="container-fluid flex-start d-flex px-0"
                             style="overflow-x: auto; white-space: nowrap;">
                            <#list currentUser.userCollection.collections_arts as art>
                                <div class="card shadow-sm ripple me-2 mb-3" id="SID${art.artID}"
                                     onclick="addSticker(${art.artID})"
                                     style="overflow: hidden; display: flex; min-width: 15rem; min-height: 18rem; max-width: 15rem; max-height: 20rem">
                                    <img src="/images/${art.image.imageID}" id="preview_image" class="card-img-top"
                                         style="max-height: 238px; object-fit: contain; background-color: gray"
                                         alt="...">
                                    <div class="card-body">
                                        <small class="badge rounded-pill bg-success">${art.title}</small>
                                    </div>
                                </div>
                            </#list>
                        </div>
                        <#if currentUser.arts?size != 0>
                            <div class="card-body pb-0 mb-0">
                                <h5 class="pb-1">Стикеры добавленные на сайт тобой</h5>
                            </div>
                        </#if>
                        <div class="container-fluid flex-start d-flex px-0"
                             style="overflow-x: auto; white-space: nowrap;">
                            <#list currentUser.arts as art>
                                <div class="card shadow-sm ripple me-2 mb-3" id="SID${art.artID}"
                                     onclick="addSticker(${art.artID})"
                                     style="overflow: hidden; display: flex; min-width: 15rem; min-height: 18rem; max-width: 15rem; max-height: 20rem">
                                    <img src="/images/${art.image.imageID}" id="preview_image" class="card-img-top"
                                         style="max-height: 238px; object-fit: contain; background-color: gray"
                                         alt="...">
                                    <div class="card-body">
                                        <small class="badge rounded-pill bg-success">${art.title}</small>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </div>

                <#else>
                    <div class="container" style="">
                        <div class="card bg-light">
                            <div class="card-body"><h4 class="text-center">Как-то тут пустовато</h4></div>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10 col-xl-9">
                <div class="alert alert-success my-2 text-center" role="alert">
                    Товары добавленные в корзину.
                </div>
                <div class="row" id="order-list">

                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10 col-xl-9">
                <div class="row justify-content-between align-items-start" id="pricesDiv">
                    <div class="col-12">
                        <div class="col-12 col-md-4 mb-3 mb-md-0">
                            <p class="mb-2">
                                Стоимость товаров: <span id="itemsPrice"></span> ₽
                            </p>
                        </div>

                        <div class="col-12 col-md-7">
                            <div class="row">
                                <div class="col-12">
                                    <button class="btn btn-warning w-100 mt-2" type="submit" onclick="makeOrder(${currentUser.userID})">
                                        Заказать
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@t.template>
