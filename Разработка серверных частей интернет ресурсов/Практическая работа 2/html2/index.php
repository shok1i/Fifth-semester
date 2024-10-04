<html lang="en">
<head>
    <title>Merge Sort Result</title>
</head>
<body>
    <h1>Сортировка вставкаами</h1>
    <q>?array=5,2,9,1,5,6</q>
    <?php
    require_once 'mergeSort.php'; // Подключаем файл с сортировкой

    // Проверяем, передан ли параметр массива
    if (isset($_GET['array'])) {
        // Получаем массив строк и преобразуем его в массив целых чисел
        $arrayString = $_GET['array'];
        $array = array_map('intval', explode(',', $arrayString));

        // Выполняем сортировку
        $sortedArray = insertionSort($array);

        // Выводим исходный и отсортированный массив
        echo '<h2>Исходный массив: </h2>';
        echo '<p>' . htmlspecialchars($arrayString) . '</p>';

        echo '<h2>Отсортированный массив: </h2>';
        echo '<p>' . implode(', ', $sortedArray) . '</p>';
    } else {
        echo '<p>Пожалуйста, передайте массив в формате ?array=1,4,2,8,5</p>';
    }
    ?>

</body>
</html>