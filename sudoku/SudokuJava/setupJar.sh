#!/bin/bash  
#t=$( cat mainClasses.txt)
#echo "${t//.class/.java}"
javac -verbose @mainJavaFiles.txt
jar cfe SudokuApp.jar Sudoku @mainClasses.txt templates.txt
chmod +x SudokuApp.jar
