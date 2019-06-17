# N-Body Simulation Using Barnes-Hut Trees, 8 Points
## About
A program that models bodies in space. 
There are many naivea and brute-forced solutions that can be used to run the model. 
This program uses Barnes-hut Trees, which recursively split the model into quadrants if occupied by bodies. 
Far off clusters of bodies are modelled as one to save on calculation costs. 
For example, a solar system is affected by the galaxy as opposed to each body in it, calculating individual changes for each planet and moon would be silly.
The running time is thus reduced from N^3 to N log N.

## Parts 

### StdDraw.java
A provided graphics library.

### Body.java
This is a class that represents a body in space. The building block of the model. 

It stores basic information such as mass, position, and velocity. It contains methods to affect these.

### Quadrant.java
A basic class that helps recursively split the model. It serves mainly to define boundaries.

Each quadrant is potentially made up of 4 quadrants if further splitting is required.

### BarnesHutTree.java
A class to assist with the recursion of the quadrants, storing bodies and inserting them.

### NBodySimulation.java
The class that contains the entire simulation, performing the necessary calculations and displaying the bodies accordingly.

#### How it works
1) The model object accepts four input paramenters. A double "radius" that defines the maximum radius of the universe. An array of Body objects. A double "time" representing how long the model should run and a double "dt" representing the change in time each iteration of the simulation.
2) Then, until the time runs out, the program runs the simulation and then displays the points. The bodies are scaled logarithmically because of the massive differences in the size of astronomical bodies.

## Running
1) Compile the classes.

``` javac StdDraw.java Body.java Quadrant.java Barnes-HutTree.java NBodySimulation.java ```

2) Run the program.
157788000.0 25000.0 < planets.txt

``` java NBodySimulation <time> <delta-time> < <Model text file> ```

ex : `java NBodySimulation 157788000.0 25000.0 < resouces/galaxy.txt` will simulate a galaxy!

### Output Format
The program outputs the data for each body after the simulation has run its course.

The visualization can be terminated by terminating the program on the command line or by simply x-ing out.
