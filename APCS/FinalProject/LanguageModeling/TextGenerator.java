public class TextGenerator{
    private LanguageModeler model;
    private String origText = "";
    private String text = "";
    private int length;
    private int kOrder;
    public TextGenerator(LanguageModeler lm, int l, int k, String t){
	length = l;
	model = lm;
	kOrder = k;
	origText = t;
    }
    public void print(){
	System.out.println(text);
    }
    public void generateText(){
	text = origText.substring(kOrder);
	while(text.length() < length){
	    print();
	    text += model.map.get(text.substring(text.length() + 1- kOrder, text.length())).random();
	}
    }
    public static void main(String [] args){
	int kOrder = Integer.parseInt(args[0]);
	int length = Integer.parseInt(args[1]);
	String text = LanguageModeler.getTextFromStdin();
	System.out.println("Original text : ");
	System.out.println(text);
	LanguageModeler lm = new LanguageModeler(kOrder, text);
	TextGenerator g = new TextGenerator(lm, length, kOrder, text);
	g.generateText();
	System.out.println("New text : ");
	g.print();
	
    }	
    
}
