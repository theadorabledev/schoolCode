
import math
import sys
def getGCD(a, b):
    for i in xrange(a + 1, 0, -1):
        if not (a % i) and not (b % i):
            return i
            break
            
def main():
    args = sys.argv[1:]
    n, m = int(args[0]), int(args[1])
    theSum = sum([getGCD(i, n) for i in xrange(1, n+1)])
    print theSum % m

if __name__ == "__main__":
    main()
    
