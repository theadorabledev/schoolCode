#!/bin/bash  
#t=$( cat mainClasses.txt)
#echo "${t//.class/.java}"
javac -d target/bin @src/main/resources/mainJavaFiles.txt
jar cfe target/SudokuApp.jar Sudoku -C src/main/resources templates.txt -C target/bin .
chmod +x target/SudokuApp.jar
#C:\Program Files\NetLogo 6.0.4\app\netlogo-6.0.4.jar
#mkdir netlogoClasses
#javac -classpath "C:\Program Files\NetLogo 6.0.4\app\netlogo-6.0.4.jar" -d netlogoClasses @mainJavaFiles.txt SudokuNetlogoExtension.java
#jar cvfm sudokuAppNetlogo.jar manifest.txt -C netlogoClasses

#$SHELL