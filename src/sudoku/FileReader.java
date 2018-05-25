package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	private FileReader(){
		
	}
	
	public static int[][] fileToMatrix(String fileName, int row, int column)
			throws FileNotFoundException {
		int board[][] = new int[row][column];
		Scanner scanner = new Scanner(new File(fileName));
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				int num = Integer.parseInt(scanner.next());
				board[i][j] = num;
			}
		}
		scanner.close();
		return board;
	}
}
