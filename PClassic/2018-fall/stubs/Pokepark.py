def solve(l):
    #print l
    myFunc = lambda i: l[i + 1] if len(l) - 1 > i else l[0]
    myFunc2 = lambda i: i if len(l) - 1 > i else 0
    tot = 0
    while len(l) > 1:
        #print l
        m = min(l)
        for i in range(len(l)  ):
            if l[i] == m:
                if l[i-1] < myFunc(i):
                    if i == 0:
                        a = l.pop(0)
                        b = l.pop(i - 1)                        
                    else:
                        a = l.pop(i - 1)
                        b = l.pop(i - 1)
                    l.insert(i - 1, b + a)
                    #print l, a, b, a+b, "a < b"
                    tot += a+b
                    break
                else:
                    a = l.pop(i)
                    b = l.pop(myFunc2(i))
                    l.insert(i, b + a)
                    #print l, a, b, a+b, "a > b"
                    tot += a+b
                    break
    return tot
#Do not modify below this line
if __name__ == '__main__':
    with open('PokeparkIN.txt', 'r') as f:
        while True:
            testCases = f.readline().strip()
            if testCases == '':
                break
            for i in range(int(testCases)):
                line = f.readline().strip()
                data = (line.split(" "))
                N = int(data[0])
                l = []
                line = f.readline().strip()
                data = line.split(" ")
                for i in range(N):
                    l.append(int(data[i]))
                print(solve(l))
