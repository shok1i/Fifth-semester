<html lang="en">
<head>
    <title>Server Info Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .section {
            margin-bottom: 20px;
        }
        h2 {
            color: #2c3e50;
        }
        pre {
            background-color: #ecf0f1;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h1>Информационно-административная страница сервера</h1>

    <!-- Секция команды ls -->
    <div class="section">
        <h2>Содержимое текущей директории (команда ls)</h2>
        <pre><?php echo shell_exec('ls -la'); ?></pre>
    </div>

    <!-- Секция команды ps -->
    <div class="section">
        <h2>Процессы сервера (команда ps)</h2>
        <pre><?php echo shell_exec('ps aux'); ?></pre>
    </div>

    <!-- Секция команды whoami -->
    <div class="section">
        <h2>Текущий пользователь (команда whoami)</h2>
        <pre><?php echo shell_exec('whoami'); ?></pre>
    </div>

    <!-- Секция команды id -->
    <div class="section">
        <h2>Информация о пользователе (команда id)</h2>
        <pre><?php echo shell_exec('id'); ?></pre>
    </div>

</body>
</html>