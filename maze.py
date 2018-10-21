from PIL import Image
import sys
import time
INFINITY = float("inf")

class Maze:
    class Node:
        """ A class holding some basic information about the node. """
        def __init__(self, pos):
            self.pos = pos
            self.connections = []
    def __init__(self, im):
        self.width = im.size[0]
        self.height = im.size[1]
        self.data = list(im.getdata(0))
        self.start = None
        self.end = None
        self.nodes = {}#[]
        count = 1
        # Gets start node
        for x in xrange(1, self.width - 1):
            if self.data[x]:
                self.start = count
                self.nodes[1] =  Maze.Node((0, x))
                count += 1
                break
        # Loops through data and gets nodes    
        for y in xrange(1, self.height - 1):
            rowChange = y * self.width
            rowAbove = rowChange - self.width
            rowBelow = rowChange + self.width
            
            prv = 0
            cur = 0
            nxt = self.data[rowChange + 1]
            for x in xrange(1, self.width - 1):
                prv = cur
                cur = nxt
                nxt = self.data[rowChange + x + 1]
                above = self.data[rowAbove + x]
                below = self.data[rowBelow + x]
                XOX = not prv and not nxt #BWB
                OOO = prv and cur and nxt # WWW
                aboveAndBelowBlack = not above and not below
                aboveOrBelowBlack = not above or not below
                if not cur:
                    continue     
                
                if not (OOO and aboveAndBelowBlack) and not (XOX and not aboveOrBelowBlack): # if not horizantal/vertical line
                    n = Maze.Node((y, x))
                    self.nodes[count] = n
                    count += 1
                
                                

        # Gets end node
        for x in xrange(1, self.width - 1):
            if self.data[(self.height * self.width) - self.width + x]:
                self.end = count
                self.nodes[count] = Maze.Node((self.height - 1, x))
                break        
        self.posToNodeDict = {self.nodes[node].pos:node for node in self.nodes}
        self.blacks = {(y, x ) for y in xrange(self.width) for x in xrange(self.width) if not self.data[(y * self.height) + x]}

        self.updateConnections()
    def updateConnections(self):
        """ Connects the nodes of the graph. """
        # Loops thorugh each node, looks North, East, South, and West until it encounters new node or wall
        for node in self.nodes:
            nodeY, nodeX = self.nodes[node].pos
            for i in xrange(nodeX + 1, self.width - 1):
                if (nodeY, i) in self.posToNodeDict:
                    self.nodes[node].connections.append((self.posToNodeDict[(nodeY, i)], abs(nodeX - i)))
                    break
                if (nodeY, i) in self.blacks:
                    break
            for i in xrange(nodeX - 1, 0, -1):
                if (nodeY, i) in self.posToNodeDict:
                    self.nodes[node].connections.append((self.posToNodeDict[(nodeY, i)], abs(nodeX - i)))
                    break
                if (nodeY, i) in self.blacks:
                    break                
            
            for i in xrange(nodeY + 1, self.height):
                if (i, nodeX) in self.posToNodeDict:
                    self.nodes[node].connections.append((self.posToNodeDict[(i, nodeX)], abs(nodeY - i)))
                    break
                if (i, nodeX) in self.blacks:
                    break                
            for i in xrange(nodeY - 1, -1, -1):
                if (i, nodeX) in self.posToNodeDict:
                    self.nodes[node].connections.append((self.posToNodeDict[(i, nodeX)], abs(nodeY - i)))
                    break  
                if (i, nodeX) in self.blacks:
                    break                     
                

    def returnConnections(self):
        """ Returns data as adjacency list. """
        return {node:self.nodes[node].connections for node in self.nodes}
def djikstra2(connections, startNode, endNode):
    pass
def djikstra(connections, startNode, endNode):
    """ Faster alternate version of djikstra that uses singe-edge extensions of shortest paths. """
    valueDict = {i:INFINITY for i in connections}
    valueDict[startNode] = 0
    unknown = {i for i in connections}
    lastNode = {i: None for i in connections}
    known = set()
    while unknown:
        curNode = min(unknown , key = valueDict.get)
        #if curNode == endNode:
         #   break
        unknown.discard(curNode)
        known.add(curNode)
        for node in connections[curNode]:
            if valueDict[node[0]] > valueDict[curNode] + node[1]:
                valueDict[node[0]] = valueDict[curNode] + node[1]
                lastNode[node[0]] = curNode
    order = [endNode]
    curNode = lastNode[endNode]
    while curNode:
        order.append(curNode)
        curNode = lastNode[curNode]
    return order[::-1]#lastNode#valueDict
def main():
    args = sys.argv[1:]
    im = Image.open(args[0])
    outFile = args[1] if len(args) > 1 else (args[0][:-4] + "-solved.png")
    maze = Maze(im)
    connections = maze.returnConnections()
    path = djikstra(connections, maze.start, maze.end)
    pathCoords = [maze.nodes[node].pos for node in path]
    allPathCoords = []

    for i in range(len(pathCoords) - 1):
        if pathCoords[i][0] == pathCoords[i + 1][0]:
            vMax, vMin = max(pathCoords[i][1], pathCoords[i + 1][1]), min(pathCoords[i][1], pathCoords[i + 1][1])
            for x in xrange(vMin, vMax + 1):
                allPathCoords.append((pathCoords[i][0], x))
        else:
            vMax, vMin = max(pathCoords[i][0], pathCoords[i + 1][0]), min(pathCoords[i][0], pathCoords[i + 1][0])
            for x in xrange(vMin, vMax + 1):
                allPathCoords.append((x, pathCoords[i][1]))
    im = im.convert("RGB")
    imPixels = im.load()
    
    for coord in allPathCoords:
        imPixels[coord[1], coord[0]] = (255, 0, 0) 
    im.save(outFile)
if __name__ == "__main__":
    x = time.time()
    main()
    print time.time() - x