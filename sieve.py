#! /usr/bin/python2.7
import time
from math import sqrt, log
def sieve(n):
    """ Main function that performs incremental sieve of eratosthenes to get nth prime. """
    if n < 100:
    	primes=[True for i in xrange(2,1002)]
    else:
	primes=[True for i in xrange(2, pi(n))]
    accumulativeLength = len(primes)
    sieveSub(primes)
    while True:
        if n > len([i for i, z in enumerate(primes) if z]):
            primes += [True for i in xrange(2, accumulativeLength)]
            accumulativeLength *= 2
            print accumulativeLength
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


        
def pi(n):
    i = 1000
    while n > (i/log(i)):
	i += 1
    return i

def p(m, n):
    #logarithmic integral
    #https://en.wikipedia.org/wiki/Prime-counting_function#The_Meissel%E2%80%93Lehmer_algorithm
    
if __name__ == "__main__":
    print p(10000)
    x=time.time()
    sieve(10000)
    print time.time()-x
    
    
