<?php
require_once __DIR__ . '/vendor/autoload.php';

use Faker\Factory;

function Generate()
{
    $faker = Factory::create();
    $data = [];

    // Генерация 50 фикстур
    for ($i = 0; $i < 50; $i++) {
        $data[] = [
            'name' => $faker->name(),
            'email' => $faker->email(),
            'age' => $faker->numberBetween(18, 70),
            'country' => $faker->country(),
            'balance' => $faker->randomFloat(2, 100, 10000)
        ];
    }

    // Сохраняем данные в файл
    file_put_contents('fixtures.json', json_encode($data));
    echo "Фикстуры успешно созданы и сохранены.\n";
}

function GenerateGraph()
{
    $data = json_decode(file_get_contents('fixtures.json'), true);

    $balances = array_map(function ($item) {
        return $item['balance'];
    }, $data);

    $width = 800;
    $height = 600;
    $image = imagecreate($width, $height);

    $backgroundColor = imagecolorallocate($image, 255, 255, 255);
    $barColor = imagecolorallocate($image, 0, 102, 204);
    $textColor = imagecolorallocate($image, 0, 0, 0);

    imagefill($image, 0, 0, $backgroundColor);

    $barWidth = 15;
    $spacing = 20;
    $maxHeight = max($balances); // Максимальная высота столбика (на основе максимального значения)

    for ($i = 0; $i < count($balances); $i++) {
        $barHeight = ($balances[$i] / $maxHeight) * ($height - 100);
        imagefilledrectangle(
            $image,
            (int)($i * ($barWidth + $spacing) + 50), // Позиция X
            (int)($height - 50 - $barHeight), // Позиция Y (отступ снизу)
            (int)($i * ($barWidth + $spacing) + $barWidth + 50), // Позиция X + ширина
            (int)($height - 50), // Позиция Y + высота столбика
            $barColor
        );
    }

    $fontPath = './arial.ttf';
    $fontSize = 40;
    $text = "Shilo Yuriu";
    $textBox = imagettfbbox($fontSize, 0, $fontPath, $text); // Получаем размер текста
    $textWidth = $textBox[2] - $textBox[0];
    $textHeight = $textBox[1] - $textBox[7];
    $x = ($width - $textWidth) / 2; // Центрируем по X
    $y = ($height - $textHeight) / 2; // Центрируем по Y

    imagettftext($image, $fontSize, 0, $x, $y, $textColor, $fontPath, $text);

    imagepng($image, 'graph_with_watermark.png');
    imagedestroy($image);

    echo "График с водяным знаком успешно создан.\n";
}

Generate();
GenerateGraph();

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Статистика</title>
</head>
<body>
<h1>Графики статистики</h1>
<div>
    <!-- Отображаем график с водяным знаком -->
    <img src="graph_with_watermark.png" alt="График с водяным знаком">
</div>
</body>
</html>
