import java.util.*;
public class TextGenerator{
	private LanguageModel model;
	private String text = "";
	private ArrayList<String> textList;
	private int length;
	public TextGenerator(LanguageModel lm, int l){
		length = l;
		model = lm;
		textList = lm.startText();
		text = String.join(" ", textList);
	}
	public void print(){
		System.out.println(text);
	}
	public void generateText(){
		while(textList.size() < length){
			textList.add(getNext());
		}
		text = String.join(" ", textList);
	}
	private String getNext(){
		String next = "";
		for(int k = model.kOrder(); k >= 0; k--){
			ArrayList<String> sub = new ArrayList<String>(textList.subList(textList.size() - k, textList.size()));
			if(model.map().containsKey(sub)){ 
				return model.getNext(sub);
			}
		}
		return next;
	}
	public static void main(String [] args){
		int kOrder = Integer.parseInt(args[0]);
		int length = Integer.parseInt(args[1]);
		String text = LanguageModel.getTextFromStdin();
		LanguageModel lm = new LanguageModel(kOrder, text);
		TextGenerator g = new TextGenerator(lm, length);
		System.out.println("Original Text : ");
		System.out.println(lm.text());
		g.generateText();
		System.out.println("\nNew Text : ");
		g.print();

		
	}	
	
}
