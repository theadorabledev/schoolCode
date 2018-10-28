#! /usr/bin/python2.7
import time
from math import sqrt, log
import sys
def sieve(n):
	""" Main function that performs optimized sieve of eratosthenes to get nth prime. """
	#python -m cProfile sieve.py 1000000
	if n == 1:
		return 2
	elif n < 200:
		primes = [True] * 1000
	else:	
		primes = [True] * (pi(n)/2)# pi(n) is divided by 2 becuase even numbers are automatically not prime
	accumulativeLength=len(primes)

	for i in xrange(int(sqrt(accumulativeLength))):
		
		if primes[i]:
			#p = Pool()
			
			#p.map(lambda x : primes.__setitem__(x, False), xrange((((3 * i) - (i %2) + 5) ** 2 - 3) / 2, accumulativeLength, (3 * i) - (i %2) + 5))
			for x in xrange((((3 * i) - (i %2) + 5) ** 2 - 3) / 2, accumulativeLength, (3 * i) - (i %2) + 5):
				primes[x] = False
				#primes.__setitem__(x, False)
	return nthTrue(primes, n)

def pi(n):
    """ Returns apprximatio of what nth prime will be. """
    #https://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
    return int((n * (log(n) + log(log(n)) -1 + ((log(log(n)) -2 )/log(n)) -( ((log(log(n)) ** 2) - (6 * log(log(n))) + 11 ) /(2* (log(n) ** 2)))  + (1/ (log(n) **2)))) * 1.05)

    
def nthTrue(primes, n):
	""" Gets the nth true in the list. """
	l = len(primes)
	numPrimes = primes.count(True)
	calc = numPrimes - n
	#map((lambda i: (calc = calc - 1) if primes[i] else pass), xrange(l-1 , 0, -1))
	for i in xrange(l-1 , 0, -1):
		if primes[i]:
			calc -= 1
		if calc == -2:
			return (3 * i) - (i %2) + 5


def sieveSet(n):
	primes = {i for i in xrange(pi(n)/2)}
	accumulativeLength=len(primes)
	for i in xrange(int(sqrt(accumulativeLength))):
		if i in primes:
			for x in xrange((((3 * i) - (i %2) + 5) ** 2 - 3) / 2, accumulativeLength, (3 * i) - (i %2) + 5):
				primes.discard(x)
	for i in xrange(n-2):
		primes.pop()
	return (2 * primes.pop()) + 3

def sieveDict(n):
	primes = {i:True for i in xrange(pi(n)/2)}
	accumulativeLength=len(primes)
	for i in xrange(int(sqrt(accumulativeLength))):
		if primes[i]:
			for x in xrange((((3 * i) - (i %2) + 5) ** 2 - 3) / 2, accumulativeLength, (3 * i) - (i %2) + 5):
				primes[x] = False
	return (2 * [i for i in primes.keys() if primes[i]][n-2]) + 3
	return (2 * primes.pop()) + 3	
if __name__ == "__main__":
	args = sys.argv[1:]
	x = time.time()  
	print "Prime number", str(args[0]) ,"is",str(sieve(int(args[0])))
	#print "Prime number", str(args[0]) ,"is",str(sieveSet(int(args[0]))) 
	#print "Prime number", str(args[0]) ,"is",str(sieveDict(int(args[0]))) #str(sieve(int(args[0])))
	
	print "Calculated in", str(time.time() - x), "seconds.\n"
		

    
