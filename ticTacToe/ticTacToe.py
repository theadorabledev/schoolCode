import random
class ticTacToe:
    winners = [[0, 1, 2], [3, 4, 5], [6, 7, 8], [0, 3, 6], [1, 4, 7], [2, 5, 8], [0, 4, 8], [2, 4, 6]]    
    def __init__(self):
        self.grid = "_________"
        self.turn = "X"
        #self.xpossible = {(i: "X") for i in range(9)}
        #self.opossible = {(i: "O") for i in range(9)}
    def findPosCombs(self):
        stack = []
        count = 0
        tried = set()
        boards = set()
        games = set()
        #root = (0, "X")
        root = 0
        stack.append((self.grid, frozenset(tried), root, self.turn))
        while stack:
            if self.isWinner() or self.isDraw():
                
                games.add(tuple(stack))
                last = stack.pop(-1)
                tried = set(last[1])
                turn = last[3]
                self.grid = last[0]
                #tried |= {last[2]}
                
            else:
                pos = (set(range(9)) - tried - {root}).pop()
                stack.append((self.grid, frozenset(tried), root, self.turn))
                self.grid = self.grid[:pos] + self.turn + self.grid[pos + 1:]
                root = pos
                tried.add(root)
                boards.add(self.grid)
                self.turn = "X" if self.turn == "O" else "O"
                
        x = 2     
    def find2(self):
        basicSet = {range(0, 9)}
        
        
    
    def isWinner(self):
        for w in self.winners:
            if self.grid[w[0]] == self.grid[w[1]] == self.grid[w[2]] != "_":
                return True
        return False
    def isDraw(self):
        return self.grid.count("_") == 0 and not self.isWinner()

t = ticTacToe()
t.findPosCombs()