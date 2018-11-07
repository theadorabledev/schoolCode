def solve(subProblem):
    nums = []
    op = "+"
    for i in subProblem:
        if i.isdigit():
            nums.append(int(i))
        elif i != "(" and i != ")":
            op = i
    return {"+": lambda x, y: x + y,
            "-": lambda x, y: x - y,
            "*": lambda x, y: x * y,
            "/": lambda x, y: x / y,

        }[op](nums[0], nums[1])
def getSubProblems():
    ops = []
    for i in xrange(len(problem) - 1):
        ops.append(i)
        if problem[i] == "(":
            count += 1 
            for k in xrange(i + 1, len(problem) - 1):
                if problem[k] == ")":
                    print problem[i], problem[k], problem[i : k + 1]    

def main():
    print "hello world"
    problem = "( 2 + 2 ) - 1"
    print eval(problem)
    simpleProb = []
    problemList = problem.split(" ")
    print problemList
    count = 0
    for i in xrange(len(problemList) - 1):
        if problemList[i] == "(":
            count += 1 
            for k in xrange(i + 1, len(problemList) - 1):
                if problemList[k] == ")":
                    print problemList[i : k + 1]
                    print solve(problemList[i : k + 1])
                    simpleProb.append(solve(problemList[i : k + 1]))
    print simpleProb
if __name__ == "__main__":
    main()
