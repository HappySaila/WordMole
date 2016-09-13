//responsible for shuffeling the vowel constanent component grid and 
//shuffles them together

import java.util.Arrays;

public class Shuffler {
	//does not create a grid but rather shuffles values with in an existing grid
	//tests if grid works
	public static void main(String[] args) {
		Grid grid = new Grid();
		Shuffler shuffler = new Shuffler();
		char[][] gridShuffled = shuffler.finalShuffle(grid.getGrid());
//		for (int i = 0; i < 6; i++) {
//			System.out.println(gridShuffled[i]);
//		}
		Shuffler shuffler1 = new Shuffler();
		char[][] gridShuffled1 = shuffler1.shuffle1(grid.getGrid());
		gridShuffled1 = shuffler1.shuffle2(gridShuffled1);
//		checks if operation is carried out correctly
		shuffler1.printGrid(gridShuffled1);
		
	}
	private char[][] gridShuffled;
	public char[][] finalShuffle(char[][] grid) {
//		Shuffler shuffler = new Shuffler();
		char[][] gridShuffled = this.shuffle1(grid);
		gridShuffled = this.shuffle2(gridShuffled);
		return gridShuffled;
	}
	//creates shuffler object
	public Shuffler(){
		Grid grid = new Grid();
		gridShuffled = this.finalShuffle(grid.getGrid());
		
	}
	
	//	swaps individual characters so that vowels mix with letters
	public char[][] shuffle2(char[][] grid){
		//shuffles top two and bottom 2 lines in the grid
		for (int i = 0; i < 5; i+=4) {
			for (int j = 0; j < 6; j+=2) {
				char temp = grid[i+1][j];
				grid[i+1][j] = grid[i][j];
				grid[i][j] = temp;
			}
		}
		for (int i = 0; i < 5; i+=2) {
			char temp = grid[2][i];
			grid[2][i]=grid[3][i];
			grid[3][i]= temp;
			
		}
		return grid;
	}
	//swaps rows for individual shuffling
	public char[][] shuffle1(char[][] grid){
		char [] temp = grid [5];
		grid[5] = grid [0];
		grid[0] = temp;
		char [] temp2 = grid [3];
		grid[3] = grid [2];
		grid[2] = temp2;
		//tests if grid is correctly printed
//		for (int i = 0; i < 6; i++) {
//			System.out.println(grid[i]);
//		}
		return grid;
	}
	public void printGrid(char[][] grid){
		for (int i = 0; i < grid.length; i++) {
			System.out.println(grid[i]);
		}
	}
	public char[][] getGrid(){
		return gridShuffled;
	}

}
