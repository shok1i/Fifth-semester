<?php
    require 'order_class.php'; // Подключение класса Menu

    // Контроллер для обработки HTTP-запросов
    $requestMethod = $_SERVER["REQUEST_METHOD"];
    $uri = explode('/', trim($_SERVER['REQUEST_URI'], '/'));
    $order = new order_class();

    switch ($requestMethod) {
        case 'GET':
            $response = isset($uri[2]) ? $order->getOrder((int)$uri[2]) : $order->getAllOrders();
            break;

        case 'POST':
            $name = $_GET['Name'];
            $price = $_GET['Price'];
            $response = $order->createOrder($name, $price);
            break;

        case 'PUT':
            $id = $_GET['id'];
            $name = $_GET['name'];
            $price = $_GET['price'];
            $response = $order->updateOrder($id, $name, $price);
            break;

        case 'DELETE':
            $id = (int)$uri[2];
            $response = $id ? $order->deleteOrder($id) : ['error' => 'Missing id for deletion'];
            break;

        default:
            http_response_code(405);
            $response = ['error' => 'Method Not Allowed'];
            break;
    }

    echo json_encode($response);