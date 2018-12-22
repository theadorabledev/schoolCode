import org.nlogo.agent.*;
import org.nlogo.api.*;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
public class SudokuNetlogoExtension extends DefaultClassManager {


    
	public class PuzzleList implements Reporter {
		public Syntax getSyntax() {
			//https://github.com/NetLogo/NetLogo/blob/5.1.0/src/main/org/nlogo/api/Syntax.scala
			return SyntaxJ.reporterSyntax(new int[] {Syntax.StringType(), Syntax.StringType()}, Syntax.ListType());
		}
		public Object report(Argument args[], Context context) throws ExtensionException {
			// create a NetLogo list for the result
			LogoListBuilder list = new LogoListBuilder();
			SudokuPuzzleGenerated s;
			int n ;
			// use typesafe helper method from
			// org.nlogo.api.Argument to access argument
			try {
				//n = args[0].getIntValue();
				s = new SudokuPuzzleGenerated(args[0].getString(), args[1].getString());
				//System.out.println(args[0].getString() + args[1].getString());
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
		pm.addPrimitive("generate-puzzle", new PuzzleList());
    }
}
