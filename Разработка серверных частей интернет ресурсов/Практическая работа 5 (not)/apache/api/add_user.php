<?php
if (True) {
  header('Content-Type: application/json');
  $mysqli = new mysqli("db", "user", "password", "appDB");

  // Получение имени пользователя из GET-запроса
  $name = $_GET['name'];
  $surname = $_GET['surname'];

  // Проверка, не пустое ли имя
  if (empty($name)) {
    // Возврат ошибки, если имя пусто
    echo json_encode(['error' => 'Missing name parameter']);
    exit;
  }
  if (empty($surname)) {
    echo json_encode(['error' => 'Missing surname parameter']);
    exit;
  }

  $mysqli->query("INSERT INTO users (name, surname) VALUES ('$name', '$surname')");
  echo json_encode(['taks' => "DONE"]);
  $mysqli->close();
}
?>
