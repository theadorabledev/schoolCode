import java.util.*;
public class MergeSort{
    public static int [] makeData(int n, int lo, int hi){
		int [] ans = new int[n];
			for (int i = 0; i < n; i++)
			ans[i] = lo + (int)(Math.random() * (hi - lo));
		return ans;
    }
    public static ArrayList<Integer> toList(int [] data){
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (Integer x : data) ans.add(x);
		return ans;
    }
	public static int[] toArr(ArrayList<Integer> data){
		int[] arr = new int[data.size()];
		for(int i = 0; i < arr.length; i++) arr[i] = data.get(i);
		return arr;
	}
	public static void print(int [] array){
        for (int x : array)
            System.out.print(x + " ");
        System.out.println();
    }
    public static ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b){
		ArrayList<Integer> fin = new ArrayList<Integer>();
		int ap = 0;
		int bp = 0;
		while(fin.size() < a.size() + b.size()){
			if(ap >= a.size() || bp >= b.size()) break;
			if(a.get(ap) <= b.get(bp)) fin.add(a.get(ap++));
			else fin.add(b.get(bp++));
		}
		while(ap < a.size()) fin.add(a.get(ap++));
		while(bp < b.size()) fin.add(b.get(bp++));
		return fin;
	}
	
	public static void mergeSort(ArrayList<Integer> data){
		LinkedList<ArrayList<Integer>> q = new LinkedList<ArrayList<Integer>>();
		for(Integer i : data){
			q.add(new ArrayList<Integer>(){{add(i);}});
		}
		while(q.size() > 1) q.add(merge(q.poll(), q.poll()));
		ArrayList<Integer> a = q.poll();
		for(int i = 0; i < a.size(); i++) data.set(i, a.get(i)); 
    }
	public static void mergeSort(int[] data){
		ArrayList<Integer> l = toList(data);
		mergeSort(l);
		for(int i = 0; i < l.size(); i++) data[i] = l.get(i);
	}
    public static void main(String args[]){
		ArrayList<Integer> test = toList(makeData(10, 1, 10));
		System.out.println(test);
		mergeSort(test);
		System.out.println(test);
		int[] test2 = makeData(10,1,10);
		print(test2);
		mergeSort(test2);
		print(test2);
    }
}
