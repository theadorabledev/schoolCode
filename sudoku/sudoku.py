#! /usr/bin/python
import sys
import time
""" 
sudoku.py  <input-filename> <output-filename>  <name-of-sudoku-board> 

If no output file is specified, the program prints the solved puzzle.

<name-of-sudoku-board> is for when there are multiple puzzles in the file.

"""
class SudokoPuzzle:
    """ A class for sudoku puzzles. """
    getGroup = staticmethod(lambda x, y: ((x + 3)/3) + ((y/3) * 3))
    
    
    def __init__(self, fileName,name=None, naive=False):
        self.data = []
        self.solved = False
        self.possibleValues = {(x, y):set(range(1, 10)) for x in xrange(9) for y in xrange(9)}
        self.impossibleValues = {coor:set() for coor in self.possibleValues}
        self.knownValues = set()
        self.knownCount = 0
        self.savedStates = []
        self.root = None
        self.backTracks = 0
        self.name = name
        lines = open(fileName).readlines()
        if name:
            for i in xrange(len(lines) - 1):
                if name in lines[i]:
                    subLines = [lines[i + x + 1] for x in xrange(9)]
                    for line in subLines:
                        self.data.append([int(i) for i in line.strip().replace("_", "0").split(",")])
                    break
        else:
            for line in lines:
                self.data.append([int(i) for i in line.strip().replace("_", "0").split(",")])
#        self.printData()
        self.initValues()
        self.rows = {row:set(self.data[row]) - {0} for row in xrange(9)}
        self.columns = {col:set(row[col] for row in self.data) - {0} for col in xrange(9)}
        self.groups = {group:set() for group in xrange(1, 10)}
        self.initGroups()
        while not self.solved:
            self.solve()
        self.spam = 0
        #random
    def solve(self):
        """ Solves the sudoko until their is a solution, contradiction, or dead end. """
        #self.root = root
        self.solved = True
        while self.knownCount < 81:
            count = self.knownCount
            for coord in self.possibleValues:
                self.possibleValues[coord] -= (self.getNeighbors(coord) | self.impossibleValues[coord]  | {0})               
                if len(self.possibleValues[coord]) == 1: # One spot solved for
                    self.setValue(coord)
                    self.solved = False
                elif not self.possibleValues[coord]: # Empty set = contradiction
                    x, y = coord
                    rows, cols, groups = self.rows[y], self.columns[x], self.groups[self.getGroup(x, y)]
                    self.solved = False
                    #self.printData()
                    self.reloadState()
                    break
            if count == self.knownCount: # dead end
                self.saveState()
                self.guessBest()
                
                break
    def initValues(self):
        """ Initializes the values."""
        for x in xrange(9):
            for y in xrange(9):
                if self.data[y][x] > 0:
                    self.possibleValues[(x, y)] = {self.data[y][x]}
                    self.knownCount += 1

    def printData(self):
        """ Prints the data. """
        for line in self.data:
            print " ".join([str(i) for i in line])  
        #print "-" * 20

    def initGroups(self):
        """ Initializes the groups(boxes). """
        for x in xrange(9):
            for y in xrange(9):
                if self.data[y][x] > 0:
                    self.groups[self.getGroup(x, y)].add(self.data[y][x])
    def getNeighbors(self, coord):
        """ Returns the neigbors of a coordinate. """
        x, y = coord
        return (self.rows[y] | self.columns[x] | self.groups[self.getGroup(x, y)]) - {self.data[y][x]}
    def setValue(self, coord, guess=False):
        """ Updates the value of a coordinate. """
        x, y = coord
        val = sum(self.possibleValues[coord])
        if guess:
            val = (self.possibleValues[coord] - self.impossibleValues[coord]).pop()
            self.possibleValues[coord].remove(val)
            self.possibleValues[coord] = {val}
        if self.data[y][x] != val:
            self.rows[y].add(val)
            self.columns[x].add(val)
            self.groups[self.getGroup(x, y)].add(val)
            self.data[y][x] = val
            self.knownCount += 1
    def s_deepcopy(self):
        data = {
            "columns":{},
            "rows":{},
            "groups":{},
            "data":[],
            "impossibleValues":{},
            "possibleValues":{},
            "knownCount":self.knownCount,
            "backTracks":self.backTracks,
            "knownValues":self.knownValues.copy(),
            "name":self.name,
            "root":self.root,
            "savedStates":None,
            "solved":self.solved
            }
        #data["knownValues"] = copy(self.knownValues)
        for i in xrange(9):
            data["columns"][i] = self.columns[i].copy()
            data["rows"][i] = self.rows[i].copy()
            data["groups"][i + 1] = self.groups[i + 1].copy()
            data["data"].append(self.data[i][:])
        for i in self.impossibleValues:
            data["impossibleValues"][i] = self.impossibleValues[i].copy()
            data["possibleValues"][i] = self.possibleValues[i].copy()
        return data   
    def saveState(self):
        """ Saves the current board state. """
        state=self.s_deepcopy()
        #state = deepcopy(self.__dict__)
        #state["impossibleValues"] = self.impossibleValues
        state["savedStates"] = None
        self.savedStates.append(state)
        if not self.root:
            for coord in self.possibleValues:
                if len(self.possibleValues[coord]) == 1:
                    self.knownValues.add((coord, sum(self.possibleValues[coord])))
            
        #print "saved state"
        #self.printData()
    def reloadState(self):
        """ Reloads the last saved state from the stack. """
        #self.backTracks += 1
        if not self.savedStates:
            print "default"
        state = self.savedStates.pop(-1)
        if self.root:
            state["impossibleValues"][self.root[0]].add(self.root[1])
            self.impossibleValues[self.root[0]].add(self.root[1])
        c = 0
        for i in self.impossibleValues:
            c += len(self.impossibleValues[i])
        state["backTracks"] = self.backTracks + 1
        state["savedStates"] = self.savedStates
        self.__dict__ = state
        #print "Reloaded state"
        #self.printData()

    def guessBest(self):
        """ Guesses the choice with the highest probability and sets it."""
        #bestCoord = min(self.possibleValues, key=lambda k: len(self.possibleValues[k] - self.impossibleValues[k]) if len(self.possibleValues[k]  - self.impossibleValues[k]) > 1 else 10)
        z = {i:(self.possibleValues[i] - self.impossibleValues[i]) for i in self.possibleValues}
        bestCoord = min(z, key=lambda k: len(z[k]) if len(z[k]) > 1 else 10)
        c = self.possibleValues[bestCoord] - self.impossibleValues[bestCoord]
        self.setValue(bestCoord, guess=True)
        self.root = (bestCoord, self.getCoordValue(bestCoord))
        #print "guessed" , self.root
        self.possibleValues[bestCoord] = {self.getCoordValue(bestCoord)}
        #self.solve()
        #print 1
    def getCoordValue(self, coord):
        """ Gets the current value of the coord. """
        return self.data[coord[1]][coord[0]]
    def writeData(self, outFile, sep=","):
        info = self.name.replace("unsolved", "solved")
        if not outFile:
            return
        f = open(outFile, "w")
        if info:
            f.write(info + "\n")
        for row in self.data:
            f.write(sep.join([str(i) for i in row]) + "\n")
        
    
def main():
    x = time.time()
    args = sys.argv[1:]
    showTracks = "-t" in args
    s = None
    if len(args) == 1:
        s = SudokoPuzzle(args[0])
        s.printData()
    else:
        if len(args) > 2:
            name = args[2]
            s = SudokoPuzzle(args[0], name=args[2])

        elif len(args) == 2:
            s = SudokoPuzzle(args[0])
        s.printData()
        s.writeData(args[1])
    #if showTracks:
    print "BackTracks :", s.backTracks
    print "Seconds: ", time.time() - x
if __name__ == "__main__":
    main()
