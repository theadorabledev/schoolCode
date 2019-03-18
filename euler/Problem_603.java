public class Problem_603{
	public static int S(String n, int l){
		int N = n.length();
		int NN = N * l;
		int s = 0;
		int t = (((int) Math.pow(10, NN) - 1) / 9);
		for(int i = 0; i < NN; i++){
			s += Character.getNumericValue(n.charAt(i % N)) * (i + 1) * t;
			t /= 10;
		}
		return s;
	}
	public static String P(int n){
		String p = "2";
		int x = 1;
		boolean [] primes = sieve.primesList(n);
		for(int i = 0; i < primes.length; i++){
			if(!primes[i]){
				p += sieve.convert(i);
				x++;
			}
			if(x == n){
				break;
			}
		}
		return p;
	}
	public static void main(String [] args){
		String p = P((int) Math.pow(10,6));
		int sum = S(p, (int) Math.pow(10, 12));
		int mod = (int) Math.pow(10, 9) + 7;
		System.out.println(sum % mod);
	}
}