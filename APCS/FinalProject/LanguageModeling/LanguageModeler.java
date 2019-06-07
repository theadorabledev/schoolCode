import java.util.*;
public class LanguageModeler{
	public static class Markov{
		private String str;
		private HashMap<String, Integer> counts = new HashMap<String, Integer>();
		private ArrayList<String> choices = new ArrayList<String>();
		private int total = 0;
		Random rand = new Random();
		public Markov(String substring){
			str = substring;
		}
		public void add(String s){
			choices.add(s);
			counts.putIfAbsent(s, 0);
			counts.put(s, counts.get(s) + 1);
			total++;
			
		}
		public String toString(){
			String s = str + ": ";
			for(String st : counts.keySet()){
				s += counts.get(st) + " " + st + " ";
			}
			return s;
		}
		public String random(){
			return choices.get(rand.nextInt(total));
		}
		
	}
	public HashMap<String, Markov> map = new HashMap<String, Markov>();
	private HashMap<String, Integer> counts = new HashMap<String, Integer>();
	public LanguageModeler(int kOrder, String text){
		for(int i = 0; i < text.length() - kOrder; i++){
			String sub = text.substring(i, i + kOrder);
			Markov m = new Markov(sub);
			map.putIfAbsent(sub, m);
			m = map.get(sub);
			counts.putIfAbsent(sub, 0);
			counts.put(sub, counts.get(sub) + 1);
			m.add(text.substring(i + kOrder, i + kOrder + 1));
		}
	}
	public void printDistinctKeys(){
		System.out.println(map.size() + " distinct keys");
		for(String s : map.keySet()){
			if(s != "") System.out.println(counts.get(s) + " " + map.get(s));
		}
	}
	public static String getTextFromStdin(){
		Scanner s = new Scanner(System.in).useDelimiter("\\A");
		String text = s.hasNext() ? s.next() : "";
		text.replace("\n","");
		return text;
	}
	public static void main(String [] args){
		int kOrder = Integer.parseInt(args[0]);
		String text = getTextFromStdin();
		System.out.println("Original Text : ");
		System.out.println(text);
		LanguageModeler l = new LanguageModeler(kOrder, text);
		l.printDistinctKeys();
	}
}
