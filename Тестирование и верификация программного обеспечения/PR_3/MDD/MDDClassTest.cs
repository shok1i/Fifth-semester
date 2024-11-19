using NUnit.Framework;
using TechTalk.SpecFlow;
using PR_3;  // Убедитесь, что у вас есть правильное пространство имен для Parser

[Binding]
public class GameQuestionsSteps
{
    private Parser parser;
    private int loadedQuestionsCount;
    private static string path = "C:\\Users\\shilo\\Documents\\Fifth-semester\\Тестирование и верификация программного обеспечения\\PR_3\\TDD\\Test.json";

    [Given(@"существует JSON файл с вопросами")]
    public void GivenExistsJsonFileWithQuestions()
    {
        parser = new Parser();
    }

    [When(@"я загружаю вопросы")]
    public void WhenILoadTheQuestions()
    {
        parser.getQuestionsFromJson(path);
        loadedQuestionsCount = parser.questions.Count;
    }

    [Then(@"должно быть загружено (.*) вопросов")]
    public void ThenShouldBeLoadedQuestions(int expectedCount)
    {
        Assert.That(loadedQuestionsCount, Is.EqualTo(expectedCount), 
            $"Должно быть загружено {expectedCount} вопросов");
    }

    [Then(@"правильный ответ для первого вопроса под номером (.*)")]
    public void ThenFirstQuestionCorrectAnswerShouldBe(int correctAnswerIndex)
    {
        Assert.That(parser.questions[0].correctAnswerIndex, Is.EqualTo(correctAnswerIndex - 1),
            "Правильный ответ для первого вопроса должен быть под номером 2");
    }
}