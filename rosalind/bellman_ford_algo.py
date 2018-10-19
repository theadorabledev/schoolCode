import sys
from edgeList import *
def main():
    #print sys.argv
    if sys.argv[1] == "--findNegCyc":
        graphs =  fileToData(sys.argv[2], directed=True, multipleGraphs=True, screwyFile=True, weighted=True)
        myList = [0] * (len(graphs))
        for i in range(len(graphs)):
            meta, connections = graphs[i]        
            #connections[m] = [[i, 0] for i in connections]
            myList[i] = bellmanFord(connections, 1, negativeCyclesCheck=True)
        print " ".join([str(i) for i in myList]) 
    elif sys.argv[1] == "--dag":
        meta, connections =  fileToData(sys.argv[2], directed=True, weighted=True)
        data = bellmanFord(connections, 1, shortestDagPath=True)
        print " ".join([str(data[key]) for key in data])        
    else:
        meta, connections =  fileToData(sys.argv[1], directed=True, weighted=True)
    
        data = bellmanFord(connections, 1)
        print " ".join([str(data[key]) for key in data])
        
       
if __name__ == "__main__":
    main()

