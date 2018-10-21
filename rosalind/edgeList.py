import numpy as np
from numpy.linalg import matrix_power
import copy
INFINITY = float("inf")
class pQueue:
    """ A python queue that returns the lowest priority item, with an optional option passign your own comparison functions."""
    def OrdinaryComparison(self, a, b):
        """ The basic comparison function."""
        if self.valueDict[a][0] < self.valueDict[b][0]: 
            return -1
        if self.valueDict[a][0] == self.valueDict[b][0]:
            return 0
        return 1

    def __init__(self, connections, comparator = OrdinaryComparison):
        self.cmpFunc = comparator
        self.array=[]
        self.valueDict = {i:[INFINITY, None] for i in connections}
        map(self.push, [i for i in connections])

    def push(self, vertex):
        """ Pushes vertex onto queue. """
        if not self.array:
            self.array.append(vertex)
        else:
            for i in xrange(len(self.array)):
                if i == (len(self.array) - 1) and self.cmpFunc(self, self.array[i], vertex) == -1:
                    self.array.append(vertex)
                    break                    
                if self.cmpFunc(self, self.array[i], vertex) != -1:
                    self.array.insert(i, vertex)
                    break        
    def pop(self):
        """ Removes minimum element of queue. """
        if self.array:
            return self.array.pop(0)
        else:
            return None
    def peek(self):
        """ Shows minimum element of queue. """
        if self.array:
            return self.array[0]
        else:
            return None


    def decreaseKey(self, vertex, value, last):
        """ Decreases the value of a vertex and updates it's position in the queue. """
        self.valueDict[vertex] = [value, last]
        for i in xrange(len(self.array)):
            if self.array[i] == vertex:
                self.array.pop(i)
                break
        self.push(vertex)
            
def djikstra(connections, startNode):
    """ BFS Priority-queue implementation of Djikstra's algorthm that reutrn a dict with the shortest distance to each node from a given start. """
#    if startNode == endNode:
#        return 0
    queue = pQueue(connections)
    queue.valueDict[startNode][0] = 0
    #queue.push(startNode, 0, startNode)
    queue.decreaseKey(1, 0, None)
    while queue.array:
        curNode = queue.pop()
        for vertex in connections[curNode]:
            if queue.valueDict[vertex[0]][0] > queue.valueDict[curNode][0] + vertex[1]:
                queue.decreaseKey(vertex[0], queue.valueDict[curNode][0] + vertex[1], curNode)
    for vertex in queue.valueDict:
        if queue.valueDict[vertex][0] == INFINITY:
            queue.valueDict[vertex][0] = -1
    return {vertex: queue.valueDict[vertex][0] for vertex in queue.valueDict}

def djikstra2(connections, startNode):
    """ Faster alternate version of djikstra that uses singe-edge extensions of shortest paths. """
    valueDict = {i:INFINITY for i in connections}
    valueDict[startNode] = 0
    unknown = {i for i in connections}
    known = set()
    while known != unknown:
        unseen = unknown - known
        curNode = min(unseen , key = valueDict.get)
        known.add(curNode)
        for node in connections[curNode]:
            if valueDict[node[0]] > valueDict[curNode] + node[1]:
                valueDict[node[0]] = valueDict[curNode] + node[1]
    for vertex in valueDict:
        valueDict[vertex] = valueDict[vertex] if valueDict[vertex] != INFINITY else -1
    return valueDict
def bellmanFord(connections, startNode, negativeCyclesCheck=False, shortestDagPath=False):
    """Finds single-source shortest paths in general graphs, checks if a cycle has negative weight cycles, finds shortest paths in DAGs. """
    startNode = startNode if not negativeCyclesCheck else len(connections) + 1
    if negativeCyclesCheck:
        connections[len(connections) + 1] = [[vertex, 0] for vertex in connections] # Dummy start node to get disconnected edges
    valueDict = {i:INFINITY for i in connections}
    valueDict[startNode] = 0
    if not shortestDagPath:
        for i in range(len(connections) - 1):
            for vertex in connections:
                for connectedVertex in connections[vertex]:            
                    valueDict[connectedVertex[0]] = min(valueDict[connectedVertex[0]], valueDict[vertex] + connectedVertex[1]) 
        if negativeCyclesCheck:
            #Negative weight is a permanent decreasing loop. The algorithm finds the shortest path which can have (len(vertexes) - 1) edges. If the value changes after a final loop, there was a negative weight cycle.
            
            for vertex in connections:
                for connectedVertex in connections[vertex]:            
                    if valueDict[connectedVertex[0]] != min(valueDict[connectedVertex[0]], valueDict[vertex] + connectedVertex[1]):
                        return 1
            return -1
        else:
            for vertex in valueDict:
                valueDict[vertex] = valueDict[vertex] if valueDict[vertex] != INFINITY else "x"
            return valueDict    
    else:
        topologicalOrder = topologicalSort({vertex:[connectedVertex[0] for connectedVertex in connections[vertex]] for vertex in connections})

        for vertex in topologicalOrder:
            for connectedVertex in connections[vertex]:
                valueDict[connectedVertex[0]] = min(valueDict[connectedVertex[0]], valueDict[vertex] + connectedVertex[1]) 
        for vertex in valueDict:
            valueDict[vertex] = valueDict[vertex] if valueDict[vertex] != INFINITY else "x"
        return valueDict
def screwyFileFix(lineData):
    """ Fixes edge-list data files without newlines. """
    newLineData = []
    for line in lineData:
        newLineData.append([int(j) for j in line.strip().split(" ")])    
    numGraphs = newLineData.pop(0)
    #print newLineData[0]
    myGraphs = []
    while newLineData:
        graph = []
        upTo = newLineData[0][1]
        for i in range(upTo+1):
            graph.append(newLineData.pop(0))
        myGraphs.append(graph)
    return myGraphs
    
    
def topologicalSort(argConnections):
    """ Topologically sorts the given graph data using Khan's algroithm. """
    connections = copy.deepcopy(argConnections)
    order = []
    nodes = [node for node in connections]
    for node in connections:
        for cNode in connections[node]:
            try:
                nodes.remove(cNode)
            except ValueError:
                pass
    #print nodes
    while nodes:
   #     print nodes
        
        node = nodes.pop(0)
        for i in xrange(nodes.count(node)):
            nodes.remove(node)
        order.append(node)
        connectedNodes = connections[node]
        del  connections[node]
        for cNode in connectedNodes:
            nodes.append(cNode)
            for eachNode in connections:
                if cNode in connections[eachNode]:
                    nodes.remove(cNode)
                    break
    return order

def isHamiltionianPath(connections):
    """ Returns if the inputted graph contains a hamiltonian path. """
    topSort = topologicalSort(connections)
    for i in range(len(topSort) - 1):
        if topSort[i+1] not in connections[topSort[i]]:
            return (False, [])
    return (True, topSort)
        
        

def isAcyclic(connections, level=set(),  checked=set()):
    """ A function that returns if a graph is acyclic. """
    if not checked:
        for i in range(1, len(connections)):
            if connections[i]:
                level.add(i)
                checked.add(i)
                break
    if not level:
        return True
    nextLevel = set()
    
    for node in level:
        for n in connections[node]:
            #if n not in checked:
            nextLevel.add(n)
    
    checked = checked | level
    if nextLevel & checked:
        return False
    return isAcyclic(connections, level=nextLevel, checked=checked)

def connectedSearch(connections, level, connected={}):
    """ Returns all connected parts of a graph. """
    if not level:
        return connected
    nextLevel = set()
    for node in level:
        for n in connections[node]:
            if n not in connected:
                nextLevel.add(n)
    connected = connected | level
    return connectedSearch(connections, nextLevel,  connected=connected)

def breadthSearch(connections, level, startNode, endNode, checked={}, length=1):
    """ Finds the shortest path from one node to another. """
    if startNode == endNode:
        return 0
    if not level:
        return -1
    if endNode in level:
        return length
    nextLevel = set()
    for node in level:
        for n in connections[node]:
            if n not in checked:
                nextLevel.add(n)
    checked = checked | level
    return breadthSearch(connections, nextLevel, startNode, endNode, checked=checked, length=(length+1))

def bipartiteTest(connections, level=set(),  checked=set(), length=1, blue=set(), red=set()):
    """ A function that test for bipartiteness in a graph using a two colored BFS search. """
    if not checked:
        for i in range(1, len(connections)):
            if connections[i]:
                level.add(i)
                checked.add(i)
                break
    if not level:
        return True
    if length % 2 == 0:
        red = red | level
    else:
        blue = blue | level
    if red & blue:
        return False
    nextLevel = set()
    for node in level:
        for n in connections[node]:
            if n not in checked:
                nextLevel.add(n)
    checked = checked | level
    return bipartiteTest(connections, level=nextLevel, checked=checked, length=(length+1), blue=blue, red=red)

def getAdjacencyMatrix(meta, graphDataOrig, directed=False):
    """ Returns graph data as adjacenct matrix. """
    matrix = np.zeros(shape=(meta[0],meta[0]))
    graphData = graphDataOrig
    if 0 in graphData:
        del graphData[0]
    for node in graphData:
        for cNode in graphData[node]:
            matrix[node-1, cNode-1] = 1
            if not directed:
                matrix[cNode-1, node-1] = 1
    return matrix

def hasCycles(meta, connections, count):
    """ Returns if the passed graph has any cycles of length count. """
    graph = getAdjacencyMatrix(meta, connections)
    if np.trace(matrix_power(graph, count))<1:#/(2 * count * meta[0]) < 1:
        return False
    return True   
    

def dataToGraph(newLineData, directed=False, weighted=False):
    """ Turns raw data into usable data. """
    meta = newLineData.pop(0)
    connections = {}
    for connection in newLineData:
        if not connection[0] in connections:
            connections[connection[0]] = []
        
        if not connection[1] in connections:
            connections[connection[1]] = []
        
        if not weighted:    
            connections[connection[0]].append(connection[1])
        else:
            connections[connection[0]].append([connection[1], connection[2]])
            
        if not directed:
            connections[connection[1]].append(connection[0])
    #keys = {i for i in connections}
    for i in xrange(1, meta[0]+1):
        if i not in connections:
            connections[i]=[]
    return (meta, connections)
    
def fileToData(fileName, directed=False, multipleGraphs=False, screwyFile=False, weighted=False):
    """ Reads a file and turns it into graph data. """
    f = open(fileName, "r")
    lineData = f.readlines()

    multGraphData = []
    if multipleGraphs:
        if screwyFile:
            return [dataToGraph(newLineData,directed=directed,weighted=weighted) for newLineData in screwyFileFix(lineData)]
            #for graph in screwyFile(lineData):
                
        else:
        
            lineData.append("\n")
            newLineData=[]
            for line in lineData:
                if line != "\n":
                    newLineData.append([int(j) for j in line.strip().split(" ")])
                else:
                    multGraphData.append(newLineData)
                    newLineData = []
            multGraphData.pop(0)
            return [dataToGraph(lineData,directed=directed,weighted=weighted) for lineData in multGraphData]
    else:
        newLineData = [[int(j) for j in i.strip().split(" ")] for i in lineData]
        return dataToGraph(newLineData, directed=directed, weighted=weighted)

    
#print fileToData("square.txt", multipleGraphs=True)
#print fileToData("breadth.txt", directed=True)