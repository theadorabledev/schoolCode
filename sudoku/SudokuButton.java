import java.util.*; 
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SudokuButton extends JButton implements ActionListener{
    public Coordinate coord;
	public boolean pressed;
	public Sudoku parent;
    public SudokuButton(Coordinate coord, Sudoku parent){
		super("");//coord.toString());
		this.coord = coord;
		this.parent = parent;
		setBorders();
		addActionListener(this);
		//setBackground(Color.cyan);
	}
	public void actionPerformed(ActionEvent e) {
        System.out.println(coord);
		pressed = !pressed;
		parent.press(this);
    }
    public void setBorders(){
		int bottom = 1;
		int left = 1;
		int right = 1;
		int top = 1;  
		if(coord.x == 2 || coord.x == 5){
			right = 3;
		}
		if(coord.x == 3 || coord.x == 6){
			left = 3;
		}
		if(coord.y == 2 || coord.y == 5){
			bottom = 3;
		}
		if(coord.y == 3 || coord.y == 6){
			top = 3;
		}
		//System.out.println(coord + " " + bottom + " " + left + " " + right +  " " + top);
		setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));
    } 
	
}

