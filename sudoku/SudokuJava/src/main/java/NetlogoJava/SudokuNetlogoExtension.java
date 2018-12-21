import org.nlogo.agent.*;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
public class SudokuNetlogoExtension extends DefaultClassManager {


    public class IntegerList implements Reporter {
		// take one number as input, report a list
		public Syntax getSyntax() {
			return SyntaxJ.reporterSyntax(new int[] {Syntax.NumberType()}, Syntax.ListType());
		}
		public Object report(Argument args[], Context context) throws ExtensionException {
			// create a NetLogo list for the result
			LogoListBuilder list = new LogoListBuilder();
			int n ;
			// use typesafe helper method from
			// org.nlogo.api.Argument to access argument
			try {
				n = args[0].getIntValue();
			}catch(LogoException e) {
				throw new ExtensionException( e.getMessage() ) ;
			}
			if (n < 0) {
				// signals a NetLogo runtime error to the modeler
				throw new ExtensionException("input must be positive");
			}
		// populate the list. note that we use Double objects; NetLogo
		// numbers are always Doubles
			for (int i = 0; i < n; i++) {
				list.add(Double.valueOf(i));
			}
			return list.toLogoList();
		}
	}
	
	public class PuzzleList implements Reporter {
		public Syntax getSyntax() {
			return SyntaxJ.reporterSyntax(new int[] {Syntax.NumberType()}, Syntax.ListType());
		}
		public Object report(Argument args[], Context context) throws ExtensionException {
			// create a NetLogo list for the result
			LogoListBuilder list = new LogoListBuilder();
			SudokuPuzzleGenerated s;
			int n ;
			// use typesafe helper method from
			// org.nlogo.api.Argument to access argument
			try {
				n = args[0].getIntValue();
				s = new SudokuPuzzleGenerated("Easy", "Seed");
			}catch(LogoException e) {
				throw new ExtensionException( e.getMessage() ) ;
			}
			
			Integer [][] data = s.getData();
			for (int i = 0; i < 9; i++) {
				for(int k = 0; k < 9; k++){
					list.add(Double.valueOf(data[i][k]));
				}
			}
			return list.toLogoList();
		}
	}
	//http://ccl.northwestern.edu/netlogo/2.1/docs/extensions.html
    //https://github.com/NetLogo/NetLogo/wiki/Extensions-API
    public void load(PrimitiveManager pm){
		pm.addPrimitive("blueFish", new IntegerList());
		pm.addPrimitive("generate-puzzle", new PuzzleList());
    }
}
