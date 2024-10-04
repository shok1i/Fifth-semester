import sys

from imports import *
import tester
# Функция старта для ui из каталога
def to_start():
    calc = Calculator(0,0)
    app = QtWidgets.QApplication(sys.argv)
    windows = Ui(calc)
    app.exec_()
#Точка входа
if __name__ == "__main__":
    to_start()