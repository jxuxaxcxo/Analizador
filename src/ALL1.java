
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;


public class ALL1 {
	
	
	public String [] [] tabla;
	public ArrayList<String> derecha, izquierda, terminales, noterminales, primeros, segundos;
	public  ArrayList<String[]> reglas;
	public ArrayList<ArrayList<String>> lineasDeTokens;
	
	public ALL1(ArrayList<ArrayList<String>> lineasDeTokens) throws IOException 
	{
		this.lineasDeTokens = lineasDeTokens;
		this.derecha = lineasDeTokens.get(0);
		izquierda = new ArrayList<String>();
		tabla = new String [64][44];
		/*File file = new File("C:\\Users\\Jorge Zamora\\Desktop\\tablaGramatica.xlsx");
		FileInputStream fi = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> rowit = sheet.iterator();
		int fila = 0, columna = 0;
		while(rowit.hasNext())
		{
			Row row = rowit.next();
			Iterator<Cell> cellit = row.iterator();
			while(cellit.hasNext())
			{
				Cell cell = cellit.next();
				tabla[fila][columna] = cell.toString();
				columna += 1;
			}
			columna = 0;
			fila += 1;
		}
		File fout = new File("C:\\Users\\Jorge Zamora\\Desktop\\tablaGramatica.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		 
		for (int i = 0; i < tabla.length; i++) {
			String linea = "";
			for(int j = 0; j < tabla[i].length;j++)
			{
				linea += tabla[i][j] + "\\";
			}
			bw.write(linea);
			bw.newLine();
		}
	 
		bw.close();*/
		File file1 = new File(System.getProperty("user.dir") + "\\tablaGramatica.txt"); 

		BufferedReader br = new BufferedReader(new FileReader(file1)); 

		String st; 
		int indice = 0;
		while ((st = br.readLine()) != null) {

			String [] linea = st.split("\\\\");
			for(int u = 0; u < linea.length;u++)
			{
				tabla[indice][u] = linea[u];
			}
			indice += 1;
		} 
		//imprime las tablas
		/*for(int i = 0; i < tabla.length;i++)
		{
			for(int j = 0; j < tabla[i].length;j++)
			{
				System.out.print(tabla[i][j]);
			}
			System.out.println();
		}*/
		izquierda.add("$");
		izquierda.add("Inicio");
		this.derecha.add("$");
	}
	public int [] probarCadena()
	{
		int [] devuelve = new int [2];
		devuelve[0] = -1;
		devuelve[1] = -1;
		boolean probando =true;
		int linea = 0;
		int indice = 0;
		int x,y;
		boolean cambio = true;
		while(probando) {
			if(indice == derecha.size()-1 && linea < lineasDeTokens.size())
			{ 
				while(cambio)
				{
					linea += 1;
					if(linea >= lineasDeTokens.size())
						break;
					if(!lineasDeTokens.get(linea).isEmpty()) {
				      derecha = lineasDeTokens.get(linea);
				      derecha.add("$");
				      indice = 0;
				      cambio = false;
					}
				}
				cambio = true;
			}
			//imprime el arbol
			/*for(int n = 0; n < izquierda.size(); n++)
			{
				System.out.print(izquierda.get(n));
			}
			System.out.print("---");
			for(int n = 0; n < derecha.size(); n++)
			{
				System.out.print(derecha.get(n));
			}*/
		
			if(!(derecha.get(indice).equals("$") && izquierda.get(izquierda.size()-1).equals("$")))
			{
				if(izquierda.get(izquierda.size()-1).equals(derecha.get(indice)))
				{
					izquierda.remove(izquierda.size()-1);
					indice += 1;
				}
				else
				{
					x = this.getIndexY(izquierda.get(izquierda.size()-1));
					y = this.getIndexX(derecha.get(indice));
					if(x == -1 || y == -1 || tabla[x][y].equals("~") )
					{
						System.out.print("ERROR");
						devuelve[0] = linea;
						devuelve[1] = indice;
						return devuelve;
					}
					izquierda.remove(izquierda.size()-1);
					if(!this.filtrar(tabla[x][y]).equals("#"))
					{
						this.cambiarIzquierda(tabla[x][y]);
					}
				}
			}
			else
			{
				probando = false;
			}
		}		
		System.out.print("COMPILADO CORRECTAMENTE");
		return devuelve;
	}
	public String filtrar(String interseccion)
	{ 

		int flecha = interseccion.indexOf("->");
		if(flecha!= -1)
			return interseccion = interseccion.substring(flecha + 3, interseccion.length());
		return "~";
	}
	public void cambiarIzquierda(String interseccion)
	{

		int flecha = interseccion.indexOf("->");
		if(flecha!= -1)
			interseccion = interseccion.substring(flecha + 3, interseccion.length());
		String [] regla = interseccion.split("\\ ");
		for(int i = regla.length -1; i  >= 0;i--)
		{ 
			if(!regla[i].isEmpty())
			this.izquierda.add(regla[i]);
		}
	}
	public int getIndexX(String elemento)
	{
		for(int i = 0; i < this.tabla[0].length; i++)
		{
			if(this.tabla[0][i].equals(elemento))
		   return i;
		}
		  return -1;
	}
	public int getIndexY(String elemento)
	{
		for(int i = 0; i < this.tabla.length; i++)
		{
			if(this.tabla[i][0].equals(elemento))
		   return i;
		}
		return -1;
	}
	public void getPrimeros(String[] regla)
	{
		ArrayList<String> first = new ArrayList<String>();
		first.add(regla[0]);
		String [] reglas;
		for(int i = 1; i < regla.length; i++)
		{
			reglas = regla[i].split("\\ ");
			for(int j = 0; j < reglas.length;j++)
			{
				if(this.terminales.contains(reglas[j]));
				first.add(reglas[j]);
			}
		}
	}
	public String[] getRegla (String cabeza)
	{
		for(int i = 0; i < this.reglas.size();i++)
		{
			if(this.reglas.get(i)[0].equals(cabeza))
				return reglas.get(i);
			
		}
			return null;
	}

}







