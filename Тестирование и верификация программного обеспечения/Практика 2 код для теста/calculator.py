class Calculator:
    def __init__(self, current, last):
        self.__lastResult = last
        self.__currentResult = current

    def setLastResult(self, number):
        self.__lastResult = number

    def getLastResult(self):
        return self.__lastResult

    def calculate(self, task):
        self.__currentResult = eval(task)
        self.__lastResult = self.__currentResult
    def getCurrentResult(self):
        return self.__currentResult
