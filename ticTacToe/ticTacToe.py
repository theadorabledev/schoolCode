import random
    
    
    #AllBoards = {} # this is a dictionary with key = a layout, and value = its corresponding BoardNode
    
class BoardNode:
    winners = [[0, 1, 2], [3, 4, 5], [6, 7, 8], [0, 3, 6], [1, 4, 7], [2, 5, 8], [0, 4, 8], [2, 4, 6]]    
    def __init__(self,layout):
        self.grid = layout
        self.endState = "d" 
        self.parents = set() 
        self.children = set() 
        if not self.isDraw() and not self.isWinner():
            for pos in xrange(9):
                if self.grid[pos] == "_":
                    if self.grid.count("X") - self.grid.count("O") == 0: #X's turn or not
                        self.children.add(self.grid[:pos] + "X" +self.grid[pos + 1:])
                    else:
                        self.children.add(self.grid[:pos] + "O" +self.grid[pos + 1:])
            self.endState = None #Move was made   
        for pos in xrange(9):
            self.parents.add(self.grid[:pos] + "_" +self.grid[pos + 1:])
        if self.endState and self.isWinner():
            self.endState = self.isWinner()            
    def display(self):
        """ Prints the grid. """
        print self.grid[0:3], "\n", self.grid[3:6], "\n", self.grid[6:9], "\n"
    def isWinner(self):
        """ Returns if grid is a current win and for who. """
        for w in self.winners:
            if self.grid[w[0]] == self.grid[w[1]] == self.grid[w[2]] != "_":
                return self.grid[w[0]]
        return False
    def isDraw(self):
        """ Returns if grid is a current draw. """
        return self.grid.count("_") == 0 and not self.isWinner()
def createAllBoards(layout="_________"):
    """ Recursively create children of boards. """
    if layout not in allBoards:
        allBoards[layout] = BoardNode(layout=layout)
        for child in allBoards[layout].children:
            createAllBoards(child)
def main():
    global allBoards 
    allBoards = {}

    createAllBoards()
    print "Count Boards: ", len(allBoards)
    allChildren = [child for board in allBoards for child in allBoards[board].children]
    print "Count of all children: ", len(allChildren)
    print "Count of all children (no duplicates): ", len(set(allChildren))
    allOutcomes = [allBoards[board].endState for board in allBoards]
    print "Count of wins for X: ", allOutcomes.count("X") 
    print "Count of wins for O:", allOutcomes.count("O") 
    print "Count of draws: ", allOutcomes.count("d")
    print "Count of boring boards: ", allOutcomes.count(None)

main()