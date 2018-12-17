import org.nlogo.api.*;
import org.nlogo.agent.*;
public class SudokuNetlogoExtension extends DefaultClassManager {
    public class IntegerList extends DefaultReporter{
    // this reporter takes a number as an argument and returns a list
	public Syntax getSyntax() {
	    return Syntax.reporterSyntax(new int[] { Syntax.TYPE_NUMBER}, Syntax.TYPE_LIST);
	}
    
	public Object report(Argument args[], Context context) throws ExtensionException{
	    // create a NetLogo list for the result
	    LogoList list = new LogoList();   
        
	    // use typesafe helper method from 
	    // org.nlogo.api.Argument to access argument
	    int n = args[0].getIntegerValue();  
        
	    if (n < 0) {
		// throw org.nlogo.api.ExtensionException to signal
		// a NetLogo runtime error to the modeler
		throw new ExtensionException
		    ("input must be positive");
	    }
        
        // populate the list 
	    for (int i = 1; i <= n; i++) {
		list.add(new Integer(i));
	    }
	    return list;
	}
    }
    //http://ccl.northwestern.edu/netlogo/2.1/docs/extensions.html
    //https://github.com/NetLogo/NetLogo/wiki/Extensions-API
    public void load(PrimitiveManager pm){
	pm.addPrimitive("blueFish", new IntegerList());
    }
}
