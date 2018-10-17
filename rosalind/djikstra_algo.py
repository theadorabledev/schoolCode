import sys
from edgeList import *
def main():
    meta, connections =  fileToData(sys.argv[1], directed=True, weighted=True)
    print " ".join([str(djikstra(connections, 1, i)) for i in connections])
if __name__ == "__main__":
    main()

