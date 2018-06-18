package sudoku;

public class Main {

	public static void main(String[] args) {
		
		//EJEMPLO DE SUDOKU RESOLUBLE
		Sudoku s = new Sudoku("ENTRADA.txt");
		if(s.solve()) System.out.println("Éxito al resolver");
		else System.out.println("No se pudo resolver");
		System.out.println(s);

	}

}
