<#import "blocks/template.ftlh" as t>
<@t.template currentUser>
    <div class="row justify-content-center">
        <div class="col-12 col-md-12 col-xl-8">
            <div class="container mt-4">
                <div class="row justify-content-between mx-auto">

                    <div class="col-lg-6 col-md-8 col-12">
                        <p class="text-start" style="font-size: x-large; font-weight: initial">Добавление стикера в наш
                            альбом</p>
                        <p class="text-start lead">
                            Небольшие правила для добавления вашего стикера:
                        </p>
                        <p class="text-start lead">
                            Загружаемые стикеры должны быть авторскими. Использование чужих работ без разрешения
                            запрещено.
                        </p>
                        <p class="text-start lead">
                            Плагиат и использование защищенных авторским правом материалов без лицензии недопустимы.
                        </p>
                        <p class="text-start lead">
                            Стикеры не должны содержать материалы, нарушающие законодательство.
                        </p>
                        <p class="text-start lead">
                            Следуйте этим правилам при добавлении своего стикера!
                        </p>
                    </div>

                    <div class="col-lg-6 col-md-8 col-12">
                        <div class="card rounded" style="border: solid #6cabd9 2px" id="register_div">
                            <form action="/addart" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">

                                <div class="card-body">
                                    <div class="mb-3">
                                        <label for="id_title" class="form-label">Название:</label>
                                        <input type="text" name="title" class="form-control shadow-sm" id="id_title">
                                    </div>

                                    <div class="mb-3">
                                        <label for="id_sticker" class="form-label">Стикер:</label>
                                        <input type="file" name="file" class="form-control form-control-sm"
                                               id="id_sticker">
                                    </div>

                                    <div class="mb-3">
                                        <label for="id_sticker_desc" class="form-label">Описание:</label>
                                        <textarea name="description" cols="40" rows="4" class="form-control"
                                                  maxlength="600"
                                                  id="id_about"></textarea>
                                    </div>

                                    <div class="row justify-content-end">
                                        <div class="col-auto" id="button_div">
                                            <button type="submit" class="btn btn-outline-danger">Добавить</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@t.template>