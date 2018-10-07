import sys
from edgeList import *
from numpy.linalg import matrix_power
def squareInMatrix(meta, graph):
    #print graph
    #print np.trace(matrix_power(graph, 4)), np.trace(matrix_power(graph, 4))/ (8 * meta[0])
    #print matrix_power(graph, 4)
    if np.trace(matrix_power(graph, 4))/(8 * meta[0]) < 1:
        return -1
    return 1
def main():
    graphs = fileToData(sys.argv[1], multipleGraphs=True)
    #print graphs
    myList = [0] * (len(graphs) + 1)
    for i in range(len(graphs)):#graph in graphs:
        
        meta, connections = graphs[i]
     #   print meta, connections
        myList[i+1] = squareInMatrix(meta, getAdjacencyMatrix(meta, connections))
        #squareInMatrix(getAdjacencyMatrix(meta, connections))
    answer = " ".join([str(i) for i in myList])
    print answer[2:]	
if __name__ == "__main__":
    main()
