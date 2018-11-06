import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	

	public static void main(String [] args) throws IOException {

		AnalizadorLexico al = new AnalizadorLexico();
		if(al.seguir) 
		{
			PreSemantico preSem = new PreSemantico(al.lineasDeTokens);
			preSem.crearTablaDeSimbolos();
			ALL1 all1 = new ALL1(preSem.nuevasLineasDeTokens);
			int [] errores = all1.probarCadena();
			if(errores[0] != -1 && errores[1] != -1)
			{
				System.out.print(" EN LA LINEA " + errores[0] + " CERCA DE >" + al.codigo.get(errores[0]).get(errores[1]) + 
						al.codigo.get(errores[0]).get(errores[1]+1) 
						+ al.codigo.get(errores[0]).get(errores[1]+2) + "<");
			}
		}


	}
}
