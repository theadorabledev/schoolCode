#! /usr/bin/python2.7
import time
from math import sqrt
def sieve(n):
    """ Main function that performs incremental sieve of eratosthenes to get nth prime. """
    primes=[i for i in xrange(2,1000)]
    accumulativeLength = len(primes)
    sieveSub(primes)
    
    last = primes[-1]
    while True:
        if n > len(primes):
            print len(primes)
            #newLength = (n * accumulativeLength)/len(primes)
            primes += [i for i in xrange(accumulativeLength, accumulativeLength * 2)]#accumulativeLength+newLength)]
            print last, primes[accumulativeLength]
            last=primes[-1]
            accumulativeLength *= 2
            sieveSub(primes)
        else:
            break
            
        
    print primes[n-1]
 
def sieveSub(primes):
    """ Subprocess that performs the sieve operation. """
    for i in primes:
        for x in primes:
            if (x % i == 0) and (x != i):
                primes.remove(x)

            
if __name__ == "__main__":
    x=time.time()
    sieve(10000)
    print time.time()-x
