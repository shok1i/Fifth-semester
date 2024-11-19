<!DOCTYPE html>
<html>
<head>
  <title>Простая динамическая страница</title>
</head>
<body>

<h1>Привет, мир!</h1>

<?php
  date_default_timezone_set('Europe/Moscow');
  // Получаем текущее время
  $currentTime = date("H:i:s");

  // Выводим время на страницу
  echo "<p>$currentTime</p>";
?>

</body>
</html>
