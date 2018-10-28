convert5 = [0, 0, 2, 2, 4, 4, 2, 4]
#5:(lambda x : 7 + (x != 0) * ((4 * x) - (convert5[x % 8]) - (2 * (x / 8))))
conversions = {
    2:(lambda x : (2*x) + 3 ), 
    3:(lambda x : (3*x) + 5 - (x % 2)),
    5:(lambda x : 7 + (x != 0) * ((4 * x) - ( (x % 8) - (x % (2 + (2 * (x /8))))) - (2 * (x / 8)))),
    55:(lambda x : 7 + (x != 0) * ((4 * x) - (convert5[x % 8]) - (2 * (x / 8))))
}
deConversions = {
    2:(lambda x : (x - 3) / 2), 
    3:(lambda x : (x - 5 + (x % 2))/3),
    5:(lambda x : (x != 7) * (float(c - 7 )/4))
}
#5:(lambda x : (x != 0) * ((x - 7 + convert5[x % 8] + (2 * (8 / x)))/4))

conversion = 5
convert = conversions[conversion]
deConvert = deConversions[conversion]
primes = [i for i in xrange(2, 10000) if (i % 2 != 0) and (i % 3 != 0) and (i % 5 != 0)]
#for i in xrange(20):
#    print i, (4 * i) + 7,  (4 * i) + 7 - primes[i]
#for x in range(10):
#        print x, convert5[x % 8],  ( (x % 8) - (x % (2 + (2 * (x /8)))))
        
for i in xrange(20):
        #c = convert(i)
        c = conversions[55](i)
        d = deConvert(c)
        print i, "-->", c, (4 * i) + 7 - c
        #print i, c , primes[i],  c==primes[i]#d, (4 * i) - c + 7 
        #print i, c, d