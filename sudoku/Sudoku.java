import java.util.*; 
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Sudoku{
	private JFrame frame = new JFrame();
	private JPanel gridPanel = new JPanel();
	private JPanel gridContainer = new JPanel();
	private JPanel control = new JPanel();
	private boolean pieceClicked;
	private SudokuButton currentCoordinate = null;
        private JButton generatePuzzleButton = new JButton("Generate Puzzle");
    private SudokuPuzzle myPuzzle;
    private HashMap<Coordinate, SudokuButton> gridButtons = new HashMap<Coordinate, SudokuButton>();
	public Sudoku(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
        gridPanel.setLayout(new GridLayout(9,9));
        for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x ++){
			    SudokuButton b = new SudokuButton(new Coordinate(x, y),this);
				gridPanel.add(b);
				gridButtons.put(b.coord, b);
			}
		}
		gridPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.blue));
        gridPanel.setPreferredSize(new Dimension(500, 500));
        gridContainer.add(gridPanel);
		gridContainer.setPreferredSize(new Dimension(700, 700));
		control.add(generatePuzzleButton);
		generatePuzzleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    myPuzzle = new SudokuPuzzleGenerated("Hard");
			    loadPuzzleData();
			}
		    });
        //frame.getContentPane().add(gridContainer);
        
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, control, gridContainer);
		frame.getContentPane().add(splitPane);
		frame.pack();
        frame.setVisible(true);
	}
	public static void main(String args[]){
        Sudoku s = new Sudoku();
    }
	public void press(SudokuButton b){
		if (currentCoordinate != null){
			//currentCoordinate.setText("Nope");
			currentCoordinate.setBackground(null);
		}
		currentCoordinate = b;
		//b.setText("Yep");
		b.setBackground(Color.green);
	}
    public void loadPuzzleData(){
	Integer[][] myData = myPuzzle.data;
	myPuzzle.printData();
	for(int x = 0; x < 9; x ++){
	    for(int y = 0; y < 9; y++){
		gridButtons.get(new Coordinate(x, y)).setText(String.valueOf(myData[y][x]));
	    }
	}
    }
}
