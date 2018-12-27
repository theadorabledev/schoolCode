import java.util.*; 
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SudokuButton extends JButton implements ActionListener{
    public Coordinate coord;
	public boolean pressed;
	public Sudoku parent;
	private boolean valueIsSet = false;
	private HashSet<String> numStrings = new HashSet<String>(Arrays.asList(new String [] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
    private HashSet<String> directions = new HashSet<String>(Arrays.asList(new String [] {"Left", "Right", "Up", "Down"}));
	public JLabel possibleValues = new JLabel("", JLabel.CENTER);
	public SudokuButton(Coordinate coord, Sudoku parent){
		super("");//coord.toString());
		this.coord = coord;
		this.parent = parent;
		setBorders();
		
		addActionListener(this);
		setFont(new Font("Arial", Font.PLAIN, 30));
		//setBackground(Color.cyan);
		possibleValues.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(possibleValues);
		
		this.addKeyListener(new KeyAdapter (){
			public void keyPressed(KeyEvent e) {
			    if(pressed){
				
					String val = e.getKeyText(e.getKeyCode());
					if(numStrings.contains(val)){
						parent.setGridSpot(val, false);
						//parent.numberChoice.;
					}else if(val.equals("Backspace")){
						parent.setGridSpot("0", false);
					}else if(directions.contains(val)){
						parent.moveSelected(val);
					}else if(val.toLowerCase().equals("z") && e.isControlDown()){
					    if( e.isShiftDown()){
						    parent.redo.doClick();
						}else{
						parent.undo.doClick();
					    }
					}
				}	
			}
		});
	}
	/**Deals with button press. */
	public void actionPerformed(ActionEvent e) {
        if(!valueIsSet){
			pressed = !pressed;
			parent.press(this);
		}
    }
	/** Creates the borders based on position. */
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
		setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));
    } 
	/** Marks it as a permanent unclickable value. */
	public void permanent(){
		valueIsSet = true;
		possibleValues.setText("");
		setFont(new Font("Arial", Font.BOLD, 30));
	}
	public boolean isPermanent(){
		return valueIsSet;
	}
	public void updateToolTip(){
		setToolTipText(possibleValues.getText());
	}
}

