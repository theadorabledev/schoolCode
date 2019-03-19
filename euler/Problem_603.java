public class Problem_603{

    public static void print(Object ... list){
	for(Object o : list){
	    System.out.print(o + " ");
	}
	System.out.println();
    }
	public static int S(String n, int l){
	    int sTest = 0;
	   

	    
		int N = n.length();
		int NN = N * l;
		int s = 0;
		int t = (((int) Math.pow(10, NN) - 1) / 9);
		for()

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
	    System.out.println(S("123123", 1)+ " "+ S("123",2));
	    System.out.println(P(7));
	    System.out.println(S(P(7),3 ) + " " + S(P(7)+P(7)+P(7), 1));
	    print(S(P((int) Math.pow(10, 3)), (int) Math.pow(10,12)) % 10007);
	    //String p = P((int) Math.pow(10,6));
	    //int sum = S(p, (int) Math.pow(10, 12));
	    //int mod = (int) Math.pow(10, 9) + 7;
	    //System.out.println(sum % mod);
	}
}
