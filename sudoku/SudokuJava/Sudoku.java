import java.util.*; 
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Sudoku{
	private JFrame frame = new JFrame();
	
	//Deals with the grid
	private JPanel gridPanel = new JPanel();
	private JPanel gridContainer = new JPanel();
	private HashMap<Coordinate, SudokuButton> gridButtons = new HashMap<Coordinate, SudokuButton>();
	private SudokuButton currentGridButton = null;
	private boolean pressable = false;
	//Control Panel
	private JPanel controlSuper = new JPanel();
	private JPanel control = new JPanel();
    //Contains the puzzle generator
	private JPanel generatorContainer = new JPanel();
	private JButton generatePuzzleButton = new JButton("Generate Puzzle.");
	private JComboBox<String> difficultiesBox = new JComboBox<String>(new String[] {"Easy", "Medium", "Hard", "Evil"});
	private JTextField seedInput = new JTextField("Seed");
	//Sudoku Puzzles
    private SudokuPuzzleGenerated myPuzzle;
	private SudokuPuzzlePlayable puzzle;
	//Deals with the choices for value of each spot on grid
	private JPanel numberBox = new JPanel(); 
	private JComboBox numberChoice = new JComboBox<String>(new String [] {"Value:", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
	private JCheckBox showValues = new JCheckBox("Show possible values?");
	private JLabel numberValues = new JLabel();
	// Deals with timer
	private JPanel timerBox = new JPanel();
	private JLabel timerLabel = new JLabel("00:00");
	private javax.swing.Timer timer;
	private int secondsElapsed;
	private int minutesElapsed;	
	//Deals with solving
	private JButton solve = new JButton("Solve.");
	private boolean solving = false;
	//Deals with history(undo/redo)
	private ArrayList<Move> history = new ArrayList<Move>();
	private int historyPosition = 0;
	private JPanel undoRedo = new JPanel();
	private JButton undo = new JButton("Undo");
	private JButton redo = new JButton("Redo");
	private boolean undoing;
	//Misc
	private JLabel winnerLabel = new JLabel("You won!");
	private JLabel seedLabel = new JLabel();
	public Sudoku(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupGrid();
		setupGenerator();
		//control.setLayout()//new BoxLayout(control, BoxLayout.PAGE_AXIS));
		control.setLayout(new GridBagLayout());

	        
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(10, 5, 0, 5);
		c.ipadx = 10;
		c.ipady = 5;
		c.gridx = 0;
		c.gridy = 0;
		setupTimer();
		control.add(timerBox, c);
		
		
		c.gridy = 1;
		control.add(generatorContainer, c);
		c.gridy = 2;
		setupSolver();
		control.add(solve, c);
		c.gridy = 3;
		setupNumberBox();
		control.add(numberBox, c);
		c.gridy = 4;
		setupUndoRedo();
		control.add(undoRedo, c);
		c.gridy = 5;
		control.add(winnerLabel, c);
		winnerLabel.setVisible(false);
		winnerLabel.setFont(new Font("Arial", Font.BOLD, 30));
		controlSuper.add(control);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, controlSuper, gridContainer);
		frame.getContentPane().add(splitPane);
		frame.pack();
        frame.setVisible(true);
	}
	public static void main(String args[]){
        Sudoku s = new Sudoku();
    }
	/** Deals with the pressing of the buttons on the sudoku grid. */
	public void press(SudokuButton b){
		if(pressable){
			if (currentGridButton != null){
				currentGridButton.setBackground(null);
			}
			if(currentGridButton == b){
				currentGridButton.setBackground(null);
				currentGridButton = null;
				numberBox.setVisible(false);
			}else{
				currentGridButton = b;
				b.setBackground(Color.green);
				numberBox.setVisible(true);
				numberValues.setText(String.valueOf(puzzle.getPossibleValues(currentGridButton.coord)));
				if(!((String) numberChoice.getSelectedItem()).equals("Value:") && !((String) numberChoice.getSelectedItem()).equals(currentGridButton.getText())){
					numberChoice.setSelectedItem("Value:");
				}
				//numberChoices.
			}
			//checkForWin();
		}
		
	}
	/** Checks if the player has won and deals with the winning. */
	private void checkForWin(){
		if(puzzle.getKnownCount() == 81){
			pressable = false;
			for(Coordinate coord: gridButtons.keySet()){
				gridButtons.get(coord).setBackground(Color.green);
			}
			timer.stop();
			numberBox.setVisible(false);
			winnerLabel.setVisible(true);
			undoRedo.setVisible(false);
			
		}
	}
    /**Loads the permanent value sof a Sudoku puzzle onto the grid. */
	public void loadPuzzleData(){
		Integer[][] myData = myPuzzle.data;
		for(int x = 0; x < 9; x ++){
			for(int y = 0; y < 9; y++){
				if(myData[y][x] != 0){
					gridButtons.get(new Coordinate(x, y)).setText(String.valueOf(myData[y][x]));
					gridButtons.get(new Coordinate(x, y)).permanent();
				}
			}
		}
		generatePuzzleButton.setVisible(false);
		difficultiesBox.setVisible(false);
		seedInput.setVisible(false);
		generatorContainer.add(new JLabel("<HTML>Difficulty: " + difficultiesBox.getSelectedItem() + "<br/>Seed:" + seedInput.getText() + "</HTML>"));
		
    }
	/** Sets up the puzzle generator.*/
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
		generatorContainer.setSize(new Dimension(200, 200));
		
		generatePuzzleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    myPuzzle = new SudokuPuzzleGenerated((String) difficultiesBox.getSelectedItem(), seedInput.getText());
			    loadPuzzleData();
				puzzle = new SudokuPuzzlePlayable(myPuzzle.data);
				pressable = true;
				startTimer();		
				solve.setVisible(false);
				undoRedo.setVisible(true);
			}
		});
		generatePuzzleButton.setToolTipText("Press to generate a puzzle of the given difficulty from the given seed. ");
	}
	/** Sets up the Sudoku grid.*/
	private void setupGrid(){
		gridPanel.setLayout(new GridLayout(9,9));
        for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x ++){
			    SudokuButton b = new SudokuButton(new Coordinate(x, y),this);
				gridPanel.add(b);
				gridButtons.put(b.coord, b);
				b.addKeyListener(new KeyAdapter (){
					public void keyPressed(KeyEvent e) {
					    // System.out.println(e);
					}
				    });
			}
		}
		gridPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.blue));
        gridPanel.setPreferredSize(new Dimension(500, 500));
        gridContainer.add(gridPanel);
		gridContainer.setPreferredSize(new Dimension(700, 700));
	}
	/** Sets up the value box for each grid position. */
	private void setupNumberBox(){
		numberBox.setLayout(new GridBagLayout());
		numberBox.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		numberBox.add(numberChoice, gbc);
		numberChoice.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				setGridSpot((String) numberChoice.getSelectedItem(), false);
			}
		});
	        
		gbc.gridy = 1;
		numberBox.add(showValues, gbc);
		showValues.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					numberValues.setVisible(true);
					numberValues.setText(String.valueOf(puzzle.getPossibleValues(currentGridButton.coord)));
				} else {
					numberValues.setVisible(false);
				}
			}
		});
		gbc.gridy = 2;
		numberBox.add(numberValues, gbc);
		numberValues.setVisible(false);
		numberBox.setVisible(false);
	}
	/** Changes the value of a spot on the grid.*/
	public void setGridSpot(String value,  boolean isUndoRedo){
		if(!value.equals("Value:") && !value.equals("0")){
			if(puzzle.isValidPlay(currentGridButton.coord, Integer.valueOf(value))){
				if(!undoing){
					addToHistory(currentGridButton.coord, Integer.valueOf(value));
				}
				puzzle.fillValue(currentGridButton.coord, Integer.valueOf(value));
				currentGridButton.setText(value);
			}else{
				numberChoice.setSelectedItem("Value:");
			}
		}else{
			if(!undoing){
				addToHistory(currentGridButton.coord, 0);
			}
			puzzle.fillValue(currentGridButton.coord, 0);
			currentGridButton.setText("");
			
		}
		checkForWin();
	}
	/** Adds the last move to the undo/redo history and deals with changes. */
	private void addToHistory(Coordinate coord, int value){
			if(!gridButtons.get(coord).getText().equals("")){
				if(value != Integer.valueOf(gridButtons.get(coord).getText())){
					history.add(new Move(coord, value, Integer.valueOf(gridButtons.get(coord).getText())));
					historyPosition++;
				}
			}else{
				if(value != 0){
					history.add(new Move(coord, value, 0));
					historyPosition++;
				}
			}
			for(int i = historyPosition; i < history.size(); i ++){
				history.remove(i);
			}
	
	}
	/**Sets up the timer.*/
	private void setupTimer(){
		timerBox.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
		timerBox.add(timerLabel);
		timerBox.setVisible(false);		
	}
	/** Starts the timer for completing a puzzle. */
	private void startTimer(){
		timerBox.setVisible(true);
		timer = new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				secondsElapsed++;
				if(secondsElapsed == 60){
					secondsElapsed = 0;
					minutesElapsed++;
				}
				timerLabel.setText(String.format("%2s", String.valueOf(minutesElapsed)).replace(' ', '0') + ":" + String.format("%2s", String.valueOf(secondsElapsed)).replace(' ', '0'));
			}			 
		});	
		timer.start();
	}
	/** Sets up the solving functionality. */
	private void setupSolver(){
		solve.setToolTipText("Press to enter the information into the grid then, then solve.");
		solve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!solving){
					solving = true;
					pressable = true;
					generatorContainer.setVisible(false);
					puzzle = new SudokuPuzzlePlayable();
					solve.setToolTipText("Press to solve with the given puzzle. ");
					undoRedo.setVisible(true);
				}else{
					
					SudokuPuzzle s = new SudokuPuzzle(puzzle.getData());
					s.solve();
					Integer[][] d = s.getData();
					for(Coordinate coord : gridButtons.keySet()){
						SudokuButton sb = gridButtons.get(coord);
						sb.setText(String.valueOf(d[sb.coord.y][sb.coord.x]));
						sb.permanent();
					}
					undoRedo.setVisible(false);
					numberBox.setVisible(false);
					solve.setVisible(false);
					currentGridButton.setBackground(null);
				}
			}
		});
	}
	/** Sets up the undo and redo buttons.*/
	private void setupUndoRedo(){
		undoRedo.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		g.gridx = 0;
		g.gridy = 0;
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(historyPosition > 0){
					historyPosition--;
					Move move = history.get(historyPosition);
					SudokuButton lastbutton = currentGridButton;
					currentGridButton = gridButtons.get(move.coord);
					undoing = true;
					setGridSpot(String.valueOf(move.lastValue), true);
					currentGridButton = lastbutton;
					undoing = false;
					
				}
			}
		});
		undoRedo.add(undo, g);
		
		g.gridx = 1;
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(historyPosition < history.size()){
					Move move = history.get(historyPosition);
					SudokuButton lastbutton = currentGridButton;
					currentGridButton = gridButtons.get(move.coord);
					undoing = true;
					setGridSpot(String.valueOf(move.value), true);
					currentGridButton = lastbutton;
					historyPosition++;
					undoing = false;
				}
			}
		});
		undoRedo.add(redo, g);
		undoRedo.setVisible(false);
	}
}

