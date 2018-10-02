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

            
    print  [i for i, z in enumerate(primes) if z][n+1] 
 
def sieveSub(primes):
    """ Subprocess that performs the sieve operation. """
    accumulativeLength=len(primes)
    for i in range(2, accumulativeLength, 2):
        primes[i]=False
    for i in range(3, accumulativeLength,2):
        if i:
            for x in range(i**2, accumulativeLength, i):
                primes[x]=False


        


def pi(n):
    """ Returns apprximatio of what nth prime will be. """
    #https://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
    return int((n * (log(n) + log(log(n)) -1 + ((log(log(n)) -2 )/log(n)) -( ((log(log(n)) ** 2) - (6 * log(log(n))) + 11 ) /(2* (log(n) ** 2)))  + (1/ (log(n) **2)))) * 1.01)
if __name__ == "__main__":
    x=time.time()
    sieve(10000)
    print time.time()-x
    
    
