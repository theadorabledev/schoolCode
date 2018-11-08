import sys
class SudokoPuzzle:
    #getGroup = lambda x, y:((x + 3)/3) + ((y/3) * 3)
    def __init__(self, fileName):
        self.data = []
        self.getGroup = lambda x, y:((x + 3)/3) + ((y/3) * 3)
        self.possibleValues = {(x, y):set(range(1,10)) for x in xrange(9) for y in xrange(9)}
        self.knownValues = set()
        self.unknownValues = set()
        self.countValues = {num:0 for num in xrange(1, 10)}
        self.knownCount = 0
        self.savedState = None
        lines = open(fileName).readlines()
        for line in lines:
            self.data.append([int(i) for i in line.strip().replace("_", "0").split(",")])
#        self.printData()
        self.initValues()
        self.rows = {row:set(self.data[row]) for row in xrange(9)}
        self.columns = {col:set(row[col] for row in self.data) for col in xrange(9)}
        self.groups = {group:set() for group in xrange(1, 10)}
        self.initGroups()
        self.solve()
    def solve(self):
        while self.knownCount < 80:
            lastCount = self.knownCount
            self.updateValues()
            if lastCount == self.knownCount:
                #print self.__dict__
                self.printData()
                for v in self.possibleValues:
                    if len(self.possibleValues[v]) > 1:
                        print v, self.possibleValues[v]
                m = min(self.possibleValues, key=lambda k: len(self.possibleValues[k]) if len(self.possibleValues[k]) > 1 else 10)
                print m, self.possibleValues[m]
                raw_input("-->")       
    #random
    def initValues(self):
        for x in xrange(9):
            for y in xrange(9):
                if self.data[y][x] > 0:
                    self.possibleValues[(x, y)] = {self.data[y][x]}
                    self.knownValues.add((x, y))
                    self.countValues[self.data[y][x]] += 1
                    self.knownCount += 1
    def saveState(self):
        self.savedState = self.__dict__.copy()
    def printData(self):
        for line in self.data:
            print " ".join([str(i) for i in line])  
    def updateValues(self):
        for coord in self.possibleValues:
            if len(self.possibleValues[coord]) != 1:
                self.possibleValues[coord] -= self.rows[coord[1]]
                self.possibleValues[coord] -= self.columns[coord[0]]
                self.possibleValues[coord] -= self.groups[self.getGroup(coord[0], coord[1])]
                if len(self.possibleValues[coord]) == 1:
                    #print coord, self.possibleValues[coord]
                    self.knownCount += 1
                    for e in self.possibleValues[coord]:
                        self.data[coord[1]][coord[0]] = e
                        self.rows[coord[1]].add(e)
                        self.columns[coord[0]].add(e)
                        self.groups[self.getGroup(coord[0], coord[1])].add(e)
                        


    def initGroups(self):
        for x in xrange(9):
            for y in xrange(9):
                if self.data[y][x] > 0:
                    self.groups[self.getGroup(x, y)].add(self.data[y][x])
                    
def main():
    args = sys.argv[1:]
    s = SudokoPuzzle(args[0])
    s.printData()
    

if __name__ == "__main__":
    main()
