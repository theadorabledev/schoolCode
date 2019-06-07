public class RSACryptosystem{
	private static class XP{
		private static int maxDigits = 100;
		public int [] arr;
		public XP(String num){
			arr = new int[2 * maxDigits + 1];
			for(int i = num.length() - 1; i >= 0; i--){
				arr[arr.length - 1 - i] = Integer.parseInt(String.valueOf(num.charAt(i)));
			}
			
		}
		public void display(){
			int stop = 0;
			for(int i = 0; i < arr.length; i++){
				if(arr[i] == 0) stop++;
				else break;
			}
			for(int i = arr.length - 1; i >= stop; i--){
				System.out.print(arr[i]);
			}
			System.out.println();
		}
		public static XP add(XP a, XP b){
			XP c = new XP("0");
			for(int i = c.arr.length - 1; i >= 0; i--){
				c.display();
				c.arr[i] = a.arr[i] + b.arr[i];
				if(c.arr[i] >= 10){
					c.arr[i] %= 10;
					c.arr[i - 1]++;
				}
			}
			return c;
			
		}
	}
	public static void main(String[] args){
		XP a, b, c;
		a = new XP("123456789");   // a   = 123456789
		b = new XP("54545454");    // b   =  54545454
		c = XP.add(a, b);          // c   =  a + b = 178002243
		c.display();
	}
}