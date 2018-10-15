import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 
import java.util.stream.*;
public class amicableNums{
  public static void main(String [] args){
    long start = System.nanoTime();
    int n = Integer.valueOf( args[0]) ;
    System.out.println(findAmicNums(n));
    System.out.println(((System.nanoTime() - start) / 1000000000.0 ) + " seconds.");
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