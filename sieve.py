#! /usr/bin/python2.7
import time
from math import sqrt
def sieve(n):
    """ Main function that performs incremental sieve of eratosthenes to get nth prime. """
    primes=[True for i in xrange(2,1000)]
    accumulativeLength = len(primes)
    sieveSub(primes)
    while True:
        if n > len([i for i, z in enumerate(primes) if z]):
            primes += [True for i in xrange(2, accumulativeLength)]
            accumulativeLength *= 2
            sieveSub(primes)
        else:
            break
            
    print  [i for i, z in enumerate(primes) if z][n+1] 
 
def sieveSub(primes):
    """ Subprocess that performs the sieve operation. """
    accumulativeLength=len(primes)
    for i in range(2,accumulativeLength):
        if i:
            for x in range(i**2, accumulativeLength, i):
                primes[x]=False

            
if __name__ == "__main__":
    x=time.time()
    sieve(1000000)
    print time.time()-x
