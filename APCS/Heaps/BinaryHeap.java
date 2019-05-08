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
    public static void heapify(int[] data, int i){
	heapify(data, i, data.length);
    }
    public static void heapify(int [] data, int i, int upper){
	int left = 2 * i + 1;
	int right = left + 1;
	if(left >= upper || i < 0) return;
	int maxChildPos = (right < upper && data[right] > data[left])? right : left;
	if(data[i] < data[maxChildPos]){
	    swap(data, i, maxChildPos);
	    heapify(data, maxChildPos, upper);
	}

	//heapify(data, i - 1);
    }
    public static void buildHeap(int [] data){
	for(int i = (data.length - 2) / 2; i > -1; i--){
	    heapify(data, i);
	}
    }
    public static void heapSort(int[] data){
	buildHeap(data);
	for(int i = data.length - 1; i > 0; i--){
	    swap(data, 0, i);
	    heapify(data, 0, i);
	}
    }
    public static void main(String [] args){
	
	int [] bheap = {17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	print(bheap);
	heapSort(bheap);
	print(bheap);
    }
}
