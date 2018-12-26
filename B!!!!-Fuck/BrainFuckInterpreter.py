import argparse
def parseInput(string, fileA=False, ook=False):
    f = ""
    if fileA:
        f = open(string, "r").read().rstrip().replace(" ","").replace('\r', '').replace('\n', '')
    if ook:
        f = f.strip("\n").lower().replace("ook","").replace(" ","")
        f = "".join(["(" + f[i] + f[i+1] + ")" for i in xrange(0, len(f) - 1, 2)])
        f = f.replace("(.?)", ">").replace("(?.)","<").replace("(..)", "+").replace("(!!)", "-").replace("(!.)", ".").replace("(.!)", ",").replace("(!?)", "[").replace("(?!)", "]")
    f = "".join([c for c in f if c in {">", "<", "+", "-", ".", ",", "[", "]"}])
    return f
def findBraces(args):
    stack = []
    bracePairs = {}
    for i in xrange(len(args)):
        if args[i] == "[":
            stack.append(i)
        elif args[i] == "]":
            a = stack.pop(-1)
            bracePairs[a] = i
            bracePairs[i] = a
    return bracePairs
            
def interpret(args):
    
    array = [0] * 30000
    pos = 0
    loops = []
    rLoops = []
    braces = findBraces(args)
    i = 0   
    #print array[0:20], pos
    while i < len(args):
        arg = args[i]
        if arg == ">":
            pos += 1
        elif arg == "<":
            pos -= 1
        elif arg == "+":
            array[pos] += 1
        elif arg == "-":
            array[pos] -= 1
        elif arg == ".":
            print chr(array[pos]),
        elif arg == ",":
            array[pos] = ord(input("->"))
        elif arg == "[":
            if not array[pos]:
                i = braces[i]
        elif arg == "]":
            if array[pos]:
                i = braces[i]  
        i += 1
        #print array[0:20], pos, arg
def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("BF", help="BF String or name of BF file.")
    parser.add_argument("-f", "--file", help="Read from file?", action="store_true")
    parser.add_argument("-o", "--ook", help="For the librarians?", action="store_true")
    args = parser.parse_args()    
    interpret(parseInput(args.BF, fileA=args.file, ook=args.ook))
        
    print ""    
    #interpret("+++++[->>>+<<<]>>>.")
if __name__ == "__main__":
    main()