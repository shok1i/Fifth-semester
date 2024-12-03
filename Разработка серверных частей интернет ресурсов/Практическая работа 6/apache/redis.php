<?php
    session_start();
    $redis = new Redis();
    $redis->connect('redis', 6379);

    if(!isset($_SESSION['username'])){
        $_SESSION['username'] = 'Гость';
    }
    if(!isset($_SESSION['theme'])){
        $_SESSION['theme'] = 'light';
    }
    if(!isset($_SESSION['lang'])){
        $_SESSION['lang'] = 'en';
    }
    $restore_name = '';
    $username = $_SESSION['username'];
    $theme = $_SESSION['theme'];
    $lang = $_SESSION['lang'];

    if($theme == 'light'){
        $theme_css = 'light.css';
    }
    else{
        $theme_css = 'dark.css';
    }

    if ($lang == 'ru'){
        $lang_txt = 'Русский язык для пользователя $username';
    }
    else{
        $lang_txt = 'English language for user $username';
    }

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personalized Content</title>
    <link rel="stylesheet" href="<?php echo $theme_css; ?>"> <!-- Подключаем соответствующий стиль -->
</head>
<body>
    <h1><?php echo $lang_txt; ?></h1>

    <p>Выберите тему:</p>
    <a href="?theme=light">Светлая</a> | <a href="?theme=dark">Темная</a>

    <p>Выберите язык:</p>
    <a href="?lang=en">Английский</a> | <a href="?lang=ru">Русский</a>

    <form method="post" action="">
        <label for="username">Ваш логин:</label>
        <input type="text" id="username" name="username" value="<?php echo $username; ?>" required>
        <input type="submit" value="Обновить логин">
    </form>
    <form method="post" action="">
        <input type="text" id = "restore_name" name="restore_name" value="<?php echo $restore_name; ?>" required>
        <input type="submit" value="Восстановить сессию">
    </form>

    <?php
    if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['username'])) {
        $_SESSION['username'] = $_POST['username'];
        echo "<script>window.location.href = '/redis.php';</script>";
        $redis->set("session: " . $_SESSION['username'], json_encode($_SESSION));
        exit;
    }

    if (isset($_GET['theme'])) {
        $_SESSION['theme'] = $_GET['theme'];
        echo "<script>window.location.href = '/redis.php';</script>";
        $redis->set("session: " . $_SESSION['username'], json_encode($_SESSION));
        exit;
    }

    if (isset($_GET['lang'])) {
        $_SESSION['lang'] = $_GET['lang'];
        echo "<script>window.location.href = '/redis.php';</script>";
        $redis->set("session: " . $_SESSION['username'], json_encode($_SESSION));
        exit;
    }
    if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['restore_name'])){
        $_SESSION = array_merge($_SESSION, json_decode($redis->get("session: " . $_POST['restore_name']), true));
        echo "<script>window.location.href = '/redis.php';</script>";
        exit;
    }
    ?>

</body>
</html>