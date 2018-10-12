import numpy as np
from numpy.linalg import matrix_power
import copy

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
    

def dataToGraph(newLineData, directed=False):
    """ Turns raw data into usable data. """
    meta = newLineData.pop(0)
    connections = {}
    for connection in newLineData:
        if not connection[0] in connections:
            connections[connection[0]] = []
        
        if not connection[1] in connections:
            connections[connection[1]] = []
            
        connections[connection[0]].append(connection[1])
        if not directed:
            connections[connection[1]].append(connection[0])
    for i in range(1, meta[0]+1):
        if i not in connections:
            connections[i]=[]
        return (meta, connections)
    
def fileToData(fileName, directed=False, multipleGraphs=False, screwyFile=False):
    """ Reads a file and turns it into graph data. """
    f = open(fileName, "r")
    lineData = f.readlines()

    multGraphData = []
    if multipleGraphs:
        if screwyFile:
            return [dataToGraph(newLineData,directed=directed) for newLineData in screwyFileFix(lineData)]
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
            return [dataToGraph(lineData,directed=directed) for lineData in multGraphData]
    else:
        newLineData = [[int(j) for j in i.strip().split(" ")] for i in lineData]
        return dataToGraph(newLineData, directed=directed)

    
#print fileToData("square.txt", multipleGraphs=True)
#print fileToData("breadth.txt", directed=True)