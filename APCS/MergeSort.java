public class MergeSort{
    public static int[] makeData(int a, int b){
	int[] ans = new int[b - a + 1];
	for (int i = 0; i < (b - a); i++){
	    ans[b - a + i - 1] = i;
	}
	return ans;
    }

    public static void shuffle(int [] data){
	for (int i = 0; i < data.length; i++){
	    int r = (int) (Math.random() * (data.length  - i));
	    int temp = data[r];
	    data[r] = data[i];
	    data[i] = temp;
	}
    }

    public static void print(int [] array){
        for (int x : array)
            System.out.print(x + " ");
        System.out.println();
    }
    public static int[] merge(int[] a, int[] b){
	int[] fin = new int[a.length + b.length];
	int pointer = 0;
	int bPointer = 0;
	int aPointer = 0;
	while(pointer < fin.length - 1){
	    print(fin);
	    if(aPointer < a.length && a[aPointer] < b[bPointer]){
		fin[pointer] = a[aPointer];
		aPointer++;
	    }else{
		fin[pointer] = b[bPointer];
		bPointer++;
	    }
	    
	    pointer++;
	}
	return fin;
    }
    
    public static void main(String args[]){
	int[] a = makeData(1, 4);
	int[] b = makeData(4, 8);
	print(a);
	print(b);
	print(merge(a, b));
    }
}
