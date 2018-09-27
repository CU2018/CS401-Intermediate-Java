import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class BoggleGUI
{
	JFrame window;
	Container content;
	JTextArea wordsFound;
	JScrollPane scroll;
	JButton randomize;
	JButton go;
	JTextField dimension;
	JTextField inputFile;
	JPanel leftPanel, boardPanel;
	String[] alphabet = {"a","b","c","d","e","f","g","h","i",
						"j","k","l","m","n","o","p","q","r",
						"s","t","u","v","w","x","y","z" };
	JButton[][] boardButtons;
	BufferedReader dFile, iFile;
	TreeSet<String> dWords = new TreeSet<String>();
	TreeSet<String> wordsFoundTree = new TreeSet<String>();
	
	public BoggleGUI() throws Exception
	{
		window = new JFrame("Boggle Game");
		content = window.getContentPane();
		content.setLayout(new GridLayout(1,3));
		ButtonListener listener = new ButtonListener();
		
		wordsFound = new JTextArea("Words Found");
		wordsFound.setBackground(Color.YELLOW);
		wordsFound.setFont(new Font("TimesRoman" , Font.PLAIN, 20) );
		
		scroll = new JScrollPane(wordsFound);
		
		randomize = new JButton ("Randomize");
		randomize.addActionListener(listener);
		randomize.setFont(new Font("Himalaya", Font.BOLD, 20));
		go = new JButton("GO!");
		go.addActionListener(listener);
		go.setFont(new Font("Himalaya", Font.BOLD, 20));
		
		dimension = new JTextField("DEFAULT DIMENSION: 4x4");
		dimension.setFont(new Font("Calibri", Font.ITALIC, 20));
		inputFile = new JTextField("type boggle filename here");
		inputFile.setFont(new Font("Calibri", Font.ITALIC, 20));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(4,1));
		leftPanel.add(randomize);
		leftPanel.add( inputFile );
		leftPanel.add( dimension );
		leftPanel.add( go );
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		boardPanel = new JPanel();
		boardButtons = new JButton[4][4]; 
		boardPanel.setLayout(new GridLayout(4,4));   
		int i = 0;
		for (int r = 0; r < boardButtons.length; r++)
		{
			for (int c = 0; c < boardButtons.length; c++)
			{
				boardButtons[r][c] = new JButton(alphabet[i++]);   
				boardButtons[r][c].setFont(new Font("Tahoma", Font.BOLD , 16 ));
				boardPanel.add(boardButtons[r][c]);
			}
		}
			
		content.add( leftPanel );
		content.add( boardPanel );
		content.add( scroll );
		
		window.setSize( 960,720);
		window.setVisible( true );
		
		BufferedReader dictionary = new BufferedReader (new FileReader("dictionary.txt"));
		while (dictionary.ready())
		{
			dWords.add(dictionary.readLine());
		}
		dictionary.close();
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
						boardButtons[r][c].setFont(new Font("Tahoma", Font.BOLD , 16 ));
						boardButtons[r][c].setText(alphabet[i]);
					}
				}
			}//END Randomize
			
			if (whichButton == go) 
			{
				wordsFound.setText("Words Found");
				wordsFoundTree.clear();
				String infileName = inputFile.getText();
				Scanner board = null;
				String word = "";
				String [][] boggle = new String[4][4];
				int dimen = 0;
				do 
				{
					try 
					{
						board = new Scanner ( new File(infileName) );
						if (board.hasNextInt())
							dimen = board.nextInt();
						else
							dimen = Integer.parseInt(dimension.getText());
						boardPanel.removeAll();
						boardButtons = new JButton[dimen][dimen]; 
						boardPanel.setLayout(new GridLayout(dimen, dimen)); 
						boggle = new String[dimen][dimen];
						while (board.hasNext())
						{
							for (int r = 0; r < boardButtons.length; r++)
							{
								for (int c = 0; c < boardButtons.length; c++)
								{
									boardButtons[r][c]=new JButton();
									String text=board.next();
									boardButtons[r][c].setText(text);
									boardButtons[r][c].setFont(new Font("Tahoma", Font.BOLD , 16 ));
									boardPanel.add(boardButtons[r][c]);
									
								}
							}
						}
						board.close();
						for (int r = 0; r < boggle.length; r++)
							for (int c = 0; c <boggle[r].length;c++)
								boggle[r][c] = boardButtons[r][c].getText();
								break;
					}
					catch (FileNotFoundException fnfe)
					{
						dimen = Integer.parseInt(dimension.getText());
						boardPanel.removeAll();
						JButton[][]boardButtons2 = new JButton[dimen][dimen]; 
						for (int r = 0; r < boardButtons2.length; r++)
							for (int c = 0; c < boardButtons2.length; c++)
							{
								boardButtons2[r][c] = boardButtons[r][c];
								boardPanel.add(boardButtons2[r][c]);
							}
						boardButtons = boardButtons2;	
						boardPanel.setLayout(new GridLayout(dimen, dimen));
						boggle = new String[dimen][dimen];
						
						for (int r = 0; r < boggle.length; r++)
							for (int c = 0; c <boggle[r].length;c++)
								boggle[r][c] = boardButtons[r][c].getText();
						break;
					}
				}
				while (true);
				
				for (int r = 0; r < boggle.length; r++)
					for (int c = 0; c < boggle[r].length; c++)
						depthFirstSearch(boggle, r, c, word);
				for (String fWords : wordsFoundTree)
					wordsFound.setText(wordsFound.getText() + "\n" + fWords);
			}//END go
			window.repaint();
			window.revalidate();
		
		}//END andtionPerformed
		
	}//END ButtonListener
	private void depthFirstSearch(String[][] boggle, int r, int c, String word )
    {	
		word += boggle[r][c];
		
		if (dWords.contains(word)&&word.length() >= 3)
			wordsFoundTree.add(word);
		else if (!dWords.ceiling(word).startsWith(word))
			return;	
		
		String temp;
		if ((!outOfBound(boggle,r-1,c))&&(boggle[r-1][c] != null)) //N
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r-1, c, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r-1,c+1))&&(boggle[r-1][c+1] != null)) //NE
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r-1, c+1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r,c+1))&&(boggle[r][c+1] != null)) //E
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r, c+1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r+1,c+1))&&(boggle[r+1][c+1] != null)) //SE
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r+1, c+1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r+1,c))&&(boggle[r+1][c] != null)) //S
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r+1, c, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r+1,c-1))&&(boggle[r+1][c-1] != null)) //SW
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r+1, c-1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r,c-1))&&(boggle[r][c-1] != null)) //W
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r, c-1, word);
			boggle[r][c] = temp;
		}
		if ((!outOfBound(boggle,r-1,c-1))&&(boggle[r-1][c-1] != null))  //NW
		{
			temp = boggle[r][c];
			boggle[r][c] = null;
			depthFirstSearch(boggle, r-1, c-1, word);
			boggle[r][c] = temp;
		}
		return;
	}
	
	static boolean outOfBound(String[][] boggle, int r, int c)
	{
		if (r < 0 || c < 0 || r > boggle.length - 1 || c  > boggle.length - 1)
			return true;
		return false;
	}
	
	public static void main(String [] args) throws Exception
	{
		
		new BoggleGUI();
	}
	
}//END BoggleGUI