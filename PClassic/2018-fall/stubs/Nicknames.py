def findLongestSubstring(s):
    pass

#Do not modify below this line
if __name__ == '__main__':
    with open('NicknamesIN.txt', 'r') as f:
        while True:
            s = f.readline().strip()
            if s == '':
                break
            print(findLongestSubstring(s))
