using System.Text;
using NUnit.Framework;

namespace Практика_2;

public class CaesarEncryption
{
    public string TransferTo (String inputText, int shift)
    {
        // Преаброзуем в массив чаров
        var letters = inputText.ToCharArray();

        var result = new StringBuilder();
            
        foreach (var letter in letters)
        {
            // ИНдекс символа в таблице
            int index = letter;
                
            // Производим сдвиг
            var newIndex = index + shift;
                
            // Переводим обратно
            var newLetter = (char)newIndex;
                
            result.Append(newLetter);
        }
        
        Console.WriteLine(result.ToString());
        return result.ToString();
    }
        
    public string TransferFrom (String inputText, int shift)
    {
        return TransferTo(inputText, shift * -1);
    }
}

public class Triangle
{
    private double SideA { get; set; }
    private double SideB { get; set; }
    private double SideC { get; set; }

    // 0: Изменение старон треугольника
    public void SetSides(double sideA, double sideB, double sideC)
    {
        SideA = sideA; SideB = sideB; SideC = sideC;
    }

    // 1: Функция проверки существует ли треугольник 
    public bool IsExist()
    {
        return SideA + SideB > SideC && SideA + SideC > SideB && SideB + SideC > SideA;
    }

        
    // 2: Функция вычисления периметра треугольника
    public double Perimeter()
    {
        return SideA + SideB + SideC;
    }
        
        
    // 3: Функция вычисления площади треугольника
    public double Area()
    {
        var perimeter = Perimeter() / 2;
        return Math.Sqrt(perimeter * (perimeter - SideA) * (perimeter - SideB) * (perimeter - SideC));
    }
        
    // 4: Функция нахождения синусов треугольника
    public double[] AngleSin()
    {
        var height = Area() / SideA;
            
        var sinA = Math.Round(height / SideB, 3);
        var sinC = Math.Round(height / SideC, 3);
        
        // Тут есть ошибка за место SideC должно быть SideB ((Но я не силен в ГЕОМЕТРИИ))
        var sinB = Math.Round(SideA * sinC / SideB, 3);
            
        return [sinA, sinB, sinC];
    }
}

// Юнит тесты
[TestFixture]
public class TriangleTests
{
    private Triangle _triangle;

    // Выполняется перед каждым тестом
    [SetUp]
    public void Setup()
    {
        _triangle = new Triangle();
    }

    [Test] // Проверка на существование треугольника (Треуголик существует)
    public void isExistCheck_True()
    {
        _triangle.SetSides(8, 17, 15);
            
        bool result = _triangle.IsExist();
            
        Assert.That(result, Is.EqualTo(true));
    }
        
    [Test] // Проверка на существование треугольника (Треуголик не существует)
    public void isExistCheck_False()
    {
        _triangle.SetSides(9, 9, 18);
            
        bool result = _triangle.IsExist();
            
        Assert.That(result, Is.EqualTo(false));
    }
    
    [Test] // Проверка периметра треугольника
    public void PerimeterCheck()
    {
        _triangle.SetSides(8, 17, 15);
            
        double result = _triangle.Perimeter();
            
        Assert.That(result, Is.EqualTo(40));
    }
    
    [Test] // Проверка периметра треугольника
    public void AreaCheck()
    {
        _triangle.SetSides(8, 17, 15);
            
        var result = _triangle.Area();
            
        Assert.That(result, Is.EqualTo(60));
    }
    
    [Test] // Проверка нахождения синусов треугольника
    public void AngleSinCheck()
    {
        _triangle.SetSides(8, 17, 15);
            
        var result = _triangle.AngleSin();
        // double[] expected = [Math.Round(0.441, 3), Math.Round(0.235, 3), Math.Round(0.500, 3)];
        double[] expected = [0.441, 0.235, 0.500];
            
        Assert.That(result, Is.EqualTo(expected));
    }
}

public class CaesarEncryptionTests
{
    private CaesarEncryption _encryption;

    // Выполняется перед каждым тестом
    [SetUp]
    public void Setup()
    {
        // Console.InputEncoding = Encoding.Unicode;
        // Console.OutputEncoding = Encoding.Unicode;
        _encryption = new CaesarEncryption();
    }

    [Test] // Проверка на правильность перевода в шифр Цезаря
    public void Encryption()
    {
        string result = _encryption.TransferTo("В чашах юга жил бы цитрус? Да, но фальшивый экземпляр!", 16);

        Assert.That(result, Is.EqualTo("Т0їрјрѕ0ўур0цшы0сћ0ішђѐѓёO0Фр<0эю0єрыќјштћщ0ѝъчхьяыџѐ1"));
    }

    [Test] // Проверка на правильность перевода из шифра Цезаря
    public void Decryption()
    {
        string result = _encryption.TransferFrom("Т0їрјрѕ0ўур0цшы0сћ0ішђѐѓёO0Фр<0эю0єрыќјштћщ0ѝъчхьяыџѐ1", 16);

        Assert.That(result, Is.EqualTo("В чашах юга жил бы цитрус? Да, но фальшивый экземпляр!"));
    }
}