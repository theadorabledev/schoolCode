import java.util.TreeSet;


public class Primes{
    
    public static TreeSet<Integer> allPrimes(int n){
	TreeSet<Integer> ans = new TreeSet<Integer>();
	if (n >= 2) ans.add(2);
	for(int i = 3; i <= n; i += 2){
	    boolean isPrime = true;
	    for (Integer x : ans){
		if (i % x == 0){
		    isPrime = false;
		    break;
		}
		if (x * x >= i) break;
	    }
	    if (isPrime) ans.add(i);

	}
	return ans;
    }

 



    public static void main(String [] args){
	int n = Integer.parseInt(args[0]);
	TreeSet<Integer> primes = allPrimes(n);
	System.out.println(primes);

    }

}
