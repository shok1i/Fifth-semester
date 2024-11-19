<?php
    $mysqli = new mysqli("db", "user", "password", "appDB");
    
    if($_SERVER['REQUEST_METHOD'] === 'GET' && isset($_GET['id'])){
        $id = $_GET['id'];
        $result = $mysqli->query("SELECT * FROM pdfs WHERE ID = $id");
        $pdf = $result->fetch_assoc();
        header('Content-Type: application/pdf');
        header('Content-Length: ' . filesize($pdf['filepath']));
        readfile($pdf['filepath']);
    }
?>