import copy
def challenge1a():
    print eval("0" + "".join([line.strip() for line in open("input1a.txt").readlines()]))
def challenge1b():
    l = set()
    last = "0"
    done = False
    while not done:
        for line in [line.strip() for line in open("input1a.txt").readlines()]:
            if eval(last + line) in l:
                print eval(last + line)
                done = True
                break            
            l.add(eval(last + line))
            last = str(eval(last + line))
def challenge2a():
    d = {2:0, 3:0}
    for line in [line.strip() for line in open("input2a.txt").readlines()]:
        x = set()
        for letter in set(line):
            x.add(line.count(letter))
        if 2 in x:
            d[2] += 1;
        if 3 in x:
            d[3] += 1
    print d[2] * d[3]
def challenge2b():
    s = []
    answer = ""
    for line in [line.strip() for line in open("input2a.txt").readlines()]:
        for x in xrange(len(line)):
            y = ''.join([line[i] for i in xrange(len(line)) if i  != x])
            for line2 in [linea.strip() for linea in open("input2a.txt").readlines()]:
                if ''.join([line2[i] for i in xrange(len(line2)) if i  != x]) == y and line2 != line:
                    print line
                    print line2
                    print y
                    print "__________"
                    break

def challenge3a():
    d = {}
    lines = [line.strip() for line in open("input3a.txt").readlines()]    
    for a in lines:
        z = (tuple([int(i) for i in a.split("@")[1].split(":")[0].split(",")]), tuple([int(i) for i in a.split("@")[1].split(":")[1].split("x")]))
        for x in xrange(z[0][0], z[0][0] + z[1][0]):
            for y in xrange(z[0][1], z[0][1] + z[1][1]):
                if (x,y) not in d:
                    d[(x,y)] = 0
                d[(x,y)] += 1
    s = 0
    for coord in d:
        if d[coord] > 1:
            s += 1
    print s
def challenge3b():
    d = {}
    lines = [line.strip() for line in open("input3a.txt").readlines()] 
    lines = [(tuple([int(i) for i in a.split("@")[1].split(":")[0].split(",")]), tuple([int(i) for i in a.split("@")[1].split(":")[1].split("x")]), a.split("@")[0]) for a in lines]
    for z in lines:
        for x in xrange(z[0][0], z[0][0] + z[1][0]):
            for y in xrange(z[0][1], z[0][1] + z[1][1]):
                if (x,y) not in d:
                    d[(x,y)] = 0
                d[(x,y)] += 1    
    for z in lines:
        perfect = True
        for x in xrange(z[0][0], z[0][0] + z[1][0]):
            for y in xrange(z[0][1], z[0][1] + z[1][1]):
                if d[(x,y)] > 1:
                    perfect = False 
        if perfect:
            print z
            break
def challenge4a():
    d = sorted([line.strip() for line in open("input4a.txt").readlines()] )
    guards = {}
    guard = None
    time = 0
    for line in d:
        #print line#line[line.index(" ") + 1: line.index("]")]
        if "Guard" in line:
            guard = line[line.index("#") + 1: line.index(" beg")]
            time = 0
            if guard not in guards:
                guards[guard] = []
                
            #print line[line.index("#") + 1: line.index(" beg")]
        else:
            if "sleep" in line:
                time = int(line[line.index(" ") + 1: line.index("]")].split(":")[1])
            else:
                for t in xrange(time, int(line[line.index(" ") + 1: line.index("]")].split(":")[1])):
                    guards[guard].append(t)
                #print guard, range(time, int(line[line.index(" ") + 1: line.index("]")].split(":")[1]))
    sleepiestGuard = max(guards, key = lambda k : len(guards[k]))
    sleepiestMinute = max(guards[sleepiestGuard], key = lambda k : guards[sleepiestGuard].count(k))
    print sleepiestMinute * int(sleepiestGuard)
def challenge4b():
    d = sorted([line.strip() for line in open("input4a.txt").readlines()] )
    guards = {}
    guard = None
    time = 0    
    minutes = {m:[] for m in xrange(0, 60)}
    for line in d:
        #print line#line[line.index(" ") + 1: line.index("]")]
        if "Guard" in line:
            guard = line[line.index("#") + 1: line.index(" beg")]
            time = 0
            sleeping = False
            if guard not in guards:
                guards[guard] = []
                
            #print line[line.index("#") + 1: line.index(" beg")]
        else:
            if "sleep" in line:
                time = int(line[line.index(" ") + 1: line.index("]")].split(":")[1])
            else:
                for t in xrange(time, int(line[line.index(" ") + 1: line.index("]")].split(":")[1])):
                    #guards[guard].append(t)      
                    minutes[t].append(guard)
    highestMinute = max(minutes, key = lambda k : max({minutes[k].count(i) for i in minutes[k]}) if len(minutes[k]) > 0 else 0)
    sleepiestGuard = int(max(guards, key = lambda k : minutes[highestMinute].count(k)))
    print sleepiestGuard * highestMinute
def challenge5a():
    d = open("input5a.txt").readlines()[0].strip()
    #d = "dabAcCaCBAcCcaDA"
    change = True
    lastPos = 3
    #print range(len(d) - 1, 0, 2)
    while change == True:
        change = False
        for i in xrange(lastPos - 2, len(d) - 1):
            
            if d[i].lower() == d[i + 1].lower() and d[i] != d[i + 1]:
                d = d[:i] + d[i + 2:]
                lastPos = i
                change = True
                #break
            if change:
                break 
    change = False
    for i in xrange(0, len(d) - 1):
        
        if d[i].lower() == d[i + 1].lower() and d[i] != d[i + 1]:
            d = d[:i] + d[i + 2:]
            lastPos = i
            change = True
            #break
        if change:
            break    
    #print d
    print len(d)

def challenge5b():
    allDict = {}
    otherDict = {}
    a = open("input5a.txt").readlines()[0].strip()
    for let in "abcdefghijklmnopqrstuvwxyz":
        d = a.replace(let, "").replace(let.upper(), "")
        #d = "dabAcCaCBAcCcaDA"
        change = True
        lastPos = 3
        #print range(len(d) - 1, 0, 2)
        while change == True:
            change = False
            
            for i in xrange(lastPos -2, len(d) - 1):
                
                if d[i].lower() == d[i + 1].lower() and d[i] != d[i + 1]:
                    d = d[:i] + d[i + 2:]
                    lastPos = i + 1
                    change = True
                    #break
                if change:
                    break 
            """    
            if not change:
                for i in xrange(0, len(d) - 1):
            
                    if d[i].lower() == d[i + 1].lower() and d[i] != d[i + 1]:
                        change = True
                        break
                    if change:
                        break"""
        change = False
        for i in xrange(0, len(d) - 1):
            
            if d[i].lower() == d[i + 1].lower() and d[i] != d[i + 1]:
                d = d[:i] + d[i + 2:]
                lastPos = i
                change = True
                #break
            if change:
                break    
        #print d
        print let
        allDict[let] = len(d)   
        #otherDict[let] = d
    print allDict[min(allDict, key = lambda k : allDict[k])]
def topologicalSort(argConnections):
    """ Topologically sorts the given graph data using Khan's algroithm. """
    connections = copy.deepcopy(argConnections)
    order = []
    nodes = [node for node in connections]
    for node in connections:
        for cNode in connections[node]:
            try:
                nodes.remove(cNode)
            except ValueError:
                pass
    while nodes:
        node = nodes.pop(0)
        for i in xrange(nodes.count(node)):
            nodes.remove(node)
        order.append(node)
        if node in connections:
            connectedNodes = sorted(connections[node])
            del  connections[node]
            for cNode in connectedNodes:
                nodes.append(cNode)
                nodes.sort()
                for eachNode in connections:
                    if cNode in connections[eachNode]:
                        nodes.remove(cNode)
                        break
    return order
def challenge7a():
    lines = sorted([line.strip() for line in open("input7.txt").readlines()])
    connections = {}#a:[] for a in "ABCDEFGHIJKLMNOPQRSTUVWXYZ"}
    for line in lines:
        a = line.split("tep ")[1][0]
        b = line.split("tep ")[2][0]
        if a not in connections:
            connections[a] = []
        connections[a].append(b)
    #print connections
    #print "".join(topologicalSort(connections))
    return "".join(topologicalSort(connections))
def challenge7b():
    
    lines = sorted([line.strip() for line in open("input7.txt").readlines()])
    connections = {}#a:[] for a in "ABCDEFGHIJKLMNOPQRSTUVWXYZ"}
    for line in lines:
        a = line.split("tep ")[1][0]
        b = line.split("tep ")[2][0]
        if a not in connections:
            connections[a] = []
        connections[a].append(b)    
    order = "".join(topologicalSort(connections))
    rOrder = order[::-1]
    possibleWork = set(order[0])
    numWorkers = 5
    alphabet = set(order)
    proles = [[0, ""] for i in xrange(numWorkers)]
    time = 0
    done = set()
    workingOn = set()
    reqs = {let:[l for l in connections if let in connections[l]] for let in order}
    while len(done) < len(order) + 1:
        print proles, done, len(done), possibleWork
        #print possibleWork
        for p in xrange(numWorkers):
            if proles[p][0] == 0:
                done.add(proles[p][1])
                rOrder = rOrder.replace(proles[p][1], "")
                if proles[p][1] in connections:
                    for c in connections[proles[p][1]]:
                        possibleWork.add(c)  
                proles[p][1] = ""
        for p in xrange(numWorkers):
            if proles[p][0] == 0:
                for let in order:
                    #print [(rOrder[l] in done) for l in xrange(rOrder.index(let), len(rOrder))]
                    #raw_input([req in done for req in reqs[let]])
                    reqsMet = [req in done for req in reqs[let]]
                    if let in possibleWork - done - workingOn and len(reqsMet) == reqsMet.count(True):
                        
                        #print p, let, possibleWork, [req for req in reqs[let]]
                        proles[p][1] = let
                        proles[p][0] = ord(let) - ord('A') + 1 + 60
                        possibleWork.remove(let)
                        workingOn.add(let)
                        #done.add(let)
                        break
            if proles[p][0] > 0:
                proles[p][0] -= 1
        #print done, order
        #raw_input("->")
        if len(done) == len(order):
            break
        time += 1
    print time
challenge7b()