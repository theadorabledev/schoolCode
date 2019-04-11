import java.util.Queue;
import java.util.ArrayDeque;


public class RadixSort{


    public static void radixSort(int[] data, int d){
	// create bins
	Queue[] bins = new Queue[10];

	// initialize each bin with a Queue
	for (int i = 0; i < 10; i++)
	    bins[i] = new ArrayDeque<Integer>();
	for(int k = 0; k < d; k++){
	    for(int i = 0; i < data.length; i++){
		bins[(data[i] / ((int) Math.pow(10, k))) % 10].add(data[i]);
	    }
	    int i = 0;
	    for(Queue<Integer> deque : bins){
		while(!deque.isEmpty()){
		    data[i] = deque.remove();
		    i++;
		}
	    }
	}
    }
    public static int[] generateData(int N, int d){
	int [] ans = new int[N];
	for (int i = 0; i < N; i++){
	    ans[i] = (int) (Math.random() * Math.pow(10,d));
	}
	return ans;
    }


    public static void print(int [] data){
	for (int x: data)
	    System.out.print(x + " ");
	System.out.println();
    }

    public static void main(String [] args){
	final int N = Integer.parseInt(args[0]);
	int d = Integer.parseInt(args[1]);
	int [] data  = generateData(N,d);
	print(data);
	radixSort(data,d);
	print(data);
    }


}
