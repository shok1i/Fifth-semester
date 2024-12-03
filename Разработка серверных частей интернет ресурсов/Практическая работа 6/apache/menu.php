<html lang="en">
<head>
<title>Hello world page</title>
    <link rel="stylesheet" href="style.css" type="text/css"/>
</head>
<body>
<h1>Таблица продуктами нашей кофейни</h1>
<table>
    <tr><th>Id</th><th>Наименование</th><th>Цена ($)</th></tr>
<?php
$mysqli = new mysqli("db", "user", "password", "appDB");
$result = $mysqli->query("SELECT * FROM Menu");
foreach ($result as $row){
    echo "<tr><td>{$row['id']}</td><td>{$row['name']}</td><td>{$row['price']}</td></tr>";
}
?>
</table>
</body>
</html>
