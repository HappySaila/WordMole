//will create an empty grid
import java.util.Random;

public class Grid {
		public char[][] grid;
	public static void main(String[] args) {
		Grid grid = new Grid();
				
	}
	public Grid(){
		//creates first three rows of grid to be vowels that will be shuffled
		grid = new char[6][6];
		for (int i = 0; i < 3; i++) {
			grid[i][1]='a';
			grid[i][2]='e';
			grid[i][3]='e';
			grid[i][4]='i';
			grid[i][5]='o';
			grid[i][0]='u';
		//tests if the grid prints out correctly
		//System.out.println(grid[i]);	
		}
		//creates last three rows with letters that will be reshuffled
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				grid[i][j]=randomLetter();
			}	
		//tests if the grid prints out correctly
//		System.out.println(grid[i]);	
		}
	}
	public char randomLetter(){
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}
	public char[][] getGrid(){
		return grid;
	}
				
}


