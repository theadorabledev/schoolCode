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
    
    #print getAnagrams("binary")
    anagrams = returnDict(int(n))
    longest = max(anagrams, key=lambda k: len(anagrams[k]))
    l = len(anagrams[longest])
    answer = []
    checked = set()
    for word in anagrams:
        if len(anagrams[word]) == l:
            a = anagrams[word]
            if not (a & checked):
                answer.append(tuple(anagrams[word]))
            for w in anagrams[word]:
                checked.add(w)
    print "Largest anagram sets of word length", n
    for s in answer:
        print s
def getAnagrams(argWord):
    anagrams = set()
    for word in theDictionary:
        if (set(word) == set(argWord)) :
            anagrams.add(word)

    return anagrams
    
def returnDict(argLen):
    return {word:getAnagrams(word) for word in theDictionary }


if __name__ == "__main__":
    x = time.time()
    main()
    print "Completed in", time.time() - x, "seconds."
