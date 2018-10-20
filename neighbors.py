import sys
ALPHABET = "abcdefghijklmnopqrstuvwxyz"

def main():
    args = sys.argv[1:]
    dictAll = open("dictall.txt", "r")
    global theDictionary
    theDictionary = set(dictAll.read().splitlines())
    #print theDictionary
    connections = returnDict(args[0])
def getNeighbors(word):
    words = set()
    count = 0
    for i in xrange(len(word)):
        temp = ""
        for letter in ALPHABET:
            if letter != word[i] and (word[:i] + letter + word[i+1:]) in theDictionary:
                count += 1
                words.add(word[:i] + letter + word[i+1:])
    #print count
    return words
    
def returnDict(argWord):
    return {word:getNeighbors(word) for word in theDictionary if len(word)==len(argWord)}
if __name__ == "__main__":
    main()
