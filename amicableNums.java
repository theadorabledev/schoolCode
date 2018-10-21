import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 
import java.util.stream.*;
public class amicableNums{
  public static void main(String [] args){
    if(args[0].contains("h")){
		System.out.println("Usage: java amicableNums upTo lengthOfCycle\n lengthOfCycle defaults to 2 unless specified\nex:java amicableNums 1000000 3 -> Finds all cycles of sociable numbers with a length less than or equal to 3.");
	}else{
		long start = System.nanoTime();
		int n = Integer.valueOf(args[0]);
		int cycleLen = 2;
		if(args.length > 1){
			cycleLen = Integer.valueOf(args[1]);
		}
		ArrayList<ArrayList<Integer>> answer = findSociableNums(n, cycleLen);
		for(ArrayList<Integer> nums: answer){
			System.out.println( new LinkedHashSet<>(nums));
		}	

		System.out.println(((System.nanoTime() - start) / 1000000000.0 ) + " seconds.");
	}
  }
  static ArrayList<Integer> sociableNumTest(int n, int cycleLen){
	  ArrayList<Integer> nums = new ArrayList<Integer>();
	  nums.add(n);
	  nums.add(primeFactorSum(n, n%2));
	  for(int i = 0; i < cycleLen; i++){
		  int factorSum = primeFactorSum(nums.get(nums.size() - 1), nums.get(nums.size() - 1) % 2);
		  if (nums.get(0).equals(factorSum) && nums.get(0) <= nums.get(nums.size() - 1)){
			  return nums;
		  }
		  if(nums.get(nums.size() - 1).equals(1)){
			  return new ArrayList<Integer>();
		  }
		  nums.add(factorSum);
	  }
	  //return nums;
	  return new ArrayList<Integer>();
	  
  }
  static ArrayList<ArrayList<Integer>> findSociableNums(int upTo, int cycleLen){
	  ArrayList<ArrayList<Integer>> nums = new ArrayList<ArrayList<Integer>>();
	  for(int i = 2; i < upTo; i++){
		  ArrayList<Integer> vals = sociableNumTest(i, cycleLen);
		  if(vals.size() > 0){
			  nums.add(vals);
		  }
		  
		  
	  }
	  return nums;
  }
  static List<String> findAmicNums(int upTo){
    List<String> amicNums = new ArrayList<String>();
    //int[][] amicNums = [];
    for(int i = 2; i < upTo; i++){
      String nums = test(i);
      if(nums != null){
        amicNums.add(nums);
      }
    }
    return amicNums;
  }
  static String test(int num){
    int theSum = primeFactorSum(num, num%2) ;
    if (num != theSum && num < theSum && num == primeFactorSum(theSum, theSum%2)){
     int[] a = {num, theSum};
     return Arrays.toString(a);
    }
    return null;
  }
  static int primeFactorSum(int num, int inc){ 
    int sum = 0;
    for(int i = 1; i < Math.sqrt(num); i += inc + 1){
      if(num % i == 0){
        sum += i ;
        sum += num/i;
      }
    }
    return sum - num;
  }
}