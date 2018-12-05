from sudoku import SudokuPuzzle
import sys
def main():
    s = SudokuPuzzle("Sudoku-boards.txt","name,sudokugarden_de/files/100sudoku2-en_pdf-Nr-50,unsolved")
    y = SudokuPuzzle("Sudoku-boards.txt","name,sudokugarden_de/files/100sudoku2-en_pdf-Nr-50,unsolved")
    z = SudokuPuzzle("outTemp.txt")
    y.data[0], y.data[1] = y.data[1], y.data[0]
    #y.solve()
    s.solve()
    z.solve()
    #y.printDataFile()
    s.printData()
    print "\n\n"
    z.printData()
if __name__ == "__main__":
    main()