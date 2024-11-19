using NUnit.Framework;
using PR_3; // Подключите пространство имен вашего основного проекта

[TestFixture]
public class TDDClass
{
    private static string path = "C:\\Users\\shilo\\Documents\\Fifth-semester\\Тестирование и верификация программного обеспечения\\PR_3\\TDD\\Test.json";
    private Parser parser;

    [SetUp]
    public void Setup()
    {
        parser = new Parser();
        parser.getQuestionsFromJson(path); // Загружаем вопросы перед каждым тестом
    }

    [Test]
    public void TestParserLoads()
    {
        Assert.That(parser.questions.Count, Is.EqualTo(10), "Должно быть загружено 10 вопросов");
    }

    [Test]
    public void TestGiveAnswers()
    {
        // Проверка, что правильный ответ на первый вопрос под номером 2
        Assert.That(parser.questions[0].correctAnswerIndex, Is.EqualTo(1), "Правильный ответ под номером 2");
    }
}