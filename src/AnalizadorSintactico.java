import java.util.ArrayList;
import java.util.regex.Pattern;

public class AnalizadorSintactico {	
	
	ArrayList<String[]> reglas;
	
	public AnalizadorSintactico(ArrayList<String[]> reglas) {
	
		this.reglas = reglas;
	}
	
	public void comprobarCadena(String exp) {
		long empiezo = System.nanoTime();
		String expresion = exp;
		int beg=0;
		int end=0;
		int diff=1;
		boolean palabraTerminada;
		while(true) {
			palabraTerminada = false;
			for(int j=0;j<expresion.length();j++) {
				for(int i=0;i<reglas.size();i++) {					
					beg = 0;
					end = 0;
					diff=j+1;
					while(true) {
						end = beg + diff;
						if(end>=expresion.length()+1) {
							break;
						}
						String palabra  = expresion.substring(beg, end);
					//	System.out.println(palabra);
						if(palabra.equals(reglas.get(i)[1])) {					
							palabraTerminada=true;
							expresion = expresion.substring(0,beg)+reglas.get(i)[0]+expresion.substring(end,expresion.length());
							System.out.println("..."+expresion);
						}
						beg++;
					}					
				}
			}
			if(!palabraTerminada) {
				System.out.println("TERMINO Y FALLO");
				break;
			}
			if(expresion.equals("BOOLEANO")||expresion.equals("FUNCION")) {
				System.out.println("FUNCIONOOOOOOOOOOOOOOOOO");
				break;
			}
		}
		long fin = System.nanoTime();
		
		System.out.println((fin-empiezo)/1000000000.00);
	}
	
	
	
	
	
	
}
