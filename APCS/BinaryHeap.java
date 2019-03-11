public class BinaryHeap{
    public static void print(int [] data){
	for (int x :  data)
	    System.out.print(x + " ");
	System.out.println();
    }
    public static void swap(int [] data, int i, int j){
	int temp = data[i];
	data[i] = data[j];
	data[j] = temp;
    }
    public static boolean isHeap(int [] data){
	return isHeapHelper(data,0);      	     
    }
    private static boolean isHeapHelper(int [] data, int i){
	final int N = data.length;
	if (i >= N) return true;
	if (data[i] > data[(i-1)/2]) return false;
	return isHeapHelper(data,i+1);
    }
    public static void heapify(int [] data, int i){
	int left = 2 * i + 1;
	int right = left + 1;
	if(left >= data.length || i < 0) return;
	int maxChildPos = (right < data.length && data[right] > data[left])? right : left;
	if(data[i] < data[maxChildPos]){
	    swap(data, i, maxChildPos);
	    heapify(data, maxChildPos);
	}

	//heapify(data, i - 1);
    }
    public static void buildHeap(int [] data){
	for(int i = (data.length - 2) / 2; i > -1; i--){
	    heapify(data, i);
	}
    }
    public static void main(String [] args){
	//int [] bheap = {1, 14, 7, 8, 3, 19};
	int [] bheap = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
	    print(bheap);
	    buildHeap(bheap);
	    print(bheap);
    }
}
