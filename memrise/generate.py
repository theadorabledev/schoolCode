import sys
if __name__ == "__main__":
    """ Hardcodes value into mem.py. python generate.py mac_addr dest_file. """
    args = sys.argv[1:]
    with open("mem.py", "r") as template:
        data = template.read()
        data = data.replace("(MAC_HERE)", args[0].replace(":", ""))
        with open(args[1], "w") as f:
            f.write(data)