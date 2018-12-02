#! /usr/bin/python
import sys
import time

""" 
sudoku.py  <input-filename> <output-filename>  <name-of-sudoku-board> <search-for-naked>

If no output file is specified, the program prints the solved puzzle.

<name-of-sudoku-board> is for when there are multiple puzzles in the file.

<search-for-naked> is true by default, can be 1 or 0
"""
class SudokoPuzzle:
    """ A class for sudoku puzzles. """
    flatten = staticmethod(lambda l: [item for sublist in l for item in sublist])
    getGroup = staticmethod(lambda x, y: ((x + 3)/3) + ((y/3) * 3))
    rowCoords = [[(x, y) for x in xrange(9)] for y in xrange(9)]
    columnCoords = [[(x, y) for y in xrange(9)] for x in xrange(9)]
    groupCoords = [[(x, y) for x in xrange(9) for y in xrange(9) if getGroup.__func__(x, y) == z] for z in xrange(1, 10)]
    rectCoords = [((x, y), (x1, y), (x, y1), (x1, y1)) for y in xrange(9) for x in xrange(9) for y1 in xrange(y + 1, 9) for x1 in xrange(x + 1, 9)]
    # a b ---> (a, c, b, d)
    # c d
    def __init__(self, fileName, name=None, naked=2):
        self.data = []
        self.solved = False
        self.possibleValues = {(x, y):set(range(1, 10)) for x in xrange(9) for y in xrange(9)}
        self.impossibleValues = {coor:set() for coor in self.possibleValues}
        self.knownCount = 0
        self.savedStates = []
        self.root = None
        self.backTracks = 0
        self.name = name
        self.naked = naked
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
        self.initValues()
        self.rows = {row:set(self.data[row]) - {0} for row in xrange(9)}
        self.columns = {col:set(row[col] for row in self.data) - {0} for col in xrange(9)}
        self.groups = {group:set() for group in xrange(1, 10)}
        self.initGroups()
        if self.naked:
            while not self.solved:
                self.solveNaked()
        else:
            while not self.solved:
                self.solve()
        self.spam = 0
    def solve(self):
        """ Solves the sudoko until their is a solution, contradiction, or dead end. """
        self.solved = True
        while self.knownCount < 81:
            count = self.knownCount
            for coord in self.possibleValues:
                self.possibleValues[coord] -= (self.getNeighbors(coord) | self.impossibleValues[coord]  | {0})               
                if len(self.possibleValues[coord]) == 1: # One spot solved for
                    self.setValue(coord)
                    self.solved = False
                elif not self.possibleValues[coord]: # Empty set = contradiction
                    self.solved = False
                    self.reloadState()
                    break                
            if count == self.knownCount:# and not nakedNums: # dead end
                self.saveState()
                self.guessBest()                
            break
    def solveNaked(self):
        """ Solves the sudoko until their is a solution, contradiction, or dead end while taking naked pairs/triplets into account. """
        self.solved = True
        while self.knownCount < 81:
            count = self.knownCount
            nakedNums = self.forbiddenFruit()
            for coord in self.possibleValues:
                self.possibleValues[coord] -= (self.getNeighbors(coord) | self.impossibleValues[coord]  | {0})               
                if len(self.possibleValues[coord]) == 1: # One spot solved for
                    self.setValue(coord)
                    self.solved = False
                elif not self.possibleValues[coord]: # Empty set = contradiction
                    self.solved = False
                    self.reloadState()
                    break                
            if count == self.knownCount and not nakedNums: # dead end
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
        """ Deepcopies instance data. """
        data = {
            "columns":{},
            "rows":{},
            "groups":{},
            "data":[],
            "impossibleValues":{},
            "possibleValues":{},
            "knownCount":self.knownCount,
            "backTracks":self.backTracks,
            "name":self.name,
            "root":self.root,
            "savedStates":None,
            "solved":self.solved,
            "naked":self.naked
            }
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
        state = self.s_deepcopy()
        state["savedStates"] = None
        self.savedStates.append(state)
            
    def reloadState(self):
        """ Reloads the last saved state from the stack. """
        if not self.savedStates:
            print "default"
        state = self.savedStates.pop(-1)
        if self.root:
            state["impossibleValues"][self.root[0]].add(self.root[1])
            self.impossibleValues[self.root[0]].add(self.root[1])

        state["backTracks"] = self.backTracks + 1
        state["savedStates"] = self.savedStates
        self.__dict__ = state
    def guessBest(self):
        """ Guesses the choice with the highest probability and sets it."""
        z = {i:(self.possibleValues[i] - self.impossibleValues[i]) for i in self.possibleValues}
        bestCoord = min(z, key=lambda k: len(z[k]) if len(z[k]) > 1 else 10)
        self.setValue(bestCoord, guess=True)
        self.root = (bestCoord, self.getCoordValue(bestCoord))
        self.possibleValues[bestCoord] = {self.getCoordValue(bestCoord)}
    def getCoordValue(self, coord):
        """ Gets the current value of the coord. """
        return self.data[coord[1]][coord[0]]
    def writeData(self, outFile, sep=","):
        """ Writes the data to a file. """
        info = self.name.replace("unsolved", "solved")
        if not outFile:
            return
        f = open(outFile, "w")
        if info:
            f.write(info + "\n")
        for row in self.data:
            f.write(sep.join([str(i) for i in row]) + "\n")    
    def hiddenSub(self, coords):
        """ Deals with repetive subproblems for hiddden groups. Not as fun a name."""
        change = False
        nakedGroups = [[self.possibleValues[coord] for coord in group] for group in coords]
        for i in xrange(9):
            for x in xrange(9):
                curSet = nakedGroups[i][x]
                # Create possible super-sets of values
                possibleGroups = {frozenset(curSet)}
                if 1 < len(curSet) <= 3:
                    for p in xrange(1, 10):
                        possibleGroups.add(frozenset(curSet | {p}))
                        if len(curSet) == 2:
                            for q in xrange(1, 10):
                                possibleGroups.add(frozenset(curSet | {p, q}))    
                for pGroup in possibleGroups: #For each possible supergroup
                    groupCount = sum([len(group) > 1 and group <= pGroup for group in nakedGroups[i]]) # get the count of elements with subset possible values
                    if groupCount == len(pGroup): # Make sure 2 elements in pair, 3 in triple, 4 in qaud
                        for coord in coords[i]:
                            if not (self.possibleValues[coord] <= pGroup) and not (pGroup <= self.impossibleValues[coord]):
                                self.impossibleValues[coord] |= pGroup     
                                change = True
								
        return change
    def nakedSub(self, coords):
        """ Deals with repetive subproblems for naked pairs. Also serves as reminder to read code out loud. """
        change = False
        nakedGroups = [[self.possibleValues[coord] for coord in group] for group in coords]
        for i in xrange(9):
            for x in xrange(9):
                groupCount = nakedGroups[i].count(nakedGroups[i][x])
                if groupCount > 1 and groupCount == len(nakedGroups[i][x]):
                    for coord in coords[i]:
                        if self.possibleValues[coord] != nakedGroups[i][x] and not (nakedGroups[i][x] <= self.impossibleValues[coord]):
                            self.impossibleValues[coord] |= nakedGroups[i][x]
                            change = True
        return change
    def forbiddenFruit(self):
        """ Deals with all hidden possibilities. Pairs to Quads. 
        
        Biblical references make code interesting (hidden pairs --> hidden pears --> forbidden fruit)"""
        #self.xWing()
        if self.naked == 1:
            return self.nakedSub(self.rowCoords) + self.nakedSub(self.columnCoords) + self.nakedSub(self.groupCoords)
        return self.hiddenSub(self.rowCoords) + self.hiddenSub(self.columnCoords) + self.hiddenSub(self.groupCoords) #+ self.xWing()
    def xWing(self):
        """ Finds x-wing patterns in the puzzle. Apparently sudoku and star wars coincide. """
        change = False
        # a b ---> (a, c, b, d)
        # c d        
        nakedWing = [[self.possibleValues[coord] for coord in rect] for rect in self.rectCoords]
        for i in xrange(len(nakedWing)):
            rect = nakedWing[i]
            commonVals = rect[0] & rect[1] & rect[2] & rect[3]
            if commonVals:
                realRect = self.rectCoords[i]
                col1, row1 = realRect[0]
                col2, row2 = realRect[3] 
                nakedRow1 = self.flatten([self.possibleValues[coord] for coord in self.rowCoords[row1]])
                nakedRow2 = self.flatten([self.possibleValues[coord] for coord in self.rowCoords[row2]])
                nakedColumn1 = self.flatten([self.possibleValues[coord] for coord in self.columnCoords[col1]])
                nakedColumn2 = self.flatten([self.possibleValues[coord] for coord in self.columnCoords[col2]])                
                for val in commonVals:
                    if nakedRow1.count(val) == 2 and nakedRow2.count(val) == 2:
                        for coord in self.rowCoords[row1] + self.rowCoords[row2]:
                            if coord not in realRect and not ({val} <= self.impossibleValues[coord]):
                                self.possibleValues[coord] |= {val}
                                change = True
                    if nakedColumn1.count(val) == 2 and nakedColumn2.count(val) == 2:
                        for coord in self.columnCoords[col1] + self.columnCoords[col2]:
                            if coord not in realRect and not ({val} <= self.impossibleValues[coord]):
                                self.possibleValues[coord] |= {val}
                                change = True                                
        return change
def main():
    """ Main method that deals with command line arguments. """
    x = time.time()
    args = sys.argv[1:]
    s = None
    if len(args) == 1:
        s = SudokoPuzzle(args[0])
        s.printData()
    else:
        if len(args) > 2:
            #isNaked = 3
            #isNaked = int(args[3])
            s = SudokoPuzzle(args[0], name=args[2])#, naked=isNaked)

        elif len(args) == 2:
            s = SudokoPuzzle(args[0])
        s.printData()
        s.writeData(args[1])
    print "BackTracks :", s.backTracks
    print "Seconds: ", time.time() - x
if __name__ == "__main__":
    main()
