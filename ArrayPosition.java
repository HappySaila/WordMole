
public class ArrayPosition {
	private int position=0;
	private int value=0;
	
	
	
	public ArrayPosition(){
		
	}
	
	public int value(char a, char[] LETTER, int[] VALUE ){
		for (int i = 0; i < VALUE.length; i++) {
			if (a==LETTER[i]){
				position=i;
			}
		}
		value = VALUE[position];
		return value;
	}
}
