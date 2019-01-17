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
def Huffman(event):
    """ Returns the best encoding given the event. """
    orig = event[::]
    event = [Node(value=p) for p in event]
    event.sort(key=lambda k : k.value)
    while len(event) > 1:
        a = event.pop(0)#smaller
        b = event.pop(0)#larger
        event.append(Node(left=b, right=a))
        event.sort(key = lambda k : k.value)
    root = event[0]
    root.setEncoding()
    r = root.getAllEncodings()
    r.sort(key = lambda k : len(k))
    rPos = orig[::-1]
    #print rPos, r
    #print [rPos[i] * len(r[i]) for i in xrange(len(r))]
    return [r, Shannon([rPos[i] * len(r[i]) for i in xrange(len(r))])]
def group(p, g):
    """ Groups the possiblities together g times. """
    theDict = [str(p1) for p1 in p]
    #print theDict
    orig = p[::]
    orig2 = p[::]
    for i in xrange(g - 1):
        a = []
        for p2 in orig:
            for p1 in theDict:
                a.append(str(p1) +"-"+ str(p2))
        theDict = a
        #print theDict
    p = []
    for p1 in theDict:
        t = 1
        for n in p1.split("-"):
            t *= float(n)
        p.append(t)
    p.sort()
    return p
def S1(prob):
    
    return log(1/prob,2) 
def Shannon(event):
    s = sum(event)
    #print s
    return abs(sum([x * log(1/(float(x)/s), 2) for x in event])/sum(event)) 
def getBestEncoding(p, g=1):
    """ Given a list of possibilities (p) and the number of consecutive times information is sent(g), returns the best encoding and the average number of bits. """
    print "\nGiven :", p, "with grouping", g,"."
    print "Theoretical best average number of bits:", Shannon(p)
    p = group(p, g)
    result = Huffman(p[::])
    print "Best encoding :", result[0]
    print "Average number of bits :", result[1]/g
def main():
    getBestEncoding([.75, .25], g=2)
if __name__ == "__main__":
    main()
