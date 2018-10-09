import sys
from edgeList import *

def main():
    meta, connections = fileToData(sys.argv[1], directed=True)
    print " ".join([str(node) for node in topologicalSort(connections)])	
if __name__ == "__main__":
    main()

