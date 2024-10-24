namespace PR_3;

using Newtonsoft.Json;

public class Question
{
    public string question { get; set; }
    public string[] answers { get; set; }
    public int correctAnswerIndex { get; set; }
}

public class Parser
{
    // Класс парсер Json файла
    public List<Question> questions { get; private set; }

    public Parser()
    {
        questions = new List<Question>();
    }

    // Метод для получения вопросов из JSON-файла
    public void getQuestionsFromJson(string filePath)
    {
        if (File.Exists(filePath))
        {
            string jsonData = File.ReadAllText(filePath);
            questions = JsonConvert.DeserializeObject<List<Question>>(jsonData);

            Console.WriteLine($"Загружено {questions.Count} вопросов.");
        }
        else
        {
            Console.WriteLine("Файл не найден.");
        }
    }
}

class RootClass
{
    static void Main(string[] args)
    {
        Parser parser = new Parser();
        parser.getQuestionsFromJson("C:\\Users\\shilo\\Documents\\Fifth-semester\\Тестирование и верификация программного обеспечения\\PR_3\\PR_3\\Game.json"); 
        foreach (Question question in parser.questions)
        {
            Console.WriteLine(question.question);
            for (int i = 0; i < question.answers.Length; i++)
                Console.WriteLine($"{i + 1}. {question.answers[i]}");

            Console.Write("Ваш ответ (введите номер варианта): ");
            int userAnswer = int.Parse(Console.ReadLine()) - 1;
            if (userAnswer == question.correctAnswerIndex)
                Console.WriteLine("Верно!\n");
            else
            {
                Console.WriteLine("Неправильно. Правильный ответ: " +
                                  $"{question.answers[question.correctAnswerIndex]}\n");
                break;  // Останавливаем игру при неправильном ответе
            }
        }
        Console.WriteLine("Игра окончена.");
    }
}