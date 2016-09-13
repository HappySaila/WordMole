import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//handels all methods for when a button in the grid is clicked
public class Handler {
	
	List<String> list = new ArrayList<String>();
	private int score;
//	private TileGUI oldTile;
	private String word="";
	private ArrayList<String> Array;
	private ArrayList<TileGUI> tileGUIArray = new ArrayList<TileGUI>();
	private int invalidCounter;
	public int scoreTotal=0;
	
	public Handler() throws FileNotFoundException, IOException{
		Array =new  ArrayList<String>(Arrays.asList(FileToArray.read("src/EnglishWords2.txt")));
	}
	
	public int getX(TileGUI tile, TileGUI[][] grid ){
		//returns the x value of a tileGUI object in the grid
		int x = 0;
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if (grid[i][j]==tile){
							x=j;
							break;
						}
					}
				}
		return x;
	}
	
	public int getY(TileGUI tile, TileGUI[][] grid ){
		//returns the y value of a tileGUI object in the grid
		int x = 0;
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if (grid[i][j]==tile){
							x=i;
							break;
						}
					}
				}
		return x;
	}
	
	public boolean checkAdjacent(TileGUI last, TileGUI next, TileGUI[][] grid) throws FileNotFoundException, IOException{
		//checks if a tile is surrounded by the previous tile clicked
		//if it is returns true and will set that tile to the last tile clicked
		Handler hand = new Handler();
		int lastX = hand.getX(last , grid);
		int lastY = hand.getY(last,grid);
		int nextX = hand.getX(next,grid);
		int nextY = hand.getY(next,grid);
//		System.out.println(lastX+" "+lastY+" "+nextX+" "+nextY);
		boolean answer=true;
		for (int i = lastX-1; i < lastX+2;i++) {
			for (int j = lastY-1; j < lastY+2;j++) {
				try{
				if (grid[i][j]==grid[lastX][lastY]){
					continue;}
				else if(grid[i][j]==grid[nextX][nextY]){
					answer=true;
						break;
				}
				else answer=false;
				}
				catch (ArrayIndexOutOfBoundsException e){
					continue;
				}
				}
			if (answer==true)
				break;
			}
		return answer;
		}

	public void ifTrue(TileGUI tile){
		//will highlight the button, append the value of the tile to an array and the score to another
		tileGUIArray.add(tile);
		tile.setForeground(Color.ORANGE);
		char letter = tile.getTile().letter();
		int value = tile.getTile().value();
		score+=value;
		word = word+letter;
		
		
	}
	public void ifFalse(){
		//will pop an error window letting the user know that the tile is not adjacent to old tile and 
		JFrame window = new JFrame("EY!");
		window.setSize(350,95);
		window.setLayout(new FlowLayout());
		JLabel label = new JLabel("Invalid move! Tile must be adjacent to selected tile.");
		window.add(label);
		JButton button = new JButton("Okay");
		button.addActionListener(e->{
			window.dispose();
		});
		button.setBackground(Color.white);
		window.add(button);
		window.setVisible(true);
		
		
	}
	

	public void instructions() {
		//will pop an error window letting the user know that the tile is not adjacent to old tile and 
				JFrame window = new JFrame("Greetings Stranger");
				window.setSize(600,220);
				window.setLayout(new FlowLayout());
				JTextArea label = new JTextArea("Welcome traveller to my awesome boring game \n that you gonna have to mark for the 100th time. \n"
						+ "The rules to the game are very simple.\n All you have to do is make as many words as you can in the set time. \n"
						+ "However, only adjacent tiles may be clicked and only real words can be made...obviously!\n"
						+ "And also do take note that the score system is a little different to what is expected,\n"
						+ " your score will bulk up with the previous score at a fraction. So the more words you make\n"
						+ "the greater the bonus!");
				label.setSize(new Dimension(400,400));
				window.add(label);
				JButton button = new JButton("Thanks buddy! Now to the Game\n");
				button.setForeground(Color.ORANGE);
				JLabel label2 = new JLabel("Created by Grant Wilson WLSGRA012");
				window.add(label2);
				button.addActionListener(e->{
					window.dispose();
				});
				button.setBackground(Color.white);
				window.add(button);
				window.setVisible(true);
		
	}

	public void submit() {
		// will check if the word is in a dictionary and will throw and error if the word isnt
		// will also update the current score using the word value
		word = word.toLowerCase();
		if(Array.contains(word)){
			scoreWindow();
			scoreTotal+=score+(0.5*scoreTotal);
			GUI.wordScore.setText("Score= "+scoreTotal);
			score=0;
			GUI.setOldTile(null);
			word="";
//			addWord();
		}
		else {
			invalidWord();
			clear();
			word="";
			GUI.setOldTile(null);
			}
		tileGUIArray.clear();
		
	}
	
	public void scoreWindow(){
		//will display a window telling the user what word was made
		JFrame window = new JFrame("Stranger!");
		window.setSize(300,90);
		window.setLayout(new FlowLayout());
		JTextArea label = new JTextArea("Your word scored "+score+" points!");
		label.setSize(new Dimension(400,400));
		window.add(label);
		JButton button = new JButton("Thanks buddy!\n");
		button.setForeground(Color.ORANGE);
		button.addActionListener(e->{
			window.dispose();
		});
		button.setBackground(Color.white);
		button.setForeground(Color.ORANGE);
		window.add(button);
		window.setVisible(true);

	}
	public void invalidWord(){
		//will display a window telling the user what word was made
		if (invalidCounter==3){
			gameOver();
		}
		else{
		JFrame window = new JFrame("Stranger!");
		window.setSize(400,120);
		window.setLayout(new FlowLayout());
		JTextArea label = new JTextArea(word+ "??, Just like supposably! What type of word is supposably??\n You have "+(3-invalidCounter)+" errors left");
		label.setSize(new Dimension(600,400));
		window.add(label);
		JButton button = new JButton("Thanks buddy!\n");
		button.setForeground(Color.ORANGE);
		button.addActionListener(e->{
			window.dispose();
		});
		button.setBackground(Color.white);
		button.setForeground(Color.ORANGE);
		window.add(button);
		window.setVisible(true);
		invalidCounter+=1;
		}
		

	}
	
	private void gameOver() {
		// will open a window that ends the game and previews final score
		JFrame window = new JFrame("Good Travelings!");
		window.setSize(400,130);
		window.setLayout(new FlowLayout());
		JTextArea label = new JTextArea("Good Run Lad... You almost made it all the way! \n I think!"
				+ " Anyways, here is your Final Score. \n"+ score);
		label.setSize(new Dimension(600,400));
		window.add(label);
		JButton button = new JButton("Thanks buddy! Let me at it again!\n");
		button.setForeground(Color.ORANGE);
		button.addActionListener(e->{
			window.dispose();
			GUI gui2 = null;
			score=0;
			GUI.wordScore.setText("Score= "+score);;
			try {
				gui2 = new GUI();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gui2.setVisible(true);
		});
		button.setBackground(Color.white);
		button.setForeground(Color.ORANGE);
		window.add(button);
		window.setVisible(true);
		
	}

	public void addWord(){
		//adds a word to the words found panel
		
	}

	public void clear() {
		// will make  
		System.out.println("works");
		for (int i = 0; i < tileGUIArray.size(); i++) {
			TileGUI tile = tileGUIArray.get(i);
			tile.setForeground(Color.DARK_GRAY);
			//System.out.println(i);
		}
	}
	
}
