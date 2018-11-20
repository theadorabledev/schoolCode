def careerPivot(A):
    pass

#Do not modify below this line
if __name__ == '__main__':
    with open('CareerPivotIN.txt', 'r') as f:
        while True:
            s = f.readline().strip()
            if s == '':
                break
            s = s.split()
            s = [int(i) for i in s]
            print(careerPivot(s))
