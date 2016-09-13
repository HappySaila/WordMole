//tests to see if the methods in the handler class work correctly
public class HandlerDemo {
	public static void main(String[] args) {
		/*Handler hand = new Handler();
		GUI gui = new GUI();
		gui.setVisible(true);
		TileGUI[][] grid = gui.getGrid();
		int lastX  = hand.getX(grid[1][1], grid);
		int nextX  = hand.getX(grid[2][2], grid);
		int lastY  = hand.getY(grid[1][1], grid);
		int nextY  = hand.getY(grid[2][2], grid);
		
		boolean check1 = hand.checkAdjacent(grid[5][5],grid[2][2],grid);
		boolean check2 = hand.checkAdjacent(grid[5][5],grid[5][4],grid);
		
		System.out.println("The Co ordinates of the old item are: ("+lastX+","+lastY+")");
		System.out.println("The Co ordinates of the new item are: ("+nextX+","+nextY+")");
		System.out.println("The first check is supposed to out put TRUE and outputted: "+check1);
		System.out.println("The first check is supposed to out put FALSE and outputted: "+check2);
		
		char letterOld = grid[1][1].getTile().letter();
		char letterNew = grid[2][1].getTile().letter();
		
		
		System.out.println("The old letter in the grid at (1,1) is:"+letterOld);
		System.out.println("The old letter in the grid at (2,1) is:"+letterNew);*/
		
		//tests getX function
		/*Handler hand = new Handler();
		GUI gui = new GUI();
		gui.setVisible(true);
		TileGUI[][] grid = gui.getGrid();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(hand.getX(grid[i][j], grid));
				System.out.print(hand.getY(grid[i][j], grid));
			}
			System.out.println("");
		}*/
//		tests checkAdjacent method
		/*Handler hand = new Handler();
		GUI gui = new GUI();
		gui.setVisible(true);
		TileGUI[][] grid = gui.getGrid();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				boolean oneTrue = hand.checkAdjacent(grid[1][1], grid[i][j], grid);
				boolean oneFalse = hand.checkAdjacent(grid[5][5], grid[3][5], grid);
				System.out.println("true: "+oneTrue+"  letter= "+grid[i][j].getTile().letter());
			}
		}
		
		System.out.println("False: "+oneFalse);
		
	}
}*/
