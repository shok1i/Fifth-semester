import colorama
import unittest
from imports import *

app = QtWidgets.QApplication(sys.argv)

class Tester(unittest.TestCase):
    def test1(self):
        calc = Calculator(0,0)
        windows = Ui(calc,True)
        windows.button1.click()
        windows.button2.click()
        windows.button3.click()
        windows.button4.click()
        windows.button5.click()
        windows.button6.click()
        windows.button7.click()
        windows.button8.click()
        windows.button9.click()
        self.assertEqual(windows.line.toPlainText(), "123456789")

    def test2(self):
        calc = Calculator(0, 0)
        windows = Ui(calc, True)
        windows.line.setText("------89+81")
        windows.buttonResult.click()
        self.assertEqual(windows.line.toPlainText(), "170")
    def test3(self):
        calc = Calculator(0, 0)
        windows = Ui(calc, True)
        windows.line.setText("(2+2)*2")
        windows.buttonResult.click()
        self.assertEqual(windows.line.toPlainText(), "8")
    def test4(self):
        calc = Calculator(0, 0)
        windows = Ui(calc, True)
        windows.buttonClear.click()
        windows.button1.click()
        windows.buttonClear.click()
        windows.buttonLastResult.click()
        self.assertEqual(windows.line.toPlainText(), "0")
    def test5(self):
        calc = Calculator(0, 0)
        windows = Ui(calc, True)
        windows.buttonClear.click()
        for i in range(0,100):
            windows.buttonMinus.click()
        windows.button1.click()
        windows.buttonPlusMinus.click()
        self.assertEqual(windows.line.toPlainText(), "1")
if __name__ == "__main__":
    unittest.main()