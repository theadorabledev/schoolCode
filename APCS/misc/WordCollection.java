import java.util.ArrayList;

public class WordCollection{

    private ArrayList<String> collection = new ArrayList<String>();
       
    // constructor
    // postcondition: creates an empty WordCollection
    public WordCollection(){  }

    //postcondition: Copys from the String[] to the WordCollection
    public WordCollection(String [] words){
	for(String word : words){
	    insert(word);
	}
    }

    // returns the total number of items stored in the collection
    public int size() {
	return collection.size();
    }

    // returns kth word in alphabetical order, where
    // 0 ≤ k < size()
    public String findKth(int k) {
	return collection.get(k);
    }

    // adds word to the collection (duplicates allowed) by maintaining
    // a sorted(ascending) list of words.
    public void insert(String word){//O(n)
	for(int i = 0; i < collection.size(); i++){
	    if(word.compareTo(collection.get(i)) >= 0){
		collection.add(i, word);
		return;
	    }
	}
	collection.add(word);
    }
    
    // returns the index of the first occurrence of word in the collection
    // returns -1 if not found.
    public int indexOf(String word){//O(n)
	return collection.indexOf(word);
    }

    // removes one instance of word from the collection if word is
    // present; otherwise, does nothing
    public void remove(String word){//O(n)
	remove(word);
    }
  
    public String toString(){
	return collection.toString();
    }
    public static int occurrences(WordCollection C, String word){
	int count = 0;
	for(int i = 0; i < C.size(); i++){
	    if (C.findKth(i).equals(word)) count++;
	}
	return count;
    }
    public static void removeDuplicates(WordCollection C, String word){
	while(occurrences(C, word) > 1){
	    C.remove(word);
	}
    }
    public static String mostCommon(WordCollection C){
	String s = "";
	int count = 0;
	for(int i = 0; i < C.size(); i++){
	    String word = C.findKth(i);
	    int o = occurrences(C, word);
	    if (o > count){
		s = C.findKth(i);
		count = o;
	    }
	}
	return s;
    }

}
