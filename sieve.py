#! /usr/bin/python2.7
import time
from math import sqrt, log
import sys
def sieve(n):
	""" Main function that performs incremental sieve of eratosthenes to get nth prime. """
	#python -m cProfile sieve.py 1000000
	if n == 1:
		return 2
	elif n < 200:
		primes = [True] * 1000
	else:	
		primes = [True] * (pi(n)/2)# pi(n) is divided by 2 becuase even numbers are automatically not prime
	#print primes
	sieveSub(primes)
	#x = time.time()
	#print nthTrue(primes, n)
	#print time.time() - x
	return nthTrue(primes, n)
	#return  ([i for i, z in enumerate(primes) if z][n-2] * 2) + 3
def sieveSub(primes):
    """ Subprocess that performs the sieve operation. """
    accumulativeLength=len(primes)
    for i in xrange(int(sqrt(accumulativeLength))):
		if primes[i]:
			#((2 * i) + 3) is converting between indexes and odd number based system
			for x in xrange((((2 * i) + 3) ** 2 - 3) / 2, accumulativeLength, (2 * i)+3):
				primes[x] = False


        


def pi(n):
    """ Returns apprximatio of what nth prime will be. """
    #https://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
    return int((n * (log(n) + log(log(n)) -1 + ((log(log(n)) -2 )/log(n)) -( ((log(log(n)) ** 2) - (6 * log(log(n))) + 11 ) /(2* (log(n) ** 2)))  + (1/ (log(n) **2)))) * 1.05)

    
def nthTrue(primes, n):
	l = len(primes)
	numPrimes = primes.count(True)
	calc = numPrimes - n
	for i in xrange(l-1 , 0, -1):
		if primes[i]:
			calc -= 1
		if calc == -2:
			return (2*i)+3

if __name__ == "__main__":
	args = sys.argv[1:]
	x = time.time()
	print "Prime number", str(args[0]) ,"is",str(sieve(int(args[0])))
	print "Calculated in", str(time.time() - x), "seconds.\n"
		

    