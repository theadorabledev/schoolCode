class Main {
	public static String pad(Object s, int n) {
    	return String.format("%-" + n + "s", s.toString());  
	}
	public static int[] makeData(int lo, int hi, int n){
        int [] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans [i] = (int) (Math.random() * (hi - lo) ) + lo;
        return ans;
    }
	public static void print(int [] array){
        for (int x : array)
            System.out.print(x + " ");
        System.out.println();
    }
	public static void bubbleShort(int[] data){
		int n = data.length - 1;
		boolean changed = false;
		for(int i = 0; i < data.length; i++){
			changed = false;
			for(int x = 0; x < n; x++){
				if(data[x] > data[x + 1]){
					changed = true;
					int temp = data[x];
					data[x] = data[x + 1];
					data[x + 1] = temp;
				}
			}
			if(!changed) break;
			n--;
		}
	}
	public static void bubbleSort(int[] data){
		int n = data.length - 1;
		for(int i = 0; i < data.length; i++){
			for(int x = 0; x < n; x++){
				if(data[x] > data[x + 1]){
					int temp = data[x];
					data[x] = data[x + 1];
					data[x + 1] = temp;
				}
			}
			n--;
		}
	}
	public static void selectionSort(int [] data){
		for(int i = 0 ; i < data.length - 1; i++){
			int minIndex = i;
			for(int k = i + 1; k < data.length; k++){
				if(data[k] < data[minIndex]){
					minIndex = k;
				}
			}
			if(minIndex != i){
				int temp = data[minIndex];
				data[minIndex] = data[i];
				data[i] = temp;
			}
		}
	}
	public static void insertionSort(int [] data){
		for(int i = 0; i < data.length; i++){
			for(int k = i ; k > 0; k--){
				if(data[k - 1]  > data[k]){
					int temp = data[k];
					data[k] = data[k - 1];
					data[k - 1] = temp;
				}else{
					break;
				}
			}
		}
	}

	public static int search(int[] data, int key){
		for(int i = 0; i < data.length; i++){
			if(data[i] == key) return i;
		}
		return -1;
	}
	public static int binarySearch(int[] data, int key){
		int low = 0;
		int high = data.length - 1;
		while(low <= high){
			int m = low + (high - low) / 2; 
			if (data[m] == key) return m; 
			if (data[m] < key) low = m + 1;
			else  high = m - 1 ; 
  			
		}
		return -1;
	}

	public static void main(String[] args) {
		Stopwatch s = new Stopwatch();
		System.out.println("N	linear	binary");
		int MAX_N = Integer.parseInt(args[0]);
		for(int i = 1; i < 8; i++){
			int n = (int) Math.pow(10, i);
			int[] data = makeData(0, n * 2, n);
			s.start();
			int q = search(data, (n * 2) + 2 );
			s.stop();
			long sTime = s.elapsedTime();
			s.start();
			q = binarySearch(data, (n * 2) + 2);
			s.stop();
			System.out.println(pad(n, 10) + pad(sTime, 4) + s.elapsedTime());
		}
		//int MAX_N = 5;
		System.out.println("\nBubble sort\nN         time");
		for(int i = 1; i < MAX_N; i++){
			int n = (int) Math.pow(10, i);
			int[] data = makeData(0, n * 2, n);
			s.start();
			bubbleSort(data);
			s.stop();
			long sTime = s.elapsedTime();
			System.out.println(pad(n, 10) + sTime);
		}
		System.out.println("\nBubble short\nN           time");
		for(int i = 1; i < MAX_N; i++){
			int n = (int) Math.pow(10, i);
			int[] data = makeData(0, n * 2, n);
			s.start();
			bubbleShort(data);
			s.stop();
			long sTime = s.elapsedTime();
			System.out.println(pad(n, 10) + sTime);
		}
		System.out.println("\nSelection sort\nN           time");
		for(int i = 1; i < MAX_N; i++){
			int n = (int) Math.pow(10, i);
			int[] data = makeData(0, n * 2, n);
			s.start();
			selectionSort(data);
			s.stop();
			long sTime = s.elapsedTime();
			System.out.println(pad(n, 10) + sTime);
		}
		System.out.println("\nInsertion sort\nN           time");
		for(int i = 1; i < MAX_N; i++){
			int n = (int) Math.pow(10, i);
			int[] data = makeData(0, n * 2, n);
			s.start();
			insertionSort(data);
			s.stop();
			long sTime = s.elapsedTime();
			System.out.println(pad(n, 10) + sTime);
		}
		
	}
}