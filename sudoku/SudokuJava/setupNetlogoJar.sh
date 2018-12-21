#!/bin/bash  

mkdir target/bin/nlogoClasses
mkdir target/sudoku-netlogo
javac -cp src/main/resources/netlogo-6.0.4.jar;src/main/resources/scala-library.jar -d target/bin/nlogoClasses src/main/java/NetlogoJava/*.java
jar cvfm target/sudoku-netlogo/sudoku-netlogo.jar src/main/resources/manifest.txt -C target/bin/nlogoClasses .
chmod +x target/sudoku-netlogo/sudoku-netlogo.jar
$SHELL