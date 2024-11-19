<?php
if (True) {
  header('Content-Type: application/json');
  $mysqli = new mysqli("db", "user", "password", "appDB");

  // Получение имени пользователя из GET-запроса
  $id = $_GET['id'];

  $name = $_GET['name'];
  $surname = $_GET['surname'];

  if(empty($id)) {
    echo json_encode(['error' => 'Missing id parameter']);
    exit;
  }
  if (empty($name)) {
    // Возврат ошибки, если имя пусто
    echo json_encode(['error' => 'Missing name parameter']);
    exit;
  }
  if (empty($surname)) {
    echo json_encode(['error' => 'Missing surname parameter']);
    exit;
  }

  $mysqli->query("UPDATE users SET name = '$name', surname = '$surname' WHERE id = $id");
  echo json_encode(['taks' => "DONE"]);
  $mysqli->close();
}
?>
