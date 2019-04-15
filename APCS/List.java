interface List<E>{

    public int size();         // returns the number of elements in the list
   
     public boolean isEmpty();
    
     public E get(int i) throws IndexOutOfBoundsException;
    // returns the element at index i; 
    // error when i not within [0,size() - 1]
    
     public E set(int i, E value) throws IndexOutOfBoundsException;
           // replace element i with value returns the old value
           // error when i not within [0,size() - 1]
   
    public boolean add(E value); 
    // add value to the end of the list

    public void add(int i, E value) throws IndexOutOfBoundsException;
    // inserts value at index i
    // Error when i not within [0,size()]

    public E remove(int i) throws IndexOutOfBouncsExcpetion;
    // Removes and returns the item at index i
    // Error when i not within [0,size()-1]

}
