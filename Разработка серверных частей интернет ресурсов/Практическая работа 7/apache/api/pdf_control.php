<?php
$requestMethod = $_SERVER["REQUEST_METHOD"];
$uri = explode('/', trim($_SERVER['REQUEST_URI'], '/'));
$pdfManager = new pdf_class();

switch ($requestMethod) {
    case 'GET':
        if (isset($_GET['id'])) {
            $pdfManager->getPDF((int)$_GET['id']);
        } else {
            echo json_encode(['error' => 'Missing id for fetching PDF']);
        }
        break;

    case 'POST':
        if (isset($_FILES['pdf_file'])) {
            $response = $pdfManager->uploadPDF($_FILES['pdf_file']);
            echo json_encode($response);
        } else {
            echo json_encode(['error' => 'No file uploaded']);
        }
        break;

    default:
        http_response_code(405);
        echo json_encode(['error' => 'Method Not Allowed']);
        break;
}