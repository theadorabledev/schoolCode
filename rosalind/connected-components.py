import sys
from edgeList import fileToData
import time

def main():
    meta, connections = fileToData(sys.argv[1])	
    checked = set()
    numChecked = 0   
    for node in connections:
        if node not in checked:
            numChecked += 1
            checked = checked | connectedSearch(connections, set(connections[node]), connected={node} )	
    print numChecked - 1
if __name__ == "__main__":
    x = time.time()
    main()

