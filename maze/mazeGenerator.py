import pprint
import random
import numpy as np
from PIL import Image
sampleData = [("A", "B", 5), ("A", "C", 6), ("A", "D", 4), ("B", "C", 1), ("B", "D", 2), ("C", "D", 2), ("C", "E", 5), ("C", "F", 3), ("D", "F", 4), ("E", "F", 4)]
def minSpanTree(connections):
    """ Returns the minumum spanning tree using Kruskal's algorithm. """
    # connections in form [(nodeA, nodeB, weight)]
    sortedConnections = sorted(connections, key=(lambda node: node[2]))
    nodes = set()
    edges = set()
    for edge in sortedConnections:
        if not (edge[0] in nodes and edge[1] in nodes):
            edges.add(edge)
            nodes.add(edge[0])
            nodes.add(edge[1])
    print edges
    return sortedConnections
print minSpanTree(sampleData)

def getNeighbors(node , width, height):
    x, y = node
    neighbors = [(x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)]
    return [node for node in neighbors if not (node[0] < 0 or node[1] < 0 or node[0] > width - 1 or node[1] > height - 1)]

def generateMaze(width, height):
    """ Generates a maze of given dimensions. """
    printLambda = lambda x: "XX" if x in walls else "  " if x in cells else "[]"
    maze = []
    for y in xrange(height):
        maze.append([(x, y) for x in xrange(width)])
   # mazeWalls=[sorted(tuple(node, neighbor)) for neighbor in getNeighbors(node, width, height) for row in maze for node in maze ]
    mazeWalls = set()
    for row in maze:
        print row
    cells = set()
    walls = set()
    for row in maze:
        for node in row:
            for neighbor in getNeighbors(node, width, height):
                mazeWalls.add(tuple(sorted([node, neighbor])))
            if node[0] == 0 or node[1] == 0 or node[0] == width - 1 or node[1] == height - 1:
                cells.add(node)
    start = ((random.randint(2, width - 2)), 0)
    cells.add(start)
    print "start", start
    print getNeighbors(start, width, height)
    #for node in getNeighbors(start, width, height):
    #    walls.add(tuple(sorted([start, node])))
    walls.add(tuple(sorted([start, (start[0], start[1] + 1)])))
    while walls:
        
        wall = walls.pop()
        print "wall", wall
        if not (wall[0] in cells and wall[1] in cells):
            cell = wall[1] if wall[0] in cells else wall[1]
            cells.add(cell)
            #cells.add(wall[1])
            mazeWalls.discard(wall)
            
            for node in getNeighbors(cell, width, height):
                if node not in cells and tuple(sorted([cell, node])) in mazeWalls:
                    walls.add(tuple(sorted([cell, node])))
            #print "hello"
        for row in maze:
            print "".join([printLambda(n) for n in row])
        print "Walls", walls
        #raw_input("--> ")
    print len(mazeWalls)
    for w in mazeWalls:
        print w

    data = np.full(((height * 2)  - 1, (width * 2) - 1, 3), (255, 255, 255), dtype=np.uint8)
    for cell in cells:

        data[cell[1] * 2, cell[0] * 2] = [255, 0, 0]
    for wall in mazeWalls:
        cell1, cell2 = wall
        #print [(cell1[1] + cell2[1]), (cell1[0] + cell2[0])]
        data[(cell1[1] + cell2[1]), (cell1[0] + cell2[0])] = (0, 0, 0)
    data = data[1:-1, 1:-1]
    data[:,[0,-1]] = data[[0,-1]] = (0, 0, 0)
    data[start[1] * 2, start[0] * 2] = (255, 255, 255)
    #data[2, 2] = (255, 0, 0)
    img = Image.fromarray(data, "RGB")
    img.save("gMaze.png")
    #for wall in mazeWalls:
        
    
generateMaze(4, 4)
        
