<!-- dynamic/menu.php -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Our Restaurant</title>
    <link href='https://fonts.googleapis.com/css?family=JetBrains Mono' rel='stylesheet'>
</head>
<body>

<div class="navbar">
    <a href="index.html" class="logo">Restourant</a>

    <div class="menu">
        <a href="about.html">About</a>
        <a href="http://localhost:8080/menu.php">Dishes</a>
        <a href="http://localhost:8080/coffee.php">Сoffee</a>
    </div>
</div>

<div>
    <h1>Это динамика</h1>
    <h2>И это вторая страница</h2>
    <p>Много полезной и интересной информации</p>
    <p>И тут тоже</p>
</div>

</body>

<style>
    /* Стили для навигационного бара */
    .navbar {
        background-color: #333;
        overflow: hidden;
    }

    .navbar a {
        float: left;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 20px;
        text-decoration: none;
    }

    .navbar a:hover {
        background-color: #575757;
    }

    /* Стили для названия */
    .navbar .logo {
        font-size: 24px;
        font-weight: bold;
        padding: 10px 20px;
    }

    .navbar .menu {
        float: right;
    }
</style>

<!-- dynamic/menu.php -->
<?php
$servername = "db";
$username = "admin";
$password = "123123";
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
echo "<tr><th>Dish</th><th>Price</th></tr>";

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
