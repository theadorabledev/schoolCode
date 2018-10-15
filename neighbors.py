import sys
ALPHABET = "abcdefghijklmnopqrstuvwxyz"

def main():
    args = sys.argv[1:]
    dictAll = open("dictall.txt", "r")
    global theDictionary
    theDictionary = set(dictAll.read().splitlines())
    #print theDictionary
    getNeighbors(args[0])
def getNeighbors(word):
    count = 0
    for i in xrange(len(word)):
        temp = ""
        for letter in ALPHABET:
            if letter != word[i] and (word[:i] + letter + word[i+1:]) in theDictionary:
                count += 1
    print count
    
    
if __name__ == "__main__":
    main()
