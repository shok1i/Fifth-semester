<?php
  date_default_timezone_set('Europe/Moscow');
  // Получаем текущее время
  $currentTime = date("H:i:s");

  // Выводим время на страницу
  echo json_encode(['time' => "$currentTime"]);
?>
