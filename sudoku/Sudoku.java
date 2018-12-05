import java.util.*; 
import java.io.*;
import javax.swing.*;
 import java.awt.*;
public class Sudoku{
	public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9,9));
        for(int i = 1; i <= 81; i ++){
			buttonPanel.add(new JButton(String.valueOf(i)));
		}
		//buttonPanel.add(new JButton("1"));
        //buttonPanel.add(new JButton("2"));
        //buttonPanel.add(new JButton("3"));
        //buttonPanel.add(new JButton("4"));
        buttonPanel.setPreferredSize(new Dimension(500, 500));
        containerPanel.add(buttonPanel);

        frame.getContentPane().add(containerPanel);
        frame.pack();
        frame.setVisible(true);
     }
}