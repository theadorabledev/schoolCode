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
challenge3b()