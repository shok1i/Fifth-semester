<#import "blocks/template.ftlh" as t>
<@t.template currentUser>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10 col-xl-9">
                <div class="row justify-content-between mb-2">
                    <div class="col-auto"><h3>Заказы</h3></div>
                    <div class="col-auto"><a href="/order/make" class="btn btn-warning">Заказать</a></div>
                </div>

                <#if orderList?size != 0>
                    <div class="row">
                        <div class="col-12">
                            <div class="row justify-content-between align-items-center py-2 shadow-sm rounded-top" style="background-color: #6cabd9">
                                <div class="col-1"><span style="font-weight: bold">#</span></div>
                                <div class="col-2 d-none d-lg-block"><span style="font-weight: bold">Дата заказа</span>
                                </div>
                                <div class="col-2 d-none d-lg-block"><span style="font-weight: bold">Стоимость</span>
                                </div>
                                <div class="col-3"><span style="font-weight: bold">Статус</span></div>
                                <div class="col-3 col-lg-2"><span style="font-weight: bold">Обзор</span></div>
                            </div>

                            <#list orderList as order>
                                <div class="row justify-content-between align-items-center bg-light">
                                    <div class="col-1">${order.orderID}</div>
                                    <div class="col-2 d-none d-lg-block">${order.orderDate}</div>
                                    <div class="col-2 d-none d-lg-block">${order.price} ₽</div>
                                    <div class="col-3">
                                        <span>${order.orderStatus}</span>
                                    </div>
                                    <div class="col-3 col-lg-2">
                                        <a href="/order/${order.orderID}" class="text-dark">Перейти</a>
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


</@t.template>