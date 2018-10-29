import sys
import time
ALPHABET = "abcdefghijklmnopqrstuvwxyz"
INFINITY = float("inf")

def main():
    args = sys.argv[1:]
    dictAll = open("dictall.txt", "r")
    global theDictionary
    theDictionary = set(dictAll.read().splitlines())
    connections = returnDict(args[0])
    inputFile = open(args[0], "r").read().splitlines()
    answers = []
    for line in inputFile:
        start, end = line.split(",")
        answers.append(djikstra(connections, start, end))
    print answers
    #answer = djikstra(connections, args[0], args[1])
    #if len(answer) == 1:
    #    answer = [args[0]] + answer
    #print " -> ".join([step.upper() for step in answer])
    #print ", ".join([step for step in answer])
    
def getNeighbors(word):
    words = set()
    for i in xrange(len(word)):
        for letter in ALPHABET:
            if letter != word[i] and (word[:i] + letter + word[i+1:]) in theDictionary:
                words.add(word[:i] + letter + word[i+1:])
    #print count
    return words
    
def returnDict(argWord):
    return {word:getNeighbors(word) for word in theDictionary if len(word)==len(argWord)}

def djikstra(connections, startNode, endNode):
    valueDict = {i:INFINITY for i in connections}
    chainDict = {i:[] for i in connections}
    lastNode = {i: None for i in connections}
    valueDict[startNode] = 0
    unknown = {i for i in connections}
    known = set()
    while known != unknown:
        unseen = unknown - known
        curNode = min(unseen , key = valueDict.get)
        known.add(curNode)
        for node in connections[curNode]:
            if valueDict[node] > valueDict[curNode] + 1:
                valueDict[node] = valueDict[curNode] + 1
                lastNode[node] = curNode
    order = [endNode]
    curNode = lastNode[endNode]
    while curNode:
        order.append(curNode)
        curNode = lastNode[curNode]
    if len(order) == 1:
        order.append(startNode)
    return order[::-1]#lastNode#valueDict



if __name__ == "__main__":
    main()
