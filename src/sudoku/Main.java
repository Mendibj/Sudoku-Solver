package sudoku;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Sudoku s = new Sudoku("ENTRADA.txt");
		System.out.println(s.solve());
		System.out.println(s);

	}

}
