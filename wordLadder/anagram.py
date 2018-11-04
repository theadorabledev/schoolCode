#! /usr/bin/python
#Use: python anagram.py n
import sys
import time

def main():
    args = sys.argv[1:]
    n = args[0]
    dictAll = open("dictall.txt", "r")
    global theDictionary
    theDictionary = set(dictAll.read().splitlines())
    c = {i:0 for i in xrange(30)}
    for word in theDictionary:
        c[len(word)] +=1
    #print c
    theDictionary = {word for word in theDictionary if len(word) == int(n)}
    anagrams = {}
    for word in theDictionary:
        if tuple(set(word)) in anagrams:
            anagrams[tuple(set(word))].add(word)
        else:
            anagrams[tuple(set(word))] = set([word])
    l =  len(anagrams[max(anagrams, key=lambda k: len(anagrams[k]))])
    print l
    print "Largest anagram sets of word length", n
    for s in anagrams:
        if len(anagrams[s]) == l:
            print anagrams[s]




if __name__ == "__main__":
    x = time.time()
    main()
    print "Completed in", time.time() - x, "seconds."
