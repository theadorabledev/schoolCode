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

    // data[lower] is the pivot
    private static int partition(int [] data, int lower , int upper){
	int pivotPos = new Random().nextInt(upper - lower - 1) + lower;
	print(data);
	System.out.println(lower + " " + upper + " " + pivotPos);
	swap(data, pivotPos, lower);
	print(data);
	int i = lower;
	int j = upper - 1;
	while ( i <= j){
	    while (i <= j && data[i] <= data[pivotPos])  i++;
	    while (j >= i && data[j] >= data[pivotPos])  j--;
	    if ( i < j)
		swap(data,i,j);
   	}
	System.out.println(i + " " + j);
	swap(data,pivotPos,j);
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
	quickSort(data);
	System.out.println("Is sorted: " + isSorted(data));
	print(data);
    }
}
