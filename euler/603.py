
import sieve as ps
import itertools
import time
irange = lambda stop: iter(itertools.count().next, stop)
convert = ps.convert

def gen(n, l=1):
	N = len(n)
	NN = N * l
	t = (((10 ** NN) - 1) / 9)
	#s = sum([int(n[i % N]) * (i + 1) * (((10 ** (NN - i)) - 1) / 9) for i in n])
	for i in irange(NN):
		yield int(n[i % N]) * (i + 1) * t
		t /= 10

def S(n, l=1):
	""" Returns the sum of all contigous integer-substrings that can be formed from the integer n. The substrings need not be distinct. """
	#https://stackoverflow.com/questions/4487748/sum-of-substring-of-a-number Beautiful algorithm!
	#return sum(gen(n, l=l))
	s = 0 # int(n)
	N = len(n)
	NN = N * l
	t = (((10 ** NN) - 1) / 9)
	#s = sum([int(n[i % N]) * (i + 1) * (((10 ** (NN - i)) - 1) / 9) for i in n])
	for i in irange(NN):
		s += int(n[i % N]) * (i + 1) * t
		t /= 10
		#print int(n[i % len(n)]), (i + 1), (((10 ** (NN - i)) - 1) / 9)
	return s
	
	
def C(n, k):
	""" Returns the integer formes by concatenating k copies of P(n) together. """
	p = P(n)
	return S(p, l=k)
def P(n):
	""" 
	Returns the integers formed by concatenating the first n primes together.
	
	Ex: P(7) returns 2347111317
	"""
	
	p = "23"
	x = 2
	primes = ps.sieve(n, l=True)
	print "Primes found"
	for i in xrange(len(primes)):
		if primes[i]:
			p += str(convert(i))
			x += 1
		if x == n:
			break
	print "Primes isolated"
	return p
t = time.time()
print S("123"), S("123123"), S("123", l=2)
#c = C(10 ** 1, 10 ** 3)
#c = C(10 ** 3, 10 ** 6)
c = C(10 ** 6, 10 ** 12)
print c % ((10 ** 9) + 7)
#p = P(10 ** 3)
#t = time.time()
#x = (10 ** 18) % (10 ** 6)
print time.time() - t

