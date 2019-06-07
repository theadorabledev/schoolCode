public class TextGenerator{
	private LanguageModeler model;
	private String text = "";
	private int length;
	private int kOrder;
	public TextGenerator(LanguageModeler lm, int l, int k){
		length = l;
		model = lm;
		kOrder = k;
	}
	public void print(){
		System.out.println(text);
	}
	public void generateText(){
		text = lm.map.get("").random();
		while(text.length() < length){
			
		}
	}
	public static void main(String [] args){
		int kOrder = Integer.parseInt(args[0]);
		String text = getTextFromStdin();
		LanguageModeler lm = new LanguageModeler(kOrder, text);
		
	}	
	
}