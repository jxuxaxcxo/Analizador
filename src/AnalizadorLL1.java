import java.util.ArrayList;

public class AnalizadorLL1 {

	public ArrayList<ArrayList<ArrayList<String>>> tabla;
	public ArrayList<String> izquierda, derecha, cadena;
	public ArrayList<ArrayList<String>> gramatica;
	public ArrayList<String> listaNoTerminales;
	public int filaActual;
	
	
	public AnalizadorLL1(ArrayList<String> cadena, int fila, ArrayList<ArrayList<String>> gramatica) {
		
		this.filaActual=fila;
		this.gramatica = gramatica;
		for(int j = 0; j < this.gramatica.size(); j++)
		{
			if(!this.listaNoTerminales.contains(this.gramatica.get(j).get(0)))
				this.listaNoTerminales.add(this.gramatica.get(j).get(0));
				
		}
		//creando la tabla
	    this.cadena = cadena;
		izquierda = new ArrayList<String>();
		derecha  = new ArrayList<String>();
		tabla = new ArrayList<ArrayList<ArrayList<String>>>();
		/*for (int i = 0; i < 5; i++)
		{
			ArrayList<ArrayList<String>> fila = new ArrayList<ArrayList<String>>();
			for(int j = 0; j < 5; j++)
			{
				ArrayList<String> interseccion = new ArrayList<String>();
				fila.add(interseccion);
			}
			tabla.add(fila);
		}*/

		this.llenarTabla();
		izquierda.add("$");
		izquierda.add("S");
		derecha = cadena;
		derecha.add("$");
	}
	//Devuelve -1 si se acepto la cadena, devuelve un valor mayor o igual a 0 si no se acepto y ese valor
	//el indice en el cual se encuentra el error
	public int probarCadena() {

		boolean probando =true;
		int indice = 0;
		int x,y;
		while(probando) {


			for(int n = 0; n < izquierda.size(); n++)
			{
				System.out.print(izquierda.get(n));
			}
			System.out.print(" ");
			for(int n = 0; n < derecha.size(); n++)
			{
				System.out.print(derecha.get(n));
			}
			System.out.println();


			if(!(derecha.get(indice).equals("$") && izquierda.get(izquierda.size()-1).equals("$")))
			{
				if(izquierda.get(izquierda.size()-1).equals(derecha.get(indice)))
				{
					izquierda.remove(izquierda.size()-1);
					indice += 1;
				}
				else
				{
					x = this.getIndexX(izquierda.get(izquierda.size()-1));
					y = this.getIndexY(derecha.get(indice));
					if(x == -1 || tabla.get(x).get(y).get(0).equals(""))
					{
						System.out.println("ERROR EN FILA");
						return indice;
					}
					izquierda.remove(izquierda.size()-1);
					if(!tabla.get(x).get(y).get(0).equals("&"))
					{
						for(int i = 0;i<tabla.get(x).get(y).size(); i++)
						{
							izquierda.add(tabla.get(x).get(y).get((tabla.get(x).get(y).size()-1) - i));
						}
					}
				}
			}
			else
				probando = false;



		}
		System.out.println("FILA CORRECTA");
		return -1;

	}
	public int getIndexX(String terminal) {
	
		for(int i = 0;i < tabla.size();i++) {
			if(tabla.get(i).get(0).get(0).equals(terminal)) {
				return i;
			}
		}
		return -1;
		
	}
	
	public int getIndexY(String noTerminal) {
		
		for(int i = 0;i < tabla.size();i++) {
			if(tabla.get(0).get(i).get(0).equals(noTerminal)) {
				return i;
			}
		}
		
		return -1;
		
		
	}

	public void llenarTabla()
	{
		ArrayList<ArrayList<String>> fila = new ArrayList<ArrayList<String>>();
		ArrayList<String> interseccion1 = new ArrayList<String>();
		ArrayList<String> interseccion2 = new ArrayList<String>();
		ArrayList<String> interseccion3 = new ArrayList<String>();
		ArrayList<String> interseccion4 = new ArrayList<String>();
		ArrayList<String> interseccion5 = new ArrayList<String>();
		interseccion1.add("");
		interseccion2.add("a");
		interseccion3.add("b");
		interseccion4.add("d");
		interseccion5.add("$");
		fila.add(interseccion1);
		fila.add(interseccion2);
		fila.add(interseccion3);
		fila.add(interseccion4);
		fila.add(interseccion5);
		
		ArrayList<ArrayList<String>> fila2 = new ArrayList<ArrayList<String>>();
		ArrayList<String> interseccion6 = new ArrayList<String>();
		ArrayList<String> interseccion7 = new ArrayList<String>();
		ArrayList<String> interseccion8 = new ArrayList<String>();
		ArrayList<String> interseccion9 = new ArrayList<String>();
		ArrayList<String> interseccion10 = new ArrayList<String>();
		interseccion6.add("S");
		interseccion7.add("A");
		interseccion7.add("a");
		interseccion8.add("A");
		interseccion8.add("a");
		interseccion9.add("A");
		interseccion9.add("a");
		interseccion10.add("");
		fila2.add(interseccion6);
		fila2.add(interseccion7);
		fila2.add(interseccion8);
		fila2.add(interseccion9);
		fila2.add(interseccion10);
		
		ArrayList<ArrayList<String>> fila3 = new ArrayList<ArrayList<String>>();
		ArrayList<String> interseccion11 = new ArrayList<String>();
		ArrayList<String> interseccion12 = new ArrayList<String>();
		ArrayList<String> interseccion13 = new ArrayList<String>();
		ArrayList<String> interseccion14 = new ArrayList<String>();
		ArrayList<String> interseccion15 = new ArrayList<String>();
		interseccion11.add("A");
		interseccion12.add("B");
		interseccion12.add("D");
		interseccion13.add("B");
		interseccion13.add("D");
		interseccion14.add("B");
		interseccion14.add("D");
		interseccion15.add("");
		fila3.add(interseccion11);
		fila3.add(interseccion12);
		fila3.add(interseccion13);
		fila3.add(interseccion14);
		fila3.add(interseccion15);
		
		ArrayList<ArrayList<String>> fila4 = new ArrayList<ArrayList<String>>();
		ArrayList<String> interseccion16 = new ArrayList<String>();
		ArrayList<String> interseccion17 = new ArrayList<String>();
		ArrayList<String> interseccion18 = new ArrayList<String>();
		ArrayList<String> interseccion19 = new ArrayList<String>();
		ArrayList<String> interseccion20 = new ArrayList<String>();
		interseccion16.add("B");
		interseccion17.add("&");
		interseccion18.add("b");
		interseccion19.add("&");
		interseccion20.add("");
		fila4.add(interseccion16);
		fila4.add(interseccion17);
		fila4.add(interseccion18);
		fila4.add(interseccion19);
		fila4.add(interseccion20);
		
		ArrayList<ArrayList<String>> fila5 = new ArrayList<ArrayList<String>>();
		ArrayList<String> interseccion21 = new ArrayList<String>();
		ArrayList<String> interseccion22 = new ArrayList<String>();
		ArrayList<String> interseccion23 = new ArrayList<String>();
		ArrayList<String> interseccion24 = new ArrayList<String>();
		ArrayList<String> interseccion25 = new ArrayList<String>();
		interseccion21.add("D");
		interseccion22.add("&");
		interseccion23.add("");
		interseccion24.add("d");
		interseccion25.add("");
		fila5.add(interseccion21);
		fila5.add(interseccion22);
		fila5.add(interseccion23);
		fila5.add(interseccion24);
		fila5.add(interseccion25);
		
		tabla.add(fila);
		tabla.add(fila2);
		tabla.add(fila3);
		tabla.add(fila4);
		tabla.add(fila5);
		
		
		
		
	}
	
	
	public void setPrimeros() 
	{
		
		
	}
	
	public void setSegundos() {
		
		
	}

	public void imprimir() {
		for(int i = 0; i < tabla.size();i++)
		{
			for(int j = 0; j < tabla.get(i).size(); j++)
			{
				for(int n = 0; n < tabla.get(i).get(j).size(); n++)
				{
					System.out.print(tabla.get(i).get(j).get(n));
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}
	
	
			
}

