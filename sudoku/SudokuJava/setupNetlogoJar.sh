#!/bin/bash  

javac -classpath /usr/local/netlogo-6.0.1/app/netlogo-6.0.1.jar -d target/bin/nlogoClasses src/main/java/NetlogoJava/*.java
jar cvfm target/SudokuAppNetlogo.jar src/main/resources/manifest.txt -C target/bin/nlogoClasses .
