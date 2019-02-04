#!/bin/bash
javac -d target/bin -cp target/bin src/main/java/*.java src/main/java/pieces/*.java && java -cp target/bin Game
