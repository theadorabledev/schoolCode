#! /usr/bin/python2.7
def sieve(n):
    """ Main function that performs incremental sieve of eratosthenes to get nth prime. """
    primes=[i for i in xrange(2,1000)]
    curLength=len(primes)
    sieveSub(primes)
    while True:
        if n > len(primes):
            primes += [i for i in xrange(2+curLength, curLength*2)]
            curLength=len(primes)
            sieveSub(primes)
        else:
            break
            
        
    print primes[n-1]
 

def sieveSub(primes):
    """ Subprocess that performs the sieve operation. """
    for i in primes:
        for x in primes:
            if (x % i == 0) and (x != i):
                primes.remove(x)
if __name__ == "__main__":
    sieve(10000)
