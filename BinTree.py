from pprint import pprint
class BinTree():
    """ A recursive binary tree. """
    def __init__(self, A=None, data=None):
        self.root = None
        self.data = data
        self.right = None
        self.left = None
        if A:
            self.values = []
            [self.values.append(el) for el in A if el not in self.values]
            for i in self.values:
                self.insert(i)

    def insert(self, v):
        """ Inserts a value into the tree. """
        if self.data:
            if v < self.data:
                if self.left:
                    self.left.insert(v)            
                else:
                    self.left = BinTree(data=v)
            elif v > self.data:
                if self.right:
                    self.right.insert(v)
                else:
                    self.right = BinTree(data=v)
            else:
                print "In tree already"
        else:
            self.data = v
    def has(self, v):
        """ Returns true if the tree has the values, false otherwise. """

        if v < self.data and self.left:
            return self.left.has(v)
        elif v > self.data and self.right:
            return self.right.has(v)
        elif v == self.data:
            return True
        else:
            return False

    def getTree(self):
        """ Returns the tree as a large dictionary. """
        left = None
        right = None
        if self.left:
            left = self.left.getTree()
        if self.right:
            right = self.right.getTree()
        return {"Data": self.data, "Left": left, "Right": right}
    def clear(self):
        """ Deletes the tree. """
        while self.right or self.left:# self.data:
            if self.right:
                if not self.right.right and not self.right.left:
                    self.right = None
                else:
                    self.right.clear()
                
            if self.left:
                if not self.left.right and not self.left.left:
                    self.left = None
                else:
                    self.left.clear()
        self.data = None
    def get_ordered_list(self):
        """ Returns an ordered list of the values in the tree. """
        values = []
        if self.left:
            values += self.left.get_ordered_list()
        values.append(self.data)
        if self.right:
            values += self.right.get_ordered_list()
        return values
        
        
def test():
    """ A test of the BinTree class. """
    b = BinTree([7, 3, 9, 2, 4, 8, 10, 11, 12, 13, 14])
    pprint(b.getTree())
    b.insert(22)
    pprint(b.getTree())
    print b.has(9)
    print b.has(22)
    print b.has(45)
    print b.get_ordered_list()
    b.clear()
    pprint(b.getTree())

if __name__ == "__main__":
    test()
    