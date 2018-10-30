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
    5:(lambda x : (x != 7) * ((x - 7 + deConvert5[(x % 3) + (x % 5)])/4) + (x + 28)/60)
}
#5:(lambda x : (x != 0) * ((x - 7 + convert5[x % 8] + (2 * (8 / x)))/4))

conversion = 5
convert = conversions[conversion]
deConvert = deConversions[conversion]
primes = [i for i in xrange(2, 1000) if (i % 2 != 0) and (i % 3 != 0) and (i % 5 != 0)]
lp = [0]
for i in primes:
    if i % 17 == 0:
        lp.append(deConvert(i))
        print deConvert(i),"-->", i, deConvert(i)-lp[-2]
#for i in xrange(20):
#    print i, (4 * i) + 7,  (4 * i) + 7 - primes[i]
#for x in range(10):
#        print x, convert5[x % 8],  ( (x % 8) - (x % (2 + (2 * (x /8)))))
#for i in xrange(2000):
#        c = convert(i)
#        d = deConvert(c)
        #print i, "-->", c, (4 * i) + 7 - c
        #print i, c,  d#, d == i, i - d, (c + 28)/60, (c + 28)/60 == i - d#, c % 8, c % 7, c % 6, c % 5, c % 4,c % 3  #, #primes[i],  c==primes[i], c % 8, d, (4 * i) - c + 7 
        #print i, c, d
#        if i != d:
#                print i, d