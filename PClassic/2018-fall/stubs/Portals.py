#!/usr/bin/env python3

def portals(r, c, m):
    portals = {l:set() for l in "ABCDEFGHIJKLMNOPQRSTUVWXYZ"}
    reserved = {"#","&","$",".","\n"}
    for x in range(r - 1):
        for y in range(c - 1):
            print x, y
            if m[y][x] not in reserved:
                portals[m[y][x]].add((x, y))
                
    moveDict = {"n": (-1,0),
                "e":(0,1),
                "s":(1,0),
                "w":(0,-1)}
    startPoint = ()
    
    for row in range(r):
        for col in range(c):
            if m[row][col] == "$":
                startPoint = (col, row)
            
    frontier = [startPoint]
    seen = {startPoint}
    current = frontier.pop(0)
    while m[current[0]][current[1]] != '&':
        
        return 0
if __name__ == "__main__":
    with open("PortalsIN.txt", "r") as f:
        n = int(f.readline())
        for i in range(n):
            r, c = [int(x) for x in f.readline().split()]
            m = [list(f.readline()) for _ in range(r)]
            print(portals(r, c, m))
