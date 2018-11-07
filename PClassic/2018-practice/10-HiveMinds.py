import sys

def main():
    args = sys.argv[1:]
    lines = open(args[0], "r").readlines()
    
    #lines = open("10-in.txt", "r").readlines()
    numOps = int(lines.pop(0))
    ops = [tuple([int(i) for i in line.strip().split(" ")]) for line in lines]
    hives = []
    for op in ops:
        if {op[1]} not in hives:
            hives.append({op[1]})
        if {op[2]} not in hives:
            hives.append({op[2]})
    for op in ops:
        typeOp, x, y = op
        xHive, yHive = set(), set()
        for hive in hives:
            if x in hive:
                xHive = hive
            if y in hive:
                yHive = hive
        if typeOp == 0:
            xHive |= yHive
            hives.remove(yHive)
        elif typeOp == 1:
            xHive.discard(x)
            yHive.add(x)
        else:
            if x in yHive:
                print True
            else:
                print False
if __name__ == "__main__":
    main()