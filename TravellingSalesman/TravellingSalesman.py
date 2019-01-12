from math import *
import random
def shuffled(l):
    a = l[::]
    random.shuffle(a)
    return a
def getEuclideanDistance(a, b):
    return round(sqrt(((a[0] - b[0]) ** 2) + ((a[1] - b[1]) ** 2)), 5)
def makeRegularPolygon(sides, dist):
    angle = radians(360.0 / sides)
    points = [(dist, 0)]
    for i in xrange(1, sides):
        x, y = points[-1]
        points.append(tuple([round((x * cos(angle)) - (y * sin(angle)), 5), round((y * cos(angle)) + (x * sin(angle)), 5)]))
    connections = {coord:[p for p in points if p != coord] for coord in points}
    distances = {(point, p) : getEuclideanDistance(point, p) for point in connections for p in connections[point]}
    return [connections, distances]
def connectionsToNetlogo(connections):
    return [list(coord) for coord in connections]
    #return " [" + " ".join([str(coord) for coord in connections]).replace("(", "[").replace(")","]").replace(",", "") + " ]"
class Path:
    def __init__(self, world, startPos=None, generations=100, batchSize=100, numberPassing=10, swapChance=5):
        self.world, self.distances = world
        self.startPos, self.generations, self.batchSize, self.numberPassing, swapChance = startPos, generations, batchSize, numberPassing, swapChance
        if not startPos:
            self.startPos = self.world.keys()[0]
        self.salesmen = []
        self.rejects = {" "}
        self.places = [coord for coord in self.world if coord != startPos]
        self.kirk()
        for i in xrange(generations):
            self.picard()
    def kirk(self):
        """ The first generation, to boldly go where no one has gone before. """
        for i in xrange(self.batchSize):
            self.salesmen.append(Salesman(self))
    def picard(self):
        """ The next generation. Still boldly going!"""
        pass
class Salesman:
    def __init__(self, path, route=None):
        self.path = path
        if route:
            self.route = route
        else:
            self.route = shuffled(path.places)
        self.score = sum([path.distances[(self.route[i], self.route[i + 1])] for i in xrange(len(path.places) - 1)], getEuclideanDistance(self.route[0], path.startPos) + getEuclideanDistance(self.route[-1], path.startPos))
    def beFruitfulAndMultiply(path, mother, father, numChildren):
        """ Returns the children of the the two Parents. """
        children = []
        
        for x in xrange(numChildren):
            newRoute = [" "] * len(mother.route)
            r1 = random.randint(0, len(newRoute) - 2)
            r2 = random.randint(r1 + 1, len(newRoute) - 1)
            for i in xrange(r1, r2 + 1):
                newRoute[i] = mother.route[i]
            for coord in father.route:
                if coord not in newRoute:
                    newRoute[newRoute.index(" ")] = coord
            for coord in newRoute:
                if random.randint(100) < self.path.swapChance:
                    r1 = random.randint(len(newRoute) - 1)
                    r2 = random.randint(len(newRoute) - 1)                   
            children.append(Salesman(self.path, route=newRoute))
        return children
    
                    
            
            
        
def netlogoA(s):
    x = makeRegularPolygon(s, 10)
    return connectionsToNetlogo(x[0])
def main():
    square = makeRegularPolygon(4, 10)
    p = Path(square)
if __name__ == "__main__":
    main()