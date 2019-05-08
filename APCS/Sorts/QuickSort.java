import java.util.*;
public class QuickSort{

	
	public static void print(int [] data){
		for (int x : data)
		System.out.print(x + " ");
		System.out.println();
	}

	public static void quickSort(int [] data){
		qsHelper(data,0, data.length);
	}
	public static int quickSelect(int [] data, int k){
		return qSelectHelper(data, k - 1, 0, data.length);
	}

	public static void swap(int [] data,int i,int j){
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	private static void qsHelper(int [] data, int lower, int upper){
		if (upper - lower <= 1) return;
		int pivotPos = partition(data,lower, upper);
		qsHelper(data,lower, pivotPos);
		qsHelper(data, pivotPos + 1, upper);
	}
	private static int qSelectHelper(int [] data, int k, int lower, int upper){
		if(lower == upper) return data[lower];
		int pivotPos = partition(data,lower, upper);
		if(k < pivotPos) return qSelectHelper(data, k, lower, pivotPos - 1);
		else if (k > pivotPos) return qSelectHelper(data, k, pivotPos + 1, upper);
		else return data[k];
	}
	// data[lower] is the pivot
	private static int partition(int [] data, int lower , int upper){
		int pivotPos = new Random().nextInt(upper - lower) + lower;
		swap(data, pivotPos, lower);
		int i = lower;
		int j = upper - 1;
		while ( i <= j){
			while (i <= j && data[i] <= data[lower])  i++;
			while (j >= i && data[j] >= data[lower])  j--;
			if( i < j)
			swap(data,i,j);
		}
		if(j < 0) j = 0;
		swap(data,lower,j);
		return j;
	}

	public static void shuffle(int [] data){
		final int N = data.length;
		for (int i = 0; i < N; i++){
			int r = i + (int) (Math.random() * (N - i));
			swap(data,i,r);
		}
	}

	// pre : n > 0
	// post: returns {0,1,2,...,n-1}
	public static int[] createData(int n){
		int [] ans = new int[n];
		for (int i = 0; i < n; i++ ) ans[i] = i;
		return ans;
	}

	// pre: data != null
	// post: returns true if sorted in ascending order.
	public static boolean isSorted(int [] data){
		final int N = data.length;
		for(int i = 0; i < N - 1; i++)
		if (data[i] > data[i+1]) return false;
		return true;
	}
	
	public static void main(String [] args){
		int n = Integer.parseInt(args[0]);
		int [] data = createData(n);
		shuffle(data);
		print(data);
		System.out.println("Is sorted: " + isSorted(data));
		System.out.println(quickSelect(data, 10));
		System.out.println("Is sorted: " + isSorted(data));
		print(data);
	}
}
