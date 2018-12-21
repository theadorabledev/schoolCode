#!/bin/bash  

mkdir target/bin/nlogoClasses
mkdir target/sudoku-netlogo
javac -d target/bin @src/main/resources/mainJavaFiles.txt
jar cfe target/SudokuApp.jar Sudoku -C src/main/resources templates.txt -C target/bin .
chmod +x target/SudokuApp.jar


javac -cp src/main/resources/netlogo-6.0.4.jar;src/main/resources/scala-library.jar;target/SudokuApp.jar -d target/bin/nlogoClasses src/main/java/NetlogoJava/*.java
jar cvfm target/sudoku-netlogo/sudoku-netlogo.jar src/main/resources/manifest.txt -C target/bin/nlogoClasses . -C target/bin . -C src/main/resources templates.txt
chmod +x target/sudoku-netlogo/sudoku-netlogo.jar


$SHELL