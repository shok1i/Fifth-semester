<?php
    require 'menu_class.php'; // Подключение класса Menu

    // Контроллер для обработки HTTP-запросов
    $requestMethod = $_SERVER["REQUEST_METHOD"];
    $uri = explode('/', trim($_SERVER['REQUEST_URI'], '/'));
    $menu = new menu_class();

    switch ($requestMethod) {
        case 'GET':
            $response = isset($uri[2]) ? $menu->getCoffee((int)$uri[2]) : $menu->getAllCoffees();
            break;

        case 'POST':
            $name = $_GET['Name'];
            $price = $_GET['Price'];
            $response = $menu->createCoffee($name, $price);
            break;

        case 'PUT':
            $id = $_GET['id'];
            $name = $_GET['name'];
            $price = $_GET['price'];
            $response = $menu->updateCoffee($id, $name, $price);
            break;

        case 'DELETE':
            $id = (int)$uri[2];
            $response = $id ? $menu->deleteCoffee($id) : ['error' => 'Missing id for deletion'];
            break;

        default:
            http_response_code(405);
            $response = ['error' => 'Method Not Allowed'];
            break;
    }

    echo json_encode($response);