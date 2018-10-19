import sys
from edgeList import *
def main():
    meta, connections =  fileToData(sys.argv[1], directed=True, weighted=True)

    data = djikstra2(connections, 1)
    print " ".join([str(data[key]) for key in data])
if __name__ == "__main__":
    main()

