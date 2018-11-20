def findNums(A, hp):
    pass

#Do not modify below this line
if __name__ == '__main__':
    with open('BeatThatClefairyIN.txt', 'r') as f:
        while True:
            s = f.readline().strip()
            t = f.readline().strip()
            if s == '':
                break
            t = (t.split(','))
            t = [int(i) for i in t]
            print(findNums(t, int(s)))
