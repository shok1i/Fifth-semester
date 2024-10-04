<?php
class Drawer {
    public function drawSvg($num) {
        // Пример логики для рисования фигуры в зависимости от числа $num
        switch ($num) {
            case 1:
                // Круг
                return '<svg width="256" height="256"><circle cx="128" cy="128" r="64" stroke="black" stroke-width="3" fill="red" /></svg>';
            case 2:
                // Квадрат
                return '<svg width="256" height="256"><rect width="256" height="256" style="fill:blue;" /></svg>';
            case 3:
                // Треугольник
                return '<svg width="256" height="256"><polygon points="100,30 180,170 20,170" style="fill:green;" /></svg>';
            default:
                return '<svg><text x="10" y="20" style="fill:red;">Unknown Shape</text></svg>';
        }
    }
}
?>