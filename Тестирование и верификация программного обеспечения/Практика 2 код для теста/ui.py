from imports import *


#Класс интерфейса
class Ui(QtWidgets.QDialog):
    def __init__(self, Calculator, testMode = False):
        #Этап подключения файла интерфейса
        super(Ui, self).__init__()
        uic.loadUi('resources/ui/untitled.ui', self)
        self.line = self.findChild(QtWidgets.QTextEdit, "resultTextEdit")
        self.line.setText("")
        #Этап соединения кнопок с функционалом
        button1 = self.findChild(QtWidgets.QPushButton, "button1")
        button1.clicked.connect(lambda: self.addNumber(button1.objectName()[-1]))
        button2 = self.findChild(QtWidgets.QPushButton, "button2")
        button2.clicked.connect(lambda: self.addNumber(button2.objectName()[-1]))
        button3 = self.findChild(QtWidgets.QPushButton, "button3")
        button3.clicked.connect(lambda: self.addNumber(button3.objectName()[-1]))
        button4 = self.findChild(QtWidgets.QPushButton, "button4")
        button4.clicked.connect(lambda: self.addNumber(button4.objectName()[-1]))
        button5 = self.findChild(QtWidgets.QPushButton, "button5")
        button5.clicked.connect(lambda: self.addNumber(button5.objectName()[-1]))
        button6 = self.findChild(QtWidgets.QPushButton, "button6")
        button6.clicked.connect(lambda: self.addNumber(button6.objectName()[-1]))
        button7 = self.findChild(QtWidgets.QPushButton, "button7")
        button7.clicked.connect(lambda: self.addNumber(button7.objectName()[-1]))
        button8 = self.findChild(QtWidgets.QPushButton, "button8")
        button8.clicked.connect(lambda: self.addNumber(button8.objectName()[-1]))
        button9 = self.findChild(QtWidgets.QPushButton, "button9")
        button9.clicked.connect(lambda: self.addNumber(button9.objectName()[-1]))
        button0 = self.findChild(QtWidgets.QPushButton, "button0")
        button0.clicked.connect(lambda: self.addNumber(button0.objectName()[-1]))

        buttonFloat = self.findChild(QtWidgets.QPushButton, "buttonFloat")
        buttonFloat.clicked.connect(lambda: self.addNumber(buttonFloat.text()))
        buttonMod = self.findChild(QtWidgets.QPushButton, "buttonMod")
        buttonMod.clicked.connect(lambda: self.addNumber(buttonMod.text()))
        buttonDiv = self.findChild(QtWidgets.QPushButton, "buttonDiv")
        buttonDiv.clicked.connect(lambda: self.addNumber(buttonDiv.text()))
        buttonMulti = self.findChild(QtWidgets.QPushButton, "buttonMulti")
        buttonMulti.clicked.connect(lambda: self.addNumber(buttonMulti.text()))
        buttonPlus = self.findChild(QtWidgets.QPushButton, "buttonPlus")
        buttonPlus.clicked.connect(lambda: self.addNumber(buttonPlus.text()))
        buttonMinus = self.findChild(QtWidgets.QPushButton, "buttonMinus")
        buttonMinus.clicked.connect(lambda: self.addNumber(buttonMinus.text()))

        buttonClear = self.findChild(QtWidgets.QPushButton, "buttonClear")
        buttonClear.clicked.connect(lambda: self.clearLine(Calculator))
        buttonLastResult = self.findChild(QtWidgets.QPushButton, "buttonLastResult")
        buttonLastResult.clicked.connect(lambda: self.showLastResult(Calculator))
        buttonResult = self.findChild(QtWidgets.QPushButton, "buttonResult")
        buttonResult.clicked.connect(lambda: self.calc(Calculator))
        buttonPlusMinus = self.findChild(QtWidgets.QPushButton, "buttonPlusMinus")
        buttonPlusMinus.clicked.connect(lambda: self.plusMinus())

        #Оказывается интерфейс по умолчанию скрытый (зачем???)
        if not testMode:
            self.show()
    def calc(self, calculator):
        calculator.calculate(self.line.toPlainText())
        self.line.setText(str(calculator.getCurrentResult()))

    def plusMinus(self):
        if str(self.line.toPlainText()).isnumeric():
            self.line.setText("-" + self.line.toPlainText())
        elif str(self.line.toPlainText())[0] == '-':
            self.line.setText(str(self.line.toPlainText()).replace("-",""))
    def clearLine(self, calculator):
        self.line.setText("")

    def showLastResult(self, calculator):
        self.line.setText(str(calculator.getLastResult()))

    def addNumber(self, number):
        if self.line.toPlainText() == "0" and number == "0":
            return
        if "." in self.line.toPlainText() and number == ".":
            return
        if "*" in self.line.toPlainText() and number == "*":
            return
        if "/" in self.line.toPlainText() and number == "/":
            return
        if "+" in self.line.toPlainText() and number == "+":
            return
        if "%" in self.line.toPlainText() and number == "%":
            return
        else:
            self.line.setText(self.line.toPlainText() + number)
