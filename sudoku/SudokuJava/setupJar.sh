#!/bin/bash  
t=$( cat mainClasses.txt)
javac "${t//.class}"
jar cfe SudokuApp.jar Sudoku $( < mainClasses.txt) templates.txt
