# Small World Phenomenom, 7 Points 
A simple program that finds the Kevin Bacon number of an actor. Namely, their degree of seperation from Kevin Bacon in terms of starring in movies.

The program uses a basic breadth-first search to find the shortest path from Kevin Bacon to each actor.

## Running
1) Compile the program.

``` javac BaconNumberFinder.java ```

2) Run the program

``` java BaconNumberFinder <movie-file> ```

`<movie-file>` is a text file containing information on which actors played in which movies with who. Two such files are provided.

3) The program will then ask you to input an actor's name in the form of `Last, First`. It will then display the Bacon number of that actor and the shortest path connecting them. Pressing teh enter key on a blank input will terminate the program.
