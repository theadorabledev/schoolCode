import pprint
import random
import numpy as np
from PIL import Image
import sys
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
#print minSpanTree(sampleData)

def getNeighbors(node , width, height):
    x, y = node
    neighbors = [(x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)]
    return [node for node in neighbors if not (node[0] < 0 or node[1] < 0 or node[0] > width - 1 or node[1] > height - 1)]

def generateMaze(width, height):
    """ Generates a maze of given dimensions using Prim's algorithm. """
    maze = []
    for y in xrange(height):
        maze.append([(x, y) for x in xrange(width)])
    mazeWalls = set()
    mazePassages = set()
    cells = set()
    walls = set()
    for row in maze:
        for node in row:
            for neighbor in getNeighbors(node, width, height):
                mazeWalls.add(tuple(sorted([node, neighbor])))
            if node[0] == 0 or node[1] == 0 or node[0] == width - 1 or node[1] == height - 1:
                pass

    start = (1, 1)
    cells.add(start)
    walls.add(tuple(sorted([start, (start[0], start[1] + 1)])))
    
    print len(mazeWalls)
    print len(walls)    
    while walls:
        wall = random.choice(tuple(walls))
        walls.discard(wall)
        if not (wall[0] in cells and wall[1] in cells):
            cell = wall[1] if wall[0] in cells else wall[1]
            cells.add(cell)
            mazeWalls.discard(wall)
            mazePassages.add(wall)
            for node in getNeighbors(cell, width, height):
                if node not in cells and tuple(sorted([cell, node])) in mazeWalls:
                    walls.add(tuple(sorted([cell, node])))
    data = np.full(((height * 2)  - 1, (width * 2) - 1, 3), (0, 0, 0), dtype=np.uint8)
    for passage in mazePassages:
        cell1, cell2 = passage
        middle = (
            (cell1[0] * 2 + cell2[0] * 2)/2, 
            (cell1[1] * 2 + cell2[1] * 2)/2 
            )
        nCell1 = (cell1[0] * 2, cell1[1] * 2)
        nCell2 = (cell2[0] * 2, cell2[1] * 2)
        #print "passage", nCell1, middle, nCell2
        data[nCell1[1] - 1, nCell1[0] - 1] = data[middle[1] - 1, middle[0] - 1] = data[nCell2[1] - 1, nCell2[0] - 1] = (255, 255, 255)
        #data[(cell1[1] + cell2[1]), (cell1[0] + cell2[0])] = (0, 0, 0)

    data[:,[0,-1]] = data[[0,-1]] = data[[1,-1]] = (0, 0, 0)
    #data[start[1] * 2, start[0] * 2 - 1] = (255, 0, 0)
    return data
    img = Image.fromarray(data, "RGB")
    img.save("gMazeX.png")  
    img = Image.fromarray(data, "RGB")
    img.save("gMaze4.png")
    img.show()
    #for wall in mazeWalls:
        
    
#generateMaze(4, 4)
#generateMaze(100, 100)       

def main():
    args = sys.argv[1:]
    mazeData = generateMaze(int(args[0]), int(args[1]))
    imgFile = "gMazeX.png" if len(args) < 2 else args[2]
    #print imgFile
    img = Image.fromarray(mazeData, "RGB")
    img.save(imgFile)  
    img.show()    
if __name__ == "__main__":
    main()