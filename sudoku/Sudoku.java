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
	private JPanel generatorContainer = new JPanel();
    private SudokuPuzzle myPuzzle;
    private HashMap<Coordinate, SudokuButton> gridButtons = new HashMap<Coordinate, SudokuButton>();
	private String [] difficulties = new String[] {"Easy", "Medium", "Hard", "Evil"};
	private JComboBox<String> difficultiesBox = new JComboBox<String>(difficulties);
    private JComboBox numberChoice = new JComboBox<String>(new String [] {"", "1", "2", "3", "4", "5", "6", "7", "8", "9"}); 
	private JTextField seedInput = new JTextField("Seed");
	
	public Sudoku(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupGrid();
		setupGenerator();
		control.setLayout(new BoxLayout(control, BoxLayout.PAGE_AXIS));
		control.add(numberChoice);
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
			currentCoordinate.setBackground(null);
		}
		if(currentCoordinate == b){
			currentCoordinate.setBackground(null);
			currentCoordinate = null;
		}else{
			currentCoordinate = b;
			b.setBackground(Color.green);
		}
	}
    public void loadPuzzleData(){
		Integer[][] myData = myPuzzle.data;
		myPuzzle.printData();
		for(int x = 0; x < 9; x ++){
			for(int y = 0; y < 9; y++){
				if(myData[y][x] != 0){
					gridButtons.get(new Coordinate(x, y)).setText(String.valueOf(myData[y][x]));
					gridButtons.get(new Coordinate(x, y)).permanent();
				}
			}
		}
    }
	private void setupGenerator(){
		generatorContainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		generatorContainer.add(difficultiesBox, gbc);
		gbc.gridx = 1;
		generatorContainer.add(seedInput, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
        gbc.gridwidth = 2;
		generatorContainer.add(generatePuzzleButton, gbc);
		generatorContainer.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		generatorContainer.setPreferredSize(new Dimension(200, 200));
		control.add(generatorContainer);
		generatePuzzleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    myPuzzle = new SudokuPuzzleGenerated((String) difficultiesBox.getSelectedItem(), seedInput.getText());
			    loadPuzzleData();
			}
		});
	}
	private void setupGrid(){
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
	}
}

