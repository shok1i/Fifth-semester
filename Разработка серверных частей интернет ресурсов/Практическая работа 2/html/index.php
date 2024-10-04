<html lang="en">
<head>
    <title>Hello world page</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
</head>
<body>
<h1>SVG Drawing Service</h1>
<?php
require_once 'draw.php'; // Подключаем файл с классом Drawer

// Проверяем, был ли передан параметр num
if (isset($_GET['num'])) {
    $num = intval($_GET['num']); // Получаем и приводим параметр к целому числу
    $drawer = new Drawer(); // Создаем экземпляр класса Drawer
    echo $drawer->drawSvg($num); // Генерируем SVG с помощью метода drawSvg
} else {
    echo '<p>No shape to draw. Please pass a number as ?num=1</p>';
}
?>
</body>
</html>