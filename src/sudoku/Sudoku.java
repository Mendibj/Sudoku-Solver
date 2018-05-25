package sudoku;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Sudoku {
	private final static int EMPTY = 0;
	private final static int N = 9;
	private int board[][] = new int[N][N];

	// CONSTRUCTOR CON NOMBRE DE FICHERO
	public Sudoku(String fileName) {
		try {
			board = FileReader.fileToMatrix(fileName, N, N);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// MÉTODO PARA RESOLVER EL SUDOKU
	public boolean solve() {
		return solveAux(0, 0);
	}

	private boolean solveAux(int row, int column) {
		// Caso base, hemos obtenido todas las soluciones
		if (column > N - 1)
			return true;

		int nextColumn = column + row / (N - 1);
		int nextRow = (int) (row % (N - 1) + Math.pow(0, row / (N - 1)));

		if (getNumber(row, column) != EMPTY)
			return solveAux(nextRow, nextColumn);

		else {
			List<Integer> list = posibilities(row, column);
			while (!list.isEmpty()) {
				setNumber(row, column, list.remove(0));
				if (solveAux(nextRow, nextColumn))
					return true;
			}
			setNumber(row, column, 0);
			return false;
		}
	}

	// COMPRUEBA SI SE CUMPLEN LAS RESTRICCIONES EN UN CUADRANTE
	private boolean checkSquare(int row, int column, int num) {
		int rowIni = (row / 3) * 3;
		int columnIni = (column / 3) * 3;

		for (int i = rowIni; i < rowIni + 3; i++) {
			for (int j = columnIni; j < columnIni + 3; j++) {
				if (getNumber(i, j) == num)
					return false;
			}
		}
		return true;
	}

	// COMPRUEBA SI SE CUMPLEN LAS RESTRICCIONES FILA/COLUMNA
	private boolean checkRC(int row, int column, int num) {
		for (int i = 0; i < N; i++) {
			if (getNumber(row, i) == num || getNumber(i, column) == num)
				return false;
		}
		return true;
	}

	// DEVUELVE UNA LISTA CON LOS POSIBLES NÚMEROS CADA UNA DETERMINADA CASILLA
	private List<Integer> posibilities(int row, int column) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (checkSquare(row, column, i) && checkRC(row, column, i))
				list.add(i);
		}
		return list;
	}

	// IMPRIMIR EL TABLERO
	public String toString() {
		String res = "";
		for (int i = 0; i < N; i++) {
			if (i != 0)
				res += "\n";
			for (int j = 0; j < N; j++) {
				res += getNumber(i, j) + " ";
			}
		}
		return res;
	}

	public void setNumber(int row, int column, int num) {
		board[row][column] = num;
	}

	public int getNumber(int row, int column) {
		return board[row][column];
	}
}
