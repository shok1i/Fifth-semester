<!-- dynamic/menu.php -->
<?php
$servername = "db";
$username = "user";
$password = "password";
$database = "restaurant";

// Подключение к базе данных MySQL
$conn = new mysqli($servername, $username, $password, $database);

// Проверка подключения
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Получение данных из таблицы menu
$sql = "SELECT name, price FROM menu";
$result = $conn->query($sql);

echo "<h1>Restaurant Menu</h1>";
echo "<table border='1'>";
echo "<tr><th>Dish</th><th>Price ($)</th></tr>";

if ($result->num_rows > 0) {
    // Вывод данных для каждого блюда
    while ($row = $result->fetch_assoc()) {
        echo "<tr><td>" . $row["name"] . "</td><td>" . $row["price"] . "</td></tr>";
    }
} else {
    echo "<tr><td colspan='2'>No dishes available</td></tr>";
}
echo "</table>";

$conn->close();
?>
