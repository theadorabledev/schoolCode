#!/usr/bin/env python3


def proximity(l, m):
    pass


# DO NOT EDIT BELOW THIS LINE
if __name__ == "__main__":
    with open("ProximityIN.txt", "r") as f:
        while True:
            one, two = f.readline(), f.readline()
            if not two:
                break
            l = [int(x) for x in one.split(" ")]
            m = [int(x) for x in two.split(" ")]
            print((" ".join(str(x) for x in proximity(l, m))) + " ")
