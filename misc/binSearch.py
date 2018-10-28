def OrdinaryComparison(a,b):
    if a < b: 
        return -1
    if a == b: 
        return 0
    return 1

def binSearch(array, val, cmpFunc = OrdinaryComparison):
    index = len(array)/2
    while True:
        if cmpFunc(array[index], val) == 1:
            index /= 2
        elif cmpFunc(array[index], val) == -1:   
            index += ((len(array) - index)/2)
        else:
            return index

#print binSearch([ 2, 3, 4, 10, 40 ] , 10)