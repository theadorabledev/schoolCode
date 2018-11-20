#!/usr/bin/env python3


def palindrome(s):
    """
    if len(s) == 0:
        return ""
    if len(set(s)) == len(s):
        return s[0]
    lp = 0
    palindrome = ""
    for i in range(len(s) - 1):
        p = 0
        for j in range(min(i + 1 , len(s) - i)):
            if s[i + j] == s[i - j]:
                #print s[i + j], s[i - j]
                p += 1
            else:
                break
        if p > lp:
            lp = p
            palindrome = s[i-p + 1:i+p]
            #print s[i]
    return palindrome
            
    """
    longest = ""
    longestLen = 0
    
    for x in range(len(s)):
        for xR in range(len(s)-1,x-1,-1):
            print(s[x:xR+1],s[xR::-1])
            if s[x:xR+1] == s[xR::-1] and len(s[x:xR+1]) > longestLen:
                print(s[x:xR+1])
                longest = s[x:xR+1]
                longestLen = len(s[x:xR+1])
                
    return longest
    

print(palindrome("ttacocat"))
if __name__ == "__main__":
    with open("PalindromeSSIN.txt", "r") as f:
        n = int(f.readline())
        for i in range(n):
            print(palindrome(f.readline().strip()))
