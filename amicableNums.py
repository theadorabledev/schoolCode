import sys
from math import sqrt
import time
from multiprocessing import Pool
def findAmicableNums(start, upTo):
    nums = set()
    for i in xrange(start, upTo):
        theSum = primeFactorsSum(i, i%2)
        if i != theSum and i == primeFactorsSum(theSum, theSum%2):
            nums.add(tuple(sorted((i, theSum))))
    return nums
def test(num):
    theSum = primeFactorsSum(num, num%2)
    if num != theSum and num == primeFactorsSum(theSum, theSum%2):
        return tuple(sorted((num, theSum)))  

def primeFactorsSum(n, increase):
    return sum(set(reduce(list.__add__, ([i, n/i] for i in xrange(1, int(sqrt(n))+1, increase+1) if (n % i == 0))))) - n
    #return {i for i in xrange(1, n) if (n % i == 0) }
def main():
    args = sys.argv[1:]
    p = Pool()
    x = set(p.map(test, range(2, int(args[0]))))
    x.remove(None)
    print sorted(x)
    #print findAmicableNums(2, int(args[0]))
if __name__ == "__main__":
    x = time.time()
    main()
    print time.time() - x
    