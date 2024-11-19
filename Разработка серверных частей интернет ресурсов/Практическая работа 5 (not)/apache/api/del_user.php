<?php
if (True) {
  header('Content-Type: application/json');
  $mysqli = new mysqli("db", "user", "password", "appDB");

  // Получение имени пользователя из GET-запроса
  $id = $_GET['id'];

  // Проверка, не пустое ли имя
  if (empty($id)) {
    // Возврат ошибки, если имя пусто
    echo json_encode(['error' => 'Missing id parameter']);
    exit;
  }

  $mysqli->query("DELETE FROM users WHERE id = $id");
  echo json_encode(['taks' => "DONE"]);
  $mysqli->close();
}
?>
