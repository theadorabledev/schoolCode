import sys
from edgeList import fileToData
import time
def breadthSearch(connections, level, startNode, endNode, checked={}, length=1):

    if startNode == endNode:
        return 0
    if not level:
        return -1
    if endNode in level:
        return length
    nextLevel = set()
    for node in level:
        for n in connections[node]:
            if n not in checked:
                nextLevel.add(n)
    checked = checked | level
    return breadthSearch(connections, nextLevel, startNode, endNode, checked=checked, length=(length+1))

def main():
    meta, connections = fileToData(sys.argv[1], directed=True)
    myList = [0] * (meta[0] + 1)
    for num in connections:
        myList[num] = breadthSearch(connections, set(connections[1]), 1, num, checked={1} )		
    answer = " ".join([str(i) for i in myList])
    print answer[2:]	
if __name__ == "__main__":
    x = time.time()
    #for i in range(10000):
    #    main()
    main()
    #print time.time() - x
