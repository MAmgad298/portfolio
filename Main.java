package def;

public class Main {

	public static final int GRID_SIZE=9;
	public static void main(String[] args) {
		
		int[][] board= {
		               {7, 0, 2, 0, 5, 0, 6, 0, 0},
						{0, 0, 0, 0, 0, 3, 0, 0, 0},
						{1, 0, 0, 0, 0, 9, 5, 0, 0},
						{8, 0, 0, 0, 0, 0, 0, 9, 0},
						{0, 4, 3, 0, 0, 0, 7, 5, 0},
						{0, 9, 0, 0, 0, 0, 0, 0, 8},
						{0, 0, 9, 7, 0, 0, 0, 0, 5},
						{0, 0, 0, 2, 0, 0, 0, 0, 0},
						{0, 0, 7, 0, 4, 0, 2, 0, 3} 
		};
		
		if(solveBoard(board)) {
			System.out.println("Successfully Solved !!!");
			System.out.println();
			
		}else
			System.out.println("Unsolvable :(");

//		printBoard(board);
		
		System.out.println("----------------------------------------------");
		recPrintBoard(board,0,0,GRID_SIZE,GRID_SIZE);
	}
	
	private static void printBoard(int[][] board) {
		
		for(int i = 0 ; i < GRID_SIZE ; i++) {
			if(i%3==0 && i !=0)
				System.out.println("----------------");
			
			for(int j = 0; j < GRID_SIZE ; j++) {
				if(j%3==0 && j !=0)
					System.out.print(" | ");
				System.out.print(board[i][j]);
				
			}
			
			System.out.println();
			
		}
		
	}
	
	private static int recPrintBoard(int[][] board, int rowIdx,int colIdx, int row, int col) {
		System.out.println();
		if(rowIdx < row) {
			
			if(colIdx < col) {
				System.out.print(board[rowIdx][colIdx]);
				recPrintBoard(board, rowIdx, colIdx + 1, row, col);
			}
			
			recPrintBoard(board, rowIdx+1, colIdx , row, col);
		}
		return 0;
		
	}
	
	

	public static boolean isNumberRow(int[][] board, int number, int row) {
		for(int i = 0; i < GRID_SIZE; i++)
			if(board[row][i] == number)
				return true;
		
		return false;
	}
	
	public static boolean isNumberCol(int[][] board, int number, int col) {
		for(int i = 0; i < GRID_SIZE; i++)
			if(board[i][col] == number)
				return true;
		
		return false;
	}
	
	public static boolean isNumberBox(int[][] board, int number, int row, int col) {
		int localBoxRow = row - row %3;
		int localBoxCol = col - col%3;
		
		for(int i=localBoxRow ; i < localBoxRow + 3 ; i++) {
			for(int j = localBoxCol ; j < localBoxCol + 3 ; j++) 
				if(board[i][j] == number)
					return true;
		}
		return false;
	}
	
	public static boolean isValidPlacement(int[][] board, int number, int row, int col) {
		return !isNumberRow(board, number, row) && 
				!isNumberCol(board, number, col) &&
				!isNumberBox(board, number, row, col);
	}
	
	public static boolean solveBoard(int[][] board) {
		for(int i = 0; i < GRID_SIZE ; i++) {
			for(int j= 0 ; j < GRID_SIZE; j++) {
				
				if(board[i][j] == 0) {
					
					for(int numToTry = 1; numToTry <= GRID_SIZE ; numToTry++) {
						
						if(isValidPlacement(board, numToTry, i, j)) {
							
							board[i][j] = numToTry;
							
							if(solveBoard(board))
								return true;
							else {
								board[i][j]=0;
							}
						}
					}
					
					return false;
				}
			}
		}
		return true;
	}

}
