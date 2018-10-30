#! /usr/bin/python
import sys
ALPHABET = "abcdefghijklmnopqrstuvwxyz"
#ALPHABET = set(ALPHABET)
INFINITY = float("inf")

def main():
    args = sys.argv[1:]
    dictAll = open("dictall.txt", "r")
    global theDictionary
    theDictionary = set(dictAll.read().splitlines())
    #connections = returnDict()
    grandConnections = {}
    #print "tail" in connections
    inputFile = open(args[0], "r").read().splitlines()
    answers = []
    wordLens = set()
    startWords = set()
    paths = {}
    for line in inputFile:
        start, end = line.split(",")
        wordLens.add(len(start))
        startWords.add(start)
    for l in wordLens:
        grandConnections[l] = returnDict(l)
    for word in startWords:
        paths[word] = djikstra(grandConnections[len(word)], word, "fish")
    
    for line in inputFile:
        start, end = line.split(",")    
        x = len(start) == len(end)
        #print connections[end]
        #paths[start] = djikstra(grandConnections[len(start)], start, end)
        answers.append(",".join(getOrder(paths[start], start, end)) + "\n")
        
        #answers.append(djikstra(connections, start, end))
    print answers
    open(args[1], "w").writelines(answers)
    #answer = djikstra(connections, args[0], args[1])
    #if len(answer) == 1:
    #    answer = [args[0]] + answer
    #print " -> ".join([step.upper() for step in answer])
    #print ", ".join([step for step in answer])
    
def getNeighbors(word):
    words = set()
    for i in xrange(len(word)):
        for letter in xrange(len(ALPHABET) - 1):
        #for letter in ALPHABET:
            words.add(word[:i] + ALPHABET[letter] + word[i+1:])

    return words & theDictionary
    
def returnDict(argLen):
    return {word:getNeighbors(word) for word in theDictionary if len(word)==argLen}

def djikstra(connections, startNode, endNode):
    valueDict = {i:INFINITY for i in connections}
    chainDict = {i:[] for i in connections}
    lastNode = {i: None for i in connections}
    valueDict[startNode] = 0
    unknown = {i for i in connections}
    while unknown:

        curNode = min(unknown , key = valueDict.get)
        unknown.discard(curNode)
        #if curNode == endNode:
        #    break
        for node in connections[curNode]:
            if valueDict[node] > valueDict[curNode] + 1:
                valueDict[node] = valueDict[curNode] + 1
                lastNode[node] = curNode

    return lastNode


def getOrder(lastNode, startNode, endNode):
    order = [endNode]
    curNode = lastNode[endNode]
    while curNode:
        order.append(curNode)
        curNode = lastNode[curNode]
    if len(order) == 1:
        order.append(startNode)
    return order[::-1]
if __name__ == "__main__":
    main()
