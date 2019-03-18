import java.util.Scanner;
import java.lang.Math; 
import java.util.Arrays;
import java.util.*; 

public class sieve{
    public static boolean[] primesList(int n){
		int primesLength;
		if (n < 200){
			primesLength = (int) 1000;
		}else{
			primesLength = (int) pi(n)/2;
		}		
		boolean [] primes = new boolean [primesLength];   //In primes, false=prime & true=composite to save time on array initialization
		for(int i = 0; i < Math.sqrt(primesLength); i++){
			if(! primes[i]){
			for(int x = (int) (Math.pow(((2 * i) + 3), 2) - 3) / 2; x < primesLength; x += ((2 * i) + 3)){
				primes[x] = true;//false;
			}
			}
		}
		return primes;
    }
    public static int convert(int i){
		return (2 * i) + 3;
    }
    public static void main(String[] args){
		long totTime = System.nanoTime();
		int n = Integer.valueOf( args[0]) ;
		for(int z = 0; z < Integer.valueOf( args[1]); z++ ){		
			long start = System.nanoTime(); 
			if (n == 1){
				System.out.println(2);
				return;
			}else{			
				boolean[] primes = primesList(n);
				int p = (int) 1;
				for(int i = 0; i < primes.length; i++){
					if(!primes[i]){
						p += 1;
						if(p == n){
							System.out.println("Prime number "+ n + " is " + ((2 * i) + 3));
							System.out.println("Calculated in " + ((System.nanoTime() - start) / 1000000000.0 ) + " seconds.");
							break;
						}
					}
				}
			}

		}
		System.out.println(args[1] + " operations done in " + ((System.nanoTime() - totTime) / 1000000000.0 ) + " seconds");
    }
    static int pi(int num) {
		double n = num;
		return (int) ((n * (Math.log(n) + Math.log(Math.log(n)) -1 + ((Math.log(Math.log(n)) -2 )/Math.log(n)) -( ((Math.pow(Math.log(Math.log(n)), 2)) - (6 * Math.log(Math.log(n))) + 11 ) /(2* Math.pow(Math.log(n), 2)))  + (1/ Math.pow(Math.log(n), 2)))) * 1.05);
    }
}
