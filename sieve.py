#! /usr/bin/python2.7
import time
from math import sqrt, log
import sys
def sieve(n):
    """ Main function that performs incremental sieve of eratosthenes to get nth prime. """
    if n == 1:
        return 2
    elif n < 200:
    	primes = [True] * 1000
    else:	
	primes = [True] * (pi(n)/2)# pi(n) is divided by 2 becuase even numbers are automatically not prime
	sieveSub(primes)
	#x = time.time()
	#print test(primes, n)
	#print time.time() - x
    return test(primes, n)# ([i for i, z in enumerate(primes) if z][n-2] * 2) + 3
def sieveSub(primes):
    """ Subprocess that performs the sieve operation. """
    accumulativeLength=len(primes)
    for i in range(int(sqrt(accumulativeLength))):
        if primes[i]:
            #((2 * i) + 3) is converting between indexes and odd number based system
            for x in range((((2 * i) + 3) ** 2 - 3) / 2, accumulativeLength, (2 * i)+3):
                primes[x] = False


        


def pi(n):
    """ Returns apprximatio of what nth prime will be. """
    #https://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
    return int((n * (log(n) + log(log(n)) -1 + ((log(log(n)) -2 )/log(n)) -( ((log(log(n)) ** 2) - (6 * log(log(n))) + 11 ) /(2* (log(n) ** 2)))  + (1/ (log(n) **2)))) * 1.05)

    
def test(primes, n):
	l = len(primes)
	numPrimes = primes.count(True)
	calc = numPrimes - n
	for i in range(l-1 , 0, -1):
		if primes[i]:
			calc -= 1
		if calc == -2:
			return (2*i)+3

if __name__ == "__main__":
    args = sys.argv[1:]
    for i in args:
        try:
            x = time.time()
            print "Prime number", str(i) ,"is",str(sieve(int(i)))
            print "Calculated in", str(time.time() - x), "seconds.\n"
        except ValueError:
            pass
    
