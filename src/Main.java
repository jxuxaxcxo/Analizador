import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	

	public static void main(String [] args) {


		
		
		Reglas reglas = new Reglas();
		
		ArrayList<String> lineasDeTokens = new ArrayList<String>() ;
		try {
			AnalizadorLexico lexico = new AnalizadorLexico();
			lineasDeTokens = lexico.getLineasDeTokens();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		AnalizadorSintactico analizador = new AnalizadorSintactico(reglas.getRules());
		
	//analizador.comprobarCadena(lineasDeTokens.get(1));
		


//	while(true) {
//		
//
//		Scanner scanner = new Scanner(System.in);
//		
//		System.out.println("Ingrese la cadena a probar: ");
//		
//		String expresion = scanner.nextLine();
//		
//		Reglas reglas= new Reglas();
//		
//		
//		AnalizadorSintactico analizador = new AnalizadorSintactico(reglas.getRules());
//		
//		analizador.comprobarCadena(expresion);
//		
//	
//		
//		
//		}
	}
}
