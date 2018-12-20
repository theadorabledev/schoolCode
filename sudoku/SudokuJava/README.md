# Sudoku
A collection of java classes for dealing with Sudoku puzzles as well as a GUI.
## How to use

#### Generate
    1) Choose a difficulty.
    2) Choose a seed for the pseudorandom generation.
    3) Press 'Generate'.
    4) Enjoy!

#### Solve
    1) Press 'Solve'.
    2) Input the data into the blank board.
    3) Press solve once again.
    4) Congratulations! You now have a solved sudoku puzzle!
	
#### Load
* Press load to load a saved sudoku game (.sudoku file).   
 
#### Play

###### Selecting spot on grid
	* The spot can either be pressed or the arrow keys can be used to navigate.
	* A permanent spot can't be changed and will be skipped over when navigating.
	
###### Setting values
	* Values can be set when a piece is selected.	 
	* Values are set via keypress.
    * Type a key 1-9 to set the value. "0" and "Backspace" will clear the spot.
    * An incorrect(contradictory) value entered will result in a three second penalty, freezing the board.
	
###### Undo & Redo
	* There are buttons that do that.
	* This can also be achieved with Control-z and Control-Shift-z
	
##### Give up
* There is a button that will solve the puzzle for you, then belittle you.
	  
##### Save
* Press save during a game and choose a destination.

## General information

#### Classes used
    1) SudokuPuzzle : A class dealing with sudoku puzzles
    2) SudokuPuzzlePlayable : A subclass of SudokuPuzzle for playing as a human
    3) SudokuPuzzleGenerated : A subclass of SudokuPuzzle specifically for generation
    4) Coordinate : An ordered coordinate pair, hashable
    5) Move : A small wrapper class for holding history (Coordinate, value, last value)
    6) SudokuButton : An subclass of JButton that is a sudoku spot on the grid.
    7) Sudoku : The main GUI
       
#### History / The Sudoku Chronicles
1) I made a Sudoku solver for my school's AI class in python.
2) I then decided to rewrite it in java.
3) I further decided to hand it in as an APCS midterm project.
4) I was told the solver was perfect enough.
5) I went overboard.
6) WAY overboard.
7) Here we are now.

## Installing
1) Try to download and run the jar present in the 'target' subdirectory. Continue if error.
2) Clone the repository.
3) Execute setupJar.sh. This will surprisingly set up the .jar file.
4) Execute the jar. It will be in the 'target' subdirectory.
### WARNING!
DO NOT put jam in the jar. It may be tempting, but it will not work.

## Coming soon
* This repackaged as a netlogo extension.
## Author
* Ian Williams

## Acknowledgments
* All the wonderful CS teachers at Stuyvesant High School who called this way overboard.
     
        
