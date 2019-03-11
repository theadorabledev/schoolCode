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
		if(2 * i + 1 >= data.length || i < 0) return;
		if(data[2 * i + 1] > data[i]){
			swap(data, i, 2 * i + 1);
			heapify(data, 2 * i + 1);
		}
		if(2 * i + 2 < data.length && data[2 * i + 2] > data[i]){
			swap(data, i, 2 * i + 2);
			heapify(data, 2 * i + 2);
		}
		
		heapify(data, i - 1);
	}
	public static void buildHeap(int [] data){
		heapify(data, (data.length - 2) / 2);
	}
	public static void main(String [] args){
		int [] bheap = {1, 14, 7, 8, 3, 19};
		print(bheap);
		buildHeap(bheap);
		print(bheap);
	}
}