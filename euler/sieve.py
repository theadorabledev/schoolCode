#! /usr/bin/python2.7
import time
from math import sqrt, log
import sys
convert5 = [0, 0, 2, 2, 4, 4, 2, 4]
deConvert5 = {3:0, 4:2, 5:4, 6:2, 2:4}
#5:(lambda x : 7 + (x != 0) * ((4 * x) - (convert5[x % 8]) - (2 * (x / 8))))
conversions = {
        2:(lambda x : (2*x) + 3 ), 
    3:(lambda x : (3*x) + 5 - (x % 2)),
    5:(lambda x : 7 + (x != 0) * ((4 * x) - (convert5[x % 8]) - (2 * (x / 8))))
}
deConversions = {
        2:(lambda x : (x - 3) / 2), 
    3:(lambda x : (x - 5 + (x % 2))/3),
    5:(lambda x : (x != 7) * ((c - 7 + deConvert5[(x % 3) + (x % 5)])/4) + (x + 28)/60)
}
conversion = 3
convert = conversions[conversion]
deConvert = deConversions[conversion]
steps3Func = [
        (lambda x : (((2 * x) - 1)/ 3)) ,
        (lambda x : (((4 * x) - 1)/ 3))
        ]
def sieve(n, l=False):
	""" Main function that performs optimized sieve of eratosthenes to get nth prime. """
	#python -m cProfile sieve.py 1000000
	if n == 1:
		return 2
	elif n == 2:
		return 3
	elif n < 200:
		primes = [True] * 400
	else:	

		primes = [True] * (pi(n)/conversion)# pi(n) is divided by 2 becuase even numbers are automatically not prime
	accumulativeLength=len(primes)

	for i in xrange(deConvert(int(sqrt(convert(accumulativeLength))))):

                steps = 2 * convert(i)
                stepA = steps3Func[i % 2](convert(i))
                stepB = steps - stepA
		if primes[i]:
			primes[deConvert(convert(i) ** 2)] = False
			for x in xrange(deConvert(convert(i) ** 2), accumulativeLength - steps, steps):
				primes[x + stepA] = False
				primes[x + steps] = False
	if l:
		return primes
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
		if calc == -3:
			return convert(i)




if __name__ == "__main__":
	args = sys.argv[1:]
	x = time.time()  
	answer = str(sieve(int(args[0])))
	print "Prime number", str(args[0]) ,"is", answer

	
	print "Calculated in", str(time.time() - x), "seconds.\n"
		

    
