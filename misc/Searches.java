// Sequential (Linear) Search
// useful for unsorted lists
// brute force search

public class Searches{

    // returns a array with the sequence [0, n).
    public static int[] makeData(int n){
	int[] ans = new int[n];
	for (int i = 0; i < n; i++){
	    ans[i] = i;
	}
	return ans;
    }

    public static void shuffle(int [] data){
	for (int i = 0; i < data.length; i++){
	    int r = (int) (Math.random() * (data.length  - i));
	    int temp = data[r];
	    data[r] = data[i];
	    data[i] = temp;
	}
    }

    public static void print(int[] data){
	for (int x : data)
	    System.out.print(x + " ");
	System.out.println();

    }
    // O(n)
    public static int sequentialSearch(int [] data, int key){
	for (int i = 0; i < data.length; i++){
	    if (data[i] == key) return i;
	}
	return -1;
    }

    public static int sequentialSearchR(int[] data, int key){
	return sequentialSearchR(data, key, 0);
	// YOUR CODE STARTS HERE 
	// YOU MAY NEED A HELPER FUNCTION. 
    }
    public static int sequentialSearchR(int[] data, int key, int pos){
	if(pos == data.length) return -1;
	if(data[pos] == key) return pos;
	return sequentialSearchR(data, key, pos + 1);
    }
    public static void main(String [] args){
	final int N = 10;
	int [] data = makeData(N); 
	print(data);
	shuffle(data);
	print(data);
	for (int x : data){
	    //System.out.println("   found " + x + " at index " + sequentialSearch(data,x));
	    System.out.println("R: found " + x + " at index " + sequentialSearchR(data,x));
	}
	//System.out.println("   found 11 at index " + sequentialSearch(data,11));
	System.out.println("R: found " + N + " at index " + sequentialSearchR(data,N));
    }



}
