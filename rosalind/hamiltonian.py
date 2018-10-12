import sys
from edgeList import *

def main():
    graphs = fileToData(sys.argv[1], multipleGraphs=True, directed=True, screwyFile=True)
    for graph in graphs:
        meta, connections = graph
        hPath = isHamiltionianPath(connections)
        if hPath[0]:
            print "1 " + " ".join([str(i) for i in hPath[1]])
        else:
            print "-1"

    #print __file__
if __name__ == "__main__":
    main()

