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

    public static void print(int [] array){
        for (int x : array)
            System.out.print(x + " ");
        System.out.println();
    }
    public static int[] merge(int[] a, int[] b){
	int[] fin = new int[a.length + b.length];
	int pointer = 0;
	int bPointer = 0;
	int aPointer = 0;
	while(pointer < fin.length - 1){
	    print(fin);
	    if(aPointer < a.length && a[aPointer] < b[bPointer]){
		fin[pointer] = a[aPointer];
		aPointer++;
	    }else{
		fin[pointer] = b[bPointer];
		bPointer++;
	    }
	    pointer++;
	}
	return fin;
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
    public static void sort(ArrayList<Integer> data){
	LinkedList<ArrayList<Integer>> q = new LinkedList<ArrayList<Integer>>();
	for(Integer i : data){
	    q.add(new ArrayList<Integer>(){{add(i);}});
	}
	System.out.println(q);
    }
    public static void main(String args[]){
	int[] a = makeData(5, 1, 10);
	int[] b = makeData(5, 1, 10);
	print(a);
	print(b);
	sort(toList(makeData(10, 1, 10)));
	//System.out.println(toList(a));
	//System.out.println(merge(toList(a), toList(b)));
    }
}
