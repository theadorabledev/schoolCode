import java.util.*;
public class LanguageModel{
	private int kOrder;
	private HashMap<ArrayList<String>, ArrayList<String>> map = new HashMap<ArrayList<String>, ArrayList<String>>();
	private String text;
	private ArrayList<String> textList;
	private Random rand = new Random();
	public LanguageModel(int kOrder, String text){
		text = text.replaceAll("\\r\\n|\\r|\\n", " ").trim().replaceAll("\\s+", " ");;
		this.text = text;
		String [] textArr = text.split(" ");//"[\\p{Punct}\\s]+");
		this.kOrder = kOrder;
		textList = new ArrayList<String>(Arrays.asList(textArr));
		for(int i = 0; i < textArr.length - kOrder - 1; i++){
			int k = 1;
			while(k <= kOrder){
				//if(!textArr[i + k + 1].equals("")){
					
					//if(textArr[i + k + 1].equals(""))  throw new  IndexOutOfBoundsException();
					ArrayList<String> sub = new ArrayList<String>(textList.subList(i, i + k));
					map.putIfAbsent(sub, new ArrayList<String>(Arrays.asList(new String [] {textArr[i + k + 1]})));
					map.get(sub).add(textArr[i + k + 1]);
					k++;				
				//}
			}
		}
	}
	public int kOrder(){
		return kOrder;
	}
	public String text(){
		return text;
	}
	public HashMap<ArrayList<String>, ArrayList<String>> map(){
		return map;
	}
	public ArrayList<String> textList(){
		return textList;
	}
	public ArrayList<String> startText(){
		return new ArrayList<String>(textList.subList(0, kOrder));
	}
	public void printDistinctKeys(){
		System.out.println(map.size() + " distinct keys");
		for(ArrayList<String> s : map.keySet()){
			System.out.println(s + " " + map.get(s));
		}
	}
	public String getNext(ArrayList<String> k){
		return map.get(k).get(rand.nextInt(map.get(k).size()));
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
		LanguageModel l = new LanguageModel(kOrder, text);
		l.printDistinctKeys();
	}
}
