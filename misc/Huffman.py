from math import log
class Node:
    def __init__(self, value=0, left=None, right=None):
        if left and right:
            self.left = left
            self.right = right
            left.parent = right.parent = self
            self.value = left.value + right.value
        else:
            self.left = self.right = None
            self.value = value
        self.parent = None
        self.encoding = ""
    def setEncoding(self):
        """ Recursively set the encoding of the tree. """
        if self.left:
            self.left.encoding = self.encoding + "0"
            self.left.setEncoding()
        if self.right:
            self.right.encoding = self.encoding + "1"
            self.right.setEncoding()
    #bigger smaller
    def getAllEncodings(self):
        """ Returns all possible encodings in the tree. """
        children = []
        if self.left:
            children += self.left.getAllEncodings()
        if self.right:
            children += self.right.getAllEncodings()
        if not self.right and not self.left:
            children.append(self.encoding)
        return children
# Shannon info for one possibility given its probability def 
S1(prob):  
return log(1/prob,2) 
# Shannon info for an event, given list of probabilities: 
def S(event
):  
return sum([x * S1(x) for x in event]) 
def Huffman(possibilities):
    """ Returns the best encoding given the possibilities. """
    possibilities = [Node(value=p) for p in possibilities]
    possibilities.sort(key=lambda k : k.value)
    while len(possibilities) > 1:
        a = possibilities.pop(0)#smaller
        b = possibilities.pop(0)#larger
        possibilities.append(Node(left=b, right=a))
        possibilities.sort(key = lambda k : k.value)
    root = possibilities[0]
    root.setEncoding()
    r = root.getAllEncodings()
    r.sort(key = lambda k : len(k))
    return r
def group(p, g):
    """ Groups the possiblities together g times. """
    r = [p1 * p2 for p1 in p for p2 in p]
    r.sort()
    return r
def main():
    print Huffman(group([.75, .25], 2))
if __name__ == "__main__":
    main()
