import sys
from edgeList import fileToData
def main():
	meta, connections = fileToData(sys.argv[1])
	myList = [0] * (meta[0] + 1)
	for num in connections:
		myList[num] = len(connections[num])		
	answer = " ".join([str(i) for i in myList])
	print answer[2:]	
if __name__ == "__main__":
	main()

	