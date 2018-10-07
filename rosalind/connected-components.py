import sys
from edgeList import fileToData
import time
def connectedSearch(connections, level, connected={}):
    if not level:
        return connected
    nextLevel = set()
    for node in level:
        for n in connections[node]:
            if n not in connected:
                nextLevel.add(n)
    connected = connected | level
    return connectedSearch(connections, nextLevel,  connected=connected)

def main():
    meta, connections = fileToData(sys.argv[1])
    #myList = [0] * (meta[0] + 1)
	
    #answer = " ".join([str(i) for i in myList])
    #print answer[2:]	
    checked = set()
    #while len(checked) < connections:
    numChecked = 0   
    for node in connections:
        if node not in checked:
            numChecked += 1
            checked = checked | connectedSearch(connections, set(connections[node]), connected={node} )	
    print numChecked - 1
if __name__ == "__main__":
    x = time.time()
    #for i in range(10000):
    #    main()
    main()
    #print time.time() - x
