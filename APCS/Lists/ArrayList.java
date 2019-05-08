import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E>  implements List<E>{

	// instance variables
	private  int CAPACITY;       //  array capacity
	private E[] data;                       // generic array used for storage
	private int size = 0;                       // current number of elements
	public ArrayList(){
		this(16);
	}
	@SuppressWarnings("unchecked")
	public ArrayList(int cap){
		CAPACITY = cap;
		data = (E[]) new Object[cap];
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public E get(int i) throws IndexOutOfBoundsException{
		if(i < 0 || i > size() - 1) throw new IndexOutOfBoundsException();
		return data[i];
	}
	public E set(int i, E value) throws IndexOutOfBoundsException{//Returns old value
		E ans = get(i);
		data[i] = value;
		return ans;
	}
	public boolean add(E value){
		add(size, value);
		return true;
	}
	public void add(int i, E value) throws IndexOutOfBoundsException{
		if(i < 0 || i > size()) throw new IndexOutOfBoundsException();
		if(size == CAPACITY){
			data = Arrays.copyOf(data ,(CAPACITY * 3) / 2);
			CAPACITY = (CAPACITY * 3) / 2;
		}
		for(int k = size; k > i; k--){
			data[k] = data[k - 1];
		}
		data[i] = value;
		size++;
	}
	public E remove(int i) throws IndexOutOfBoundsException{
		E val = get(i);
		for(int k = i; k < size - 1; k++){
			data[k] = data[k + 1];
		}
		data[size - 1] = null;
		size--;
		
		return val;
	}
	public Iterator iterator(){
		return new ListIterator<E>(this);
	}
	
	private class ListIterator<E> implements Iterator<E>{
		private ArrayList<E> list;
		private E current;
		int i = 0;
		
		public ListIterator(ArrayList<E> list){
			this.list = list;
			current = list.get(0);
		}

		public boolean hasNext(){
			return i < size();
		}
		public E next(){
			i++;
			current = list.get(i);
			return current;
		}


	}
	public String toString(){
		String s = "[ ";
		for(int i = 0; i < size; i++){
			s += data[i] + ", ";
		}
		s += "]";
		return s.replace(", ]", " ]");
	}
	public static void main(String[] args){
		ArrayList<String> l = new ArrayList<String>();
		String[] s = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"};
		System.out.println(l);
		System.out.println("\nProblem 6");
		for(int i = 0; i < s.length; i++){
			l.add(s[i]);
			System.out.println(l);
		}
		System.out.println("\nProblem 7");
		for(int i = 0; i < 16; i++){
			System.out.println(i + " :  " +l.get(i));
		}
		System.out.println("\nProblem 8");
		System.out.println(l);
		l.add(0, l.remove(1));//l.set(1, l.set(0,.get(1)))); Faster O(1)
		System.out.println(l);
		l.add(0, l.remove(1));
		System.out.println("\nProblem 9");
		System.out.println("Before removing : " + l);
		for(int i = 1; i < 9; i++){
			System.out.println("Remove : " + l.remove(i));
			System.out.println("List : " + l);
			
		}
		System.out.println("\nProblem 10");
		System.out.println("Before inserting : " + l);
		for(int i = 1; i < s.length; i += 2){
			String str = s[i];
			System.out.println("Insert : " + str);
			l.add(i, str);
			System.out.println("List : " + l);
		}
		l.add("q");

		
	}
}
