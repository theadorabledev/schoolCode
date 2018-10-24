#! /usr/bin/python2.7
import time
from math import sqrt, log
import sys
import numpy as np

conversions = {2:(lambda x : (2*x) + 3 ), 3:(lambda x : (3*x) + 5 - (x % 2))}
deConversions = {2:(lambda x : (x - 3) / 2), 3:(lambda x : (x - 5 + (x % 2))/3)}
conversion = 3
convert = conversions[conversion]
deConvert = deConversions[conversion]
stepsFunc = [
        (lambda x : (((2 * x) - 1)/ 3)) ,
        (lambda x : (((4 * x) - 1)/ 3))
        ]

def sieve(n):
	""" Main function that performs optimized sieve of eratosthenes to get nth prime. """
	#python -m cProfile sieve.py 1000000
	if n == 1:
		return 2
	elif n < 200:
		primes = [True] * 1000
	else:	

		primes = [True] * (pi(n)/conversion)# pi(n) is divided by 2 becuase even numbers are automatically not prime
	accumulativeLength=len(primes)

	for i in xrange(int(sqrt(accumulativeLength))):

                steps = 2 * convert(i)
                stepA = stepsFunc[i % 2](convert(i))
                stepB = steps - stepA
                print i, convert(i), stepsA, stepsB
		if primes[i]:
			#p = Pool()
			#print i, convert(i), deConvert(convert(i))
			for x in xrange(deConvert(convert(i) ** 2), accumulativeLength, convert(i)):
				#print "i is not prime", i," : ", x, convert(x)
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
	#numPrimes = np.count_nonzero(primes == 1)
	numPrimes = primes.count(True)
	calc = numPrimes - n
	for i in xrange(l-1 , 0, -1):
		if primes[i]:
			calc -= 1
		if calc == -2:
			return convert(i)




if __name__ == "__main__":
	args = sys.argv[1:]
	x = time.time()  
	answer = str(sieve(int(args[0])))
	print "Prime number", str(args[0]) ,"is", answer

	
	print "Calculated in", str(time.time() - x), "seconds.\n"
		

    
