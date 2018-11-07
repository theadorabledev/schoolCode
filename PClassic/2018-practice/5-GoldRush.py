import sys
def main():
    args = sys.argv[1:]
    l = [int(i.strip("]").strip("[").strip(",")) for i in args]
#    l = eval(str(args[0]))

    print "".join([str(i) for i in sorted(l, reverse=True)])
if __name__ == "__main__":
    main()