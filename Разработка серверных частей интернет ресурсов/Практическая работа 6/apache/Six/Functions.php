<?php
//require_once 'vendor/autoload.php';
//require_once 'jpgraph/jpgraph.php';
//require_once 'jpgraph/jpgraph_bar.php';

use Faker\Factory;
function Generate ()
{
// Создаем генератор данных
    $faker = Factory::create();
    $data = [];

// Генерация 50 фикстур с 5 полями
    for ($i = 0; $i < 50; $i++) {
        $data[] = [
            'name' => $faker->name(),
            'email' => $faker->email(),
            'age' => $faker->numberBetween(18, 70),
            'country' => $faker->country(),
            'balance' => $faker->randomFloat(2, 100, 10000)
        ];
    }

// Сохраняем в файл для использования
    file_put_contents('fixtures.json', json_encode($data));
    echo "Фикстуры успешно созданы и сохранены.";
}

function Create () {
// Загружаем фикстуры
    $data = json_decode(file_get_contents('fixtures.json'), true);

// Пример анализа данных: распределение по возрасту
    $ages = array_column($data, 'age');
    $ageDistribution = array_count_values($ages);

// Подготовка данных для графика
    $xData = array_keys($ageDistribution);
    $yData = array_values($ageDistribution);

// Создание графика
    $graph = new Graph(600, 400);
    $graph->SetScale('textlin');

    $barplot = new BarPlot($yData);
    $graph->Add($barplot);

    $graph->xaxis->SetTickLabels($xData);
    $graph->title->Set('Распределение по возрасту');
    $graph->xaxis->title->Set('Возраст');
    $graph->yaxis->title->Set('Количество');

    $graph->Stroke('graph1.png');
}

function WaterMark () {
    $graphImage = imagecreatefrompng('graph1.png');

// Создание водяного знака
    $watermarkText = "Shilo";
    $fontSize = 12;
    $fontFile = __DIR__ . '/arial.ttf'; // Укажите путь к шрифту
    $color = imagecolorallocatealpha($graphImage, 255, 255, 255, 60);

// Добавление текста на график
    imagettftext($graphImage, $fontSize, 0, 20, 380, $color, $fontFile, $watermarkText);

// Сохранение изображения
    imagepng($graphImage, 'graph_with_watermark.png');
    imagedestroy($graphImage);
}
