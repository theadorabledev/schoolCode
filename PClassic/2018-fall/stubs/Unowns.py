#!/usr/bin/env python3
#from math import abs
def challenge(k, lst):
    answer = []
    for i in range(len(lst) - k + 1):
        a = lst[i: i+k ]
        #print i + k - 1, i
        #print lst[i + k - 1], lst[i]
        answer.append(max(a) - min(a))
        #answer.append(abs(lst[i + k - 1] - lst[i]))
        #answer.append(a[-1]-a[0])
        #print lst[i: i+k]
    return answer
    #pass

#print challenge(5, range(1, 3, 4, 2, 5, 2))
# DO NOT EDIT BELOW THIS LINE
if __name__ == "__main__":
    with open("UnownsIN.txt", "r") as f:
        for line in f:
            k, n = line.split(" ", 1)
            print(" ".join(str(x) for x in challenge(int(k), [int(x) for x in n.split(" ")])))
