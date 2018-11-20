def homefun(equations, values, queries):
    pass


#Do not modify below this line
if __name__ == '__main__':
    with open('HomefunIN.txt', 'r') as f:
        num_eqs = int(f.readline().strip())
        eqs = []
        values = []
        for i in range(num_eqs):
            n1, n2, value = f.readline().split()
            value = float(value)
            eqs.append([n1, n2])
            values.append(value)
        num_queries = int(f.readline().strip())
        queries = []
        for i in range(num_queries):
            query = f.readline().split()
            queries.append(query)
        results = homefun(eqs, values, queries)
        for r in results:
            print ('%.2f' % r)
