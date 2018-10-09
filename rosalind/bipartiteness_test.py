import sys
from edgeList import *


    
def main():
    graphs = fileToData(sys.argv[1], multipleGraphs=True)
    myList = [-1] * (len(graphs) + 1)
    for i in range(len(graphs)):
        meta, connections = graphs[i]
        if bipartiteTest(connections):
             myList[i+1] = 1

    answer = " ".join([str(i) for i in myList])
    print answer[2:]	
    #print __file__
if __name__ == "__main__":
    main()

