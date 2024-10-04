<?php
// Функция для сортировки массива методом вставок
function insertionSort($array) {
    $n = count($array);
    for ($i = 1; $i < $n; $i++) {
        $key = $array[$i];
        $j = $i - 1;

        // Сдвигаем элементы массива, которые больше ключа, вправо
        while ($j >= 0 && $array[$j] > $key) {
            $array[$j + 1] = $array[$j];
            $j = $j - 1;
        }
        $array[$j + 1] = $key;
    }
    return $array;
}
?>