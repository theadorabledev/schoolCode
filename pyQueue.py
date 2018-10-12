class Pqueue:
    """ A python queue that returns the lowest priority item, with an optional option passign your own comparison functions."""
    def OrdinaryComparison(a,b):
        """ 
        The basic comparison function.
        
        All comparison functions passed should follow this format.
        """
        if a < b: 
            return -1
        if a == b: 
            return 0
        return 1

    def __init__(self, comparator = OrdinaryComparison):
        self.cmpFunc = comparator
        self.array=[]
    
    def push(self, data):
        """  Pushes element onto queue. """
        if not self.array:
            self.array.append(data)
        else:
            for i in range(len(self.array)):
                if i == (len(self.array) - 1) and self.cmpFunc(self.array[i], data) == -1:
                    self.array.append(data)
                    break                    
                if self.cmpFunc(self.array[i], data) != -1:
                    self.array.insert(i, data)
                    break
    def pop(self):
        """ Removes minimum element of queue. """
        if self.array:
            return self.array.pop(0)
        else:
            return None
    def peek(self):
        """ Shows minimum element of queue. """
        if self.array:
            return self.array[0]
        else:
            return None

