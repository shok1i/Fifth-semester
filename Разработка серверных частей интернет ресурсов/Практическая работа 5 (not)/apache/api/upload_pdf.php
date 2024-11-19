<?php
    $mysqli = new mysqli("db", "user", "password", "appDB");

    if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_FILES['pdf_file'])){
        $file = $_FILES['pdf_file'];
        $upload_dir = '/var/www/html/uploads/';
        $filename = basename($file['name']);
        $file_path = $upload_dir . uniqid() . '_' . $filename;

        if ($file['error'] === UPLOAD_ERR_OK){
            if (move_uploaded_file($file['tmp_name'], $file_path)){
                $mysqli->query("INSERT INTO pdfs (filename, filepath) VALUES ('$filename', '$file_path')");
            } else {
                echo "Ошибка при загрузке файла в бд.";
            }
        } else {
            echo "Ошибка при загрузке файла на сервер.";
        }
    }
?>