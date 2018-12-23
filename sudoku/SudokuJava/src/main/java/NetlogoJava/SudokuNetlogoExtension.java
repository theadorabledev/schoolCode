import org.nlogo.agent.*;
import org.nlogo.api.*;
import org.nlogo.core.LogoList;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import java.util.*;
import java.io.*;
public class SudokuNetlogoExtension extends DefaultClassManager {

	public class solveSudoku implements Reporter {
		public Syntax getSyntax() {
			return SyntaxJ.reporterSyntax(new int[] {Syntax.ListType()}, Syntax.ListType());
		}
		public Object report(Argument args[], Context context) throws ExtensionException {
			Integer [][] data = new Integer[9][9];
			LogoList l = args[0].getList();
			for(int i = 0; i < 9; i++){
				for(int k = 0; k < 9; k ++){
					data[i][k] = ((Double) l.get((i * 9) + k)).intValue();
				}
			}
			LogoListBuilder list = new LogoListBuilder();
			SudokuPuzzle s = new SudokuPuzzle(data);
			s.solve();
			data = s.getData();
			for (int i = 0; i < 9; i++) {
				for(int k = 0; k < 9; k++){
					list.add(Double.valueOf(data[i][k]));
				}
			}
			return list.toLogoList();
		}
	}
    public class isValidMove implements Reporter {
		public Syntax getSyntax() {
			return SyntaxJ.reporterSyntax(new int[] {Syntax.ListType(), Syntax.NumberType(), Syntax.NumberType(), Syntax.NumberType()}, Syntax.BooleanType());
		}
		public Object report(Argument args[], Context context) throws ExtensionException {
			Integer [][] data = new Integer[9][9];
			LogoList l = args[0].getList();
			int x = args[1].getIntValue();
			int y = args[2].getIntValue();
			int val = args[3].getIntValue();
			for(int i = 0; i < 9; i++){
				for(int k = 0; k < 9; k ++){
					data[i][k] = ((Double) l.get((i * 9) + k)).intValue();
				}
				
			}
			for(Coordinate coord : SudokuPuzzle.columnCoords.get(x)){
				if(data[coord.y][coord.x] == val) return false;
			}
			for(Coordinate coord : SudokuPuzzle.rowCoords.get(y)){
				if(data[coord.y][coord.x] == val) return false;
			}
			for(Coordinate coord : SudokuPuzzle.groupCoords.get(SudokuPuzzle.getGroup(x, y))){
				if(data[coord.y][coord.x] == val) return false;
			}
			return true;
		}
		
	}
	public class PuzzleList implements Reporter {
		public Syntax getSyntax() {
			
			return SyntaxJ.reporterSyntax(new int[] {Syntax.StringType(), Syntax.StringType()}, Syntax.ListType());
		}
		public Object report(Argument args[], Context context) throws ExtensionException {
			LogoListBuilder list = new LogoListBuilder();
			SudokuPuzzleGenerated s;
			int n ;
			try {
				s = new SudokuPuzzleGenerated(args[0].getString(), args[1].getString());
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
	//https://github.com/NetLogo/NetLogo/blob/5.1.0/src/main/org/nlogo/api/Syntax.scala
	//http://ccl.northwestern.edu/netlogo/2.1/docs/extensions.html
    //https://github.com/NetLogo/NetLogo/wiki/Extensions-API
    public void load(PrimitiveManager pm){
		pm.addPrimitive("generate-puzzle", new PuzzleList());
		pm.addPrimitive("is-valid-move", new isValidMove());
		pm.addPrimitive("solve-puzzle", new solveSudoku());
    }
}
