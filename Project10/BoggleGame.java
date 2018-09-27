import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class BoggleGame
{
	JFrame window;
	Container content;
	JTextArea wordsFound;
	JButton randomize;
	JButton go;
	JTextField dimension;
	JTextField inputFile;
	JPanel leftPanel, boardPanel;
	String[] alphabet = {"a","b","c","d","e","f","g","h","i",
						"j","k","l","m","n","o","p","q","r",
						"s","t","u","v","w","x","y","z" };
	JButton[][] boardButtons;
	
	public BoggleGame()
	{
		window = new JFrame("Boggle Game");
		content = window.getContentPane();
		content.setLayout(new GridLayout(1,3));
		ButtonListener listener = new ButtonListener();
		wordsFound = new JTextArea();
		randomize = new JButton ("Randomize");
		go = new JButton("GO!");
		dimension = new JTextField("DEFAULT DIMENSION: 4x4");
		inputFile = new JTextField("type boggle filename here");
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(4,1));
		leftPanel.add(randomize);
		leftPanel.add( inputFile );
		leftPanel.add( dimension );
		leftPanel.add( go );
		
		boardPanel = new JPanel();
		boardButtons = new JButton[4][4];      //change!!!!!!!!
		boardPanel.setLayout(new GridLayout(4,4));   //change!!!!!!!!
		int i = 0;
		for (int r = 0; r < boardButtons.length; r++)
		{
			for (int c = 0; c < boardButtons.length; c++)
			{
				boardButtons[r][c] = new JButton(alphabet[i++]);    //change!!!!!!!!
				boardPanel.add(boardButtons[r][c]);
			}
		}
	
		content.add( leftPanel );
		content.add( boardPanel );
		content.add( wordsFound );
		
		window.setSize( 640,480);
		window.setVisible( true );
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Component whichButton = (Component) e.getSource();
			
			if (whichButton == randomize)
			{
				
				for (int r = 0; r < boardButtons.length; r++)
				{
					for (int c = 0; c < boardButtons.length; c++)
					{
						Random generator = new Random();
						int i = 0 + generator.nextInt(25);
						boardButtons[r][c] = new JButton(alphabet[i]);    //change!!!!!!!!
						boardPanel.add(boardButtons[r][c]);
					}
				}
			}//END Randomize
			
			if (whichButton == go)
			{
				String fileName = JTextField.getText();
				BufferedReader 
			}//END go
		}//END andtionPerformed
	}//END ButtonListener

	public static void main(String [] args)
	{
		new BoggleGame();
	}
	
}