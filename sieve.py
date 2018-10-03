#! /usr/bin/python2.7
import time
from math import sqrt, log
def sieve(n):
    """ Main function that performs incremental sieve of eratosthenes to get nth prime. """
    if n < 100:
    	primes=[True for i in xrange(2,1002)]
    else:
	primes=[True for i in xrange(2, pi(n)/2)]
    accumulativeLength = len(primes)
    sieveSub(primes)

    #print primes        
    print  [i for i, z in enumerate(primes) if z][n+1] 
 
def sieveSub(primes):
    """ Subprocess that performs the sieve operation. """
    accumulativeLength=len(primes)
    for i in range(accumulativeLength):
        raw_input("->")
        print i, ((2*i)+3), primes[i]
        print primes[:20]
        #print [i for i, z in enumerate(primes[:20])]
        if primes[i]:
            print ((2 * i)+3, accumulativeLength, (2 * i)+3)
            for x in range((2 * i)+3, accumulativeLength, (2 * i)+3):
                #print x
                primes[x]=False
        print [str((2*i +3 )) + str(primes[i]) for i in range(20)]
        #print [i for i, z in enumerate(primes[:20])]


        


def pi(n):
    """ Returns apprximatio of what nth prime will be. """
    #https://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
    return int((n * (log(n) + log(log(n)) -1 + ((log(log(n)) -2 )/log(n)) -( ((log(log(n)) ** 2) - (6 * log(log(n))) + 11 ) /(2* (log(n) ** 2)))  + (1/ (log(n) **2)))) * 1.01)
if __name__ == "__main__":
    x=time.time()
    sieve(1000)
    print time.time()-x
    
    
