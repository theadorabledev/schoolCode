def search(n, rows, A):
    #print n, rows, A
    for i in range(len(A)):
        for j in range(len(A[i])):
            if A[i][j] == n:
                return str(i) + "," + str(j)
    return "Pikachu is lost"

#Do not modify below this line
if __name__ == '__main__':
    with open('GottaCatchEmAllIN.txt', 'r') as f:
        while True:
            s = f.readline().strip()
            t = f.readline().strip()
            if s == '':
                break
            arr = []
            for i in range(int(t)):
                u = f.readline().strip().split(', ')
                u = [int(i) for i in u]
                arr.append(u)
            print(search(int(s), int(t), arr))
