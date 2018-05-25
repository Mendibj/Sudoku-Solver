package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {
	private final static int ROWS = 9;
	private final static int COLUMNS = 9;
	private int board[][] = new int[ROWS][COLUMNS];

	// CONSTRUCTOR CON NOMBRE DE FICHERO
	public Sudoku(String fileName) {
		try {
			board = fileToBoard(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Sudoku() {
	};

	// MÉTODO PARA RESOLVER EL SUDOKU
	public boolean solve() {
		return solveAux(0, 0);
	}

	private boolean solveAux(int row, int column) {
		//Caso base, hemos obtenido todas las soluciones
		if(column == 9) return true;
		
		int nextColumn;
		int nextRow;
		if (row < 8) {
			nextRow = row + 1;
			nextColumn = column;
		} else {
			nextRow = 0;
			nextColumn = column + 1;

		}

		// si es un número ya generado hacemos la llamada recursiva del
		// siguiente
		if (getNumber(row, column) != 0)
			return solveAux(nextRow, nextColumn);

		// si es una casilla vacía obtenemos una lista de combinaciones,
		// probamos una y hacemos la llamada recursiva del siguiente
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

	// COMPRUEBA SI SE CUMPLEN LAS RESTRICCIONES EN UNA FILA
	private boolean checkRow(int row, int num) {
		for (int i = 0; i < COLUMNS; i++) {
			if (getNumber(row, i) == num)
				return false;
		}
		return true;
	}

	// COMPRUEBA SI SE CUMPLEN LAS RESTRICCIONES EN UNA COLUMNA
	private boolean checkColumn(int column, int num) {
		for (int i = 0; i < COLUMNS; i++) {
			if (getNumber(i, column) == num)
				return false;
		}
		return true;
	}

	// DEVUELVE UNA LISTA CON LOS POSIBLES NÚMEROS CADA UNA DETERMINADA CASILLA
	private List<Integer> posibilities(int row, int column) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			if (checkSquare(row, column, i) && checkRow(row, i)
					&& checkColumn(column, i))
				list.add(i);
		}
		return list;
	}

	// DADO UN NOMBRE DE FICHERO DEVUELVE UNA MATRIZ
	private static int[][] fileToBoard(String fileName)
			throws FileNotFoundException {
		int board[][] = new int[ROWS][COLUMNS];
		Scanner scanner = new Scanner(new File(fileName));
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				int num = Integer.parseInt(scanner.next());
				board[i][j] = num;
			}
		}
		scanner.close();
		return board;
	}

	// IMPRIMIR EL TABLERO
	public String toString() {
		String res = "";
		for (int i = 0; i < ROWS; i++) {
			if (i != 0)
				res += "\n";
			for (int j = 0; j < COLUMNS; j++) {
				res += board[i][j] + " ";
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
