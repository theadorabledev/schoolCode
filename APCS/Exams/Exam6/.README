# Exam 6
## Section 2 : MorseCodeTranslator
#### Bryan Monge Serrano

### About 

A program that translates English to and from Morse Code.

### Running

Compile the program.

- `javac MorseCodeTranslator.java`

Run.

- `java MorseCodeTranslator`
- You will be asked to input a string. Depending on whether the first character is a letter or not, the program will then print the translated line. When you press enter on a blank line the program will terminate.

## Section 3 : JumpingLeprechauns
#### Author : Ian Anton Norbek Williams
### About
Jumping Leprechauns is a simple n-body simulation.

Leprechauns are stored in a TreeMap with their position on the horizon as a key.

Each iteration of the simulation, the program goes through the TreeMap in order of position on the horizon, sending each leprechaun to a new random position based of its gold and current position. The Leprechaun then steals half the gold from the two nearest leprechauns or nearest leprechaun if it is on the edge.

### Components and methods

#### static class Leprechaun
A small static class which wraps the Leprechauns amount of gold and unique number id. It contains basic accessors.

##### public int stealHalf()
* Steals half the gold from the Leprechaun and returns it.

##### public void stealFrom(Leprechaun other)
* Steals the gold from leprechaun "other" and adds the amount to its own gold reserves.
	
##### toString()
* Returns the leprechaun as represented by its unique id and gold.
* Ex : "(#5, 1550050g)" for leprechaun number 5 with 1550050 gold coins.


#### public JumpingLeprechauns(int numLeprechauns)
* Creates a horizon with "numLeprechauns" leprechauns placed randomly.
#### public void iterate()
	1) Prints the current horizon.
	2) Iterates through the leprechauns once as previously defined.
	3) Print the horizon.

#### public void iterate(int rounds)
* Calls iterate() "rounds" times

#### public void print()
Prints each leprechaun in order on the horizon.

### Running

Compile the program.

* `javac JumpingLeprechauns.java`

Run.

* `java JumpingLeprechauns <number-of-leprechauns> <number-of-iterations>`
* Ex : Create a horizon with 10 leprechauns and iterate over it 15 times  `java JumpingLeprechauns 10 15`  