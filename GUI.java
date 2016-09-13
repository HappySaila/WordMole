import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI extends JFrame implements ActionListener{
	private static final Object Letter = null;
	//instance variables
	private int width=600;
	private int hieght=600;
	private Shuffler gridVal = new Shuffler();
	private char[][] gridLetters = gridVal.getGrid();
	private final char[] LETTER = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y','Z'};  
	private final int[] VALUE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	private TileGUI[][] tiles = new TileGUI[6][6];
	private static TileGUI oldTile;
	private GUI gui;
	private char[] word;
	private static int score=0;
	private Handler hand;
	public JPanel wordsFound = new JPanel();
	public static JLabel wordScore = new JLabel("score= "+score);
	JLabel header = new JLabel("Words Found");
	
	
	public GUI() throws FileNotFoundException, IOException{
		//creates the JFrame Main Window
		super("Scrabble!");
		setSize(width,hieght);
		getContentPane().setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		hand = new Handler();
		//Creates menu bar with menu button
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		bar.add(menu);
		add(bar, BorderLayout.NORTH);
		
		JMenuItem newGame = new JMenuItem("New Game");
		menu.add(newGame);
		newGame.addActionListener(this);
		JMenuItem instructions = new JMenuItem("Instructions");
		menu.add(instructions);
		instructions.addActionListener(this);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		menu.add(exit);
		
		//creates right tab with words made as a header and panel for words
		//submit button
		JButton submit = new JButton("SUBMIT");
		submit.setBackground(Color.orange);
		submit.setForeground(Color.WHITE);
		submit.addActionListener(this);
		JPanel right = new JPanel();
		
		
		right.setLayout(new GridLayout(0,1));
//		JLabel header = new JLabel("Words Found");
		wordScore.setForeground(Color.ORANGE);
		JButton clear = new JButton("Clear");
		clear.setBackground(Color.WHITE);
		clear.setForeground(Color.ORANGE);
		clear.setPreferredSize(new Dimension(right.getWidth(),100));
		clear.addActionListener(this);
		
		right.add(wordScore);
		right.add(submit);
		right.add(clear);
		add(right, BorderLayout.EAST);
		
		//creates grid in the center
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(6,6));
		gridPanel.setBackground(Color.ORANGE);
		
		//creates an array of tileGUI objects
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				char val = gridLetters[i][j]; 
				val = Character.toUpperCase(val);
				ArrayPosition arr = new ArrayPosition();
				int letterValue = arr.value(val,LETTER,VALUE);
				TileGUI tile = new TileGUI(new Tile(val,letterValue));
				tiles[i][j] = tile;
				gridPanel.add(tile);
//				Handler hand = new Handler();
				tile.addActionListener(e->{
					//action listener for the button
					if (getOldTile()==null){
						setOldTile(tile);
						hand.ifTrue(tile);
					}
					else {
						boolean checkAdjacent = false;
						try {
							checkAdjacent = hand.checkAdjacent(tile, getOldTile(), tiles);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (checkAdjacent==true){
							setOldTile(tile);
							hand.ifTrue(tile);
							header.setText("Words Found: "+word);
						}
						else hand.ifFalse();
					}
					
				});
			}
		}
			
		add(gridPanel, BorderLayout.CENTER);
	}
	public TileGUI[][] getGrid(){
		//returns the grid of objects of tile gui
		return tiles;
	}
	public static void setOldTile(TileGUI tile){
		oldTile=tile;
	}
	public TileGUI getOldTile() {
		return oldTile;
	}
	
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		if (button.equals("Exit")){
			System.exit(0);
		}if (button.equals("New Game")){
			this.dispose();
			GUI gui2 = null;
			score=0;
			wordScore.setText("Score= "+score);;
			try {
				gui2 = new GUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gui2.setVisible(true);
		}
		if (button.equals("Instructions")){
			hand.instructions();
		}
		if (button.equals("SUBMIT")){
			hand.submit();
		}
		if (button.equals("Clear")){
			hand.clear();
		}
		
	}
}
