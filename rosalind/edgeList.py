import numpy as np
def getAdjacencyMatrix(meta, graphDataOrig, directed=False):
    matrix = np.zeros(shape=(meta[0],meta[0]))
    graphData = graphDataOrig
    del graphData[0]
    for node in graphData:
        for cNode in graphData[node]:
            matrix[node-1, cNode-1] = 1
            if not directed:
                matrix[cNode-1, node-1] = 1
    return matrix
def dataToGraph(newLineData, directed=False):
    meta = newLineData.pop(0)
    connections = {0:[]}
    for connection in newLineData:
        if not connection[0] in connections:
            connections[connection[0]] = []
        
        if not connection[1] in connections:
            connections[connection[1]] = []
            
        connections[connection[0]].append(connection[1])
        if not directed:
            connections[connection[1]].append(connection[0])
    for i in range(meta[0]+1):
        if i not in connections:
            connections[i]=[]
        return (meta, connections)
def fileToData(fileName, directed=False, multipleGraphs=False):
    f = open(fileName, "r")
    lineData = f.readlines()

    multGraphData = []
    if multipleGraphs:
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