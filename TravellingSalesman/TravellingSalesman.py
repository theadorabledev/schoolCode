from math import *
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
class Path:
    def __init__(self, world, startPos, depth, batchSize, numberPassing, minSwaps, maxSwaps):
        self.world, self.distances = world
        self.startPos, self.depth, self.batchSize, self.numberPassing, self.minSwaps, self.maxSwaps = startPos, depth, batchSize, numberPassing, minSwaps, maxSwaps
print makeRegularPolygon(4, 10)
