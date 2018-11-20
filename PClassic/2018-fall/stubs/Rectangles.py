def solve(field, l1, l2):
    
    #vals = {(x, y) for y in range(field[0], field[2]) for x in range(field[1], field[3])}
    badVals = set()
    retLen = len(field)
    for area in l1 + l2:
        x1, y1, x2, y2 = tuple(area)
        for x in range(max(x1, field[0]), min(x2, field[2])):
            for y in range(max(y1, field[1]), min(y2, field[3])):
                badVals.add((x, y))
                #vals.discard((x, y))
    return ((field[2] - field[0]) * (field[3] - field[1])) -len(badVals)
        
    """
    retVal = 0
    
    for y in range(field[0],field[2]):
        for x in range(field[1],field[3]):
            retVal += 1
    #print(retVal)
    for y in range(l1[0][0], l1[0][2]):
        for x in range(l1[0][1], l1[0][3]):
            if x >= field[0] and x < field[2] and y >= field[1] and y < field[3]:
                retVal -= 1
    
    for y in range(l2[0][0], l2[0][2]):
        for x in range(l2[0][1], l2[0][3]+1):
            if x >= field[0] and x < field[2] and y >= field[1] and y < field[3]:
                #print(x,y)
                retVal -= 1  
            else:
                print(x,y)
    
    return retVal
    """
#Do not modify below this line
if __name__ == '__main__':
    with open('RectanglesIN.txt', 'r') as f:
        while True:
            testCases = f.readline().strip()
            if testCases == '':
                break
            for i in range(int(testCases)):
                line = f.readline().strip()
                data = (line.split(" "))
                field = []
                for j in range(4):
                    field.append(int(data[j]))
                line = f.readline().strip()
                data = (line.split(" "))
                N = int(data[0])
                M = int(data[1])
                l1 = []
                l2 = []
                for j in range(N):
                    line = f.readline().strip()
                    data = (line.split(" "))
                    l1.append([int(x) for x in data])
                for j in range(M):
                    line = f.readline().strip()
                    data = (line.split(" "))
                    l2.append([int(x) for x in data])
                print(solve(field, l1, l2))
                
