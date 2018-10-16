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
def sociableNumTest(num, length=4):
    """ Checks if the number is a sociable number with length under 4. """
    nums = [primeFactorsSum(num, num%2)]
    for i in xrange(length):

        nums.append(primeFactorsSum(nums[-1],nums[-1]%2))
        if nums[0] == nums[-1]:
            return nums
    return None
def primeFactorsSum(n, increase):
    """ Returns the sum of the factors. """
    return sum(set(reduce(list.__add__, ([i, n/i] for i in xrange(1, int(sqrt(n))+1, increase+1) if (n % i == 0))))) - n
    #return {i for i in xrange(1, n) if (n % i == 0) }
def main():
    args = sys.argv[1:]
    if args[0] != "--sociable":
        
        p = Pool()
        x = set(p.map(test, range(2, int(args[0]))))
        x.remove(None)
        print sorted(x)
    else:
        for i in xrange(2,int(args[1])):
            print sociableNumTest(i)
        #p = Pool()
        #x = set(p.map(sociableNumTest, range(2, int(args[1]))))
        #x.remove(None)
        #print sorted(x)
        #print sociableNumTest(int(args[1]))
    #print findAmicableNums(2, int(args[0]))
if __name__ == "__main__":
    x = time.time()
    main()
    print time.time() - x
    
