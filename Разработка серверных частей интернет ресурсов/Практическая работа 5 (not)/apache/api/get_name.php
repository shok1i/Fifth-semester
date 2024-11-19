<?php
// Файл api.php

// Проверка, является ли запрос API
if (True) {
  // Заголовок, указывающий, что мы возвращаем JSON
  header('Content-Type: application/json');

  // Подключение к базе данных
  $mysqli = new mysqli("db", "user", "password", "appDB");

  // Получение имени пользователя из GET-запроса
  $name = $_GET['name'];

  // Проверка, не пустое ли имя
  if (empty($name)) {
    // Возврат ошибки, если имя пусто
    echo json_encode(['error' => 'Missing name parameter']);
    exit;
  }

  // Поиск пользователя по имени
  $result = $mysqli->query("SELECT ID FROM users WHERE name = '$name'");

  // Проверка, найден ли пользователь
  if ($result->num_rows > 0) {
    // Получение ID пользователя
    $row = $result->fetch_assoc();
    $userId = $row['ID'];

    // Возврат ответа с ID
    echo json_encode(['id' => $userId]);
  } else {
    // Возврат ошибки, если пользователь не найден
    echo json_encode(['error' => 'User not found']);
  }

  $mysqli->close();
}
?>
