import java.util.Scanner;
import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 

public class sieve{
	public static void main(String[] args){
		long start = System.nanoTime(); 
		int n = Integer.valueOf( args[0]) ;
		if (n == 1){
			System.out.println(2);
			return;
		}else{

			
      int primesLength;
			if (n < 200){
				primesLength = (int) 1000;
			}else{
				primesLength = (int) pi(n)/2;
			}
			
			//BitSet primes = new BitSet(primesLength);
      //primes.set(0, primesLength)   ;
      boolean [] primes = new boolean [primesLength];   //In primes, false=prime & true=composite to save time on array initialization
      //Arrays.fill(primes, true);
			for(int i = 0; i < Math.sqrt(primesLength); i++){
				//if(primes.get(i)){  
        if(! primes[i]){
					for(int x = (int) (Math.pow(((2 * i) + 3), 2) - 3) / 2; x < primesLength; x += ((2 * i) + 3)){
					   //primes.set(x, false);
             primes[x] = true;//false;
					}

				}
			}
			int p = (int) 1;
			for(int i = 0; i < primesLength; i++){
				//if(primes.get(i)){  
        if(!primes[i]){
					p += 1;
					if(p == n){
						System.out.println("Prime number "+ n + " is " + ((2 * i) + 3));
						System.out.println("Calculated in " + ((System.nanoTime() - start) / 1000000000.0 ) + " seconds.");
						System.out.println(Collections.frequency(Arrays.asList(primes), true));
						break;
					}
				}
			}
		}

	}
	static int pi(int num) {
		double n = num;
		return (int) ((n * (Math.log(n) + Math.log(Math.log(n)) -1 + ((Math.log(Math.log(n)) -2 )/Math.log(n)) -( ((Math.pow(Math.log(Math.log(n)), 2)) - (6 * Math.log(Math.log(n))) + 11 ) /(2* Math.pow(Math.log(n), 2)))  + (1/ Math.pow(Math.log(n), 2)))) * 1.05);
	}
}