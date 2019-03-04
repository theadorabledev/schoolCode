
/*
Reduction to Sorting
=====================

A problem A reduces to a problem B if we can use a solution to B
to solve A.

Given a new problem, ask yourself how you would solove it if the
data were sorted.

Consider the problem of determining  element distinctness in a
sequence. To solve the problem, it is easier to first sort
the sequence efficiently, O(NlogN),  then use a linear pass.
Thus the element distinctness problem reduces to sorting.

Java Classes
============
  Both Arrays.sort()  and Collections.sort() use the merge sort
  algorithm which is linearithmic, O(NlogN).


Exercises:
==========
1. Write Stats.java, a program that creates 2 random sequence
   of integers one stored in an array the other in a list.
   
2. Add to Stats.java the methods median(int[] data) and
   median(List<Integer> data) that computes in lineararithmic time
   the median of n integers. Hint: Reduce to sorting.


3. Add to Stat.java the methods mode(int[] data) and mode(List<Integer> data)
   to compute the mode. Hint: Reduce to sorting.
*/

import java.util.*;

public class Stats{
    // pre: n > 0, lo <= hi
    // post: returns a n element array of random integers
    //       each element is within [lo,hi).
    public static int [] randomArray(int n, int lo, int hi){
		int [] ans = new int[n];
		for (int i = 0; i < n; i++)
			ans[i] = lo + (int)(Math.random() * (hi - lo));
		return ans;
	
    }
    // post: prints an array
    public static void print(int[] data){
		for (int x : data)
			System.out.print(x + " ");
		System.out.println();
    }
    // O(NlogN)
    public static double median(int[] data){
		Arrays.sort(data);
		//print(data);
		int l = data.length;
		return (l % 2 == 0) ? (data[l / 2] + data[(l / 2) - 1]) / 2.0 : data[l / 2] ;
    }
     // O(NlogN)
    public static double median(List<Integer> data){
		return median(toArray(data));
    }
    //O(N)
    public static int mode(int[] data){
		int mn = 0;
		for(int i = 0; i < data.length; i++){
			if(data[i] > mn) mn = data[i];
		}
		int[] counts = new int[mn + 1];
		int m = -1;
		int p = 0;
		for(int i = 0; i < data.length; i++){
			counts[data[i]]++;
		}
		for(int i = 0; i < counts.length; i++){
			if(counts[p] < counts[i] && counts[i] > 1){
				m = i;
				p = i;
			}
		}
		return m;
    }  
    //O(N)
    public static int mode(List<Integer> data){
		return mode(toArray(data));
    }
    // pre: data != null
    // post: returns a list whose elements are chosen from data
    public static List<Integer> toList(int [] data){
		List<Integer> ans = new ArrayList<Integer>();
		for (Integer x : data) ans.add(x);
		return ans;
    }
    public static int[] toArray(List<Integer> nums){
		int[] arr = new int[nums.size()];
		for (int i=0; i < arr.length; i++){
			arr[i] = nums.get(i);
		}
		return arr;
	}
	public static void main(String [] args){
		int [] data = randomArray(10,1,10);
		List<Integer> dataList = toList(data);

		// array version
		System.out.println("testing with arrays");
		print(data);
		System.out.println("median: " + median(data));
		System.out.println("mode: "  + mode(data));
		print(data);

		// list version
		System.out.println("testing with lists");
		System.out.println(dataList);
		System.out.println("median: " + median(dataList));
		System.out.println("mode: "  + mode(dataList));
		System.out.println(dataList);
    }
    

}
