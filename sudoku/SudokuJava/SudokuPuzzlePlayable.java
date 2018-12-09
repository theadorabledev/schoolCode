import java.util.*;
import java.io.*;
public class SudokuPuzzlePlayable extends SudokuPuzzle{
	public SudokuPuzzlePlayable(){
		super();
	}
	public SudokuPuzzlePlayable(Integer[][] data){
		super(data);
		update();
	}
	/**Sets the value.*/
	public void fillValue(Coordinate coord, int val){	
		int x = coord.x;
		int y = coord.y;
		update();
		if(val != 0){
			if(isValidPlay(coord, val)){
				rows.get(y).add(val);
				columns.get(x).add(val);
				groups.get(getGroup(x, y)).add(val);
				data[y][x] = val;
			}
		}else{
			int lastVal = data[y][x];
			rows.get(y).remove(lastVal);
			columns.get(x).remove(lastVal);
			groups.get(getGroup(x, y)).remove(lastVal);
			data[y][x] = 0;
		}
		update();
		
	}
	/** Updates information. */
	public void update(){
		for(Coordinate coord: possibleValues.keySet()){
			possibleValues.get(coord).addAll(defaultValues);
			possibleValues.get(coord).removeAll(getNeighbors(coord));
			possibleValues.get(coord).remove(0);
		}
	}
	/** Returns the possible values of a coordinate. */
	public HashSet<Integer> getPossibleValues(Coordinate coord){
		return possibleValues.get(coord);
	}
	/** Checks if the player's move is valid. */
	public boolean isValidPlay(Coordinate coord, int val){
		int x = coord.x;
		int y = coord.y;
		return !rows.get(y).contains(val) && !columns.get(x).contains(val) && !groups.get(getGroup(x, y)).contains(val);
	}
	
}