import sys
from math import sqrt
import time
from multiprocessing import Pool, cpu_count
LENGTH = 4
factors = {}

def amicNumTest(num):
    #global factors
    #theSum = factors[num]#primeFactorsSum(num, num%2)
    theSum = primeFactorsSum(num, num%2)
    if theSum != 1 and num != theSum and num == primeFactorsSum(theSum, theSum%2):
    #if theSum != 1 and num != theSum and num == factors[theSum]:#primeFactorsSum(theSum, theSum%2):
        return tuple(sorted([num, theSum]))  
    return None
def sociableNumTest(num, length=LENGTH):
    """ Checks if the number is a sociable number with length under 4. """
    nums = [num, primeFactorsSum(num, num%2)]
    #while nums[-1] != 1:
    for i in xrange(30):#length):
        if nums[-1] == 1:
            return None
        nums.append(primeFactorsSum(nums[-1],nums[-1]%2))
        if nums[0] == nums[-1]:
            return tuple(set(sorted(nums)))
    return None
def primeFactorsSum(n, increase):
    """ Returns the sum of the factors. """
    a = p = Pool(cpu_count())
    x = sum(reduce(p.map(lambda i: [i, n/i] if n % i == 0 else 0, xrange(1, int(sqrt(n)) + 1, increase+1 ))))
    return a
    #return sum(set(reduce(list.__add__, ([i, n/i] for i in xrange(1, int(sqrt(n))+1, increase+1) if (n % i == 0))))) - n
def main():
    args = sys.argv[1:]
    global factors
    primeFactorsSum(14316, 14316%2)
    
    if args[0] != "--sociable":
        factors = {i:primeFactorsSum(i, i%2) for i in xrange(2, int(args[0]) + 1)}
        #print amicNumTest(6)
        p = Pool(cpu_count())
        #x = set(p.map(amicNumTest, xrange(6, int(args[0]))))
        x = set(map(amicNumTest, xrange(6, int(args[0]))))
        x.remove(None)
        print sorted(x)
    else:
        factors = {i:primeFactorsSum(i, i%2) for i in xrange(1, int(args[1]) + 1)}
        if len(args) > 2:
            LENGTH = int(args[2])
        p = Pool(cpu_count())
        x = set(p.map(sociableNumTest, range(2, int(args[1]))))
        x.remove(None)
        print sorted(x)


if __name__ == "__main__":
    x = time.time()
    main()
    print time.time() - x
    
