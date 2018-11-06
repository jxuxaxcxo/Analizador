import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalizadorLexico {


	public ArrayList<String> lineas ;
	public boolean seguir = true;
	public ArrayList<ArrayList<String>> lineasDeTokens, codigo;
	public AnalizadorLexico() throws IOException {

		codigo = new ArrayList<ArrayList<String>>();
		lineasDeTokens = new ArrayList<ArrayList<String>>();
		lineas = new ArrayList<String> ();
		File file = new File("C:\\Users\\joaquin\\Desktop\\prueba.txt"); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String st; 
		while ((st = br.readLine()) != null) {

			lineas.add(st);
			System.out.println(st); 
		} 
		br.close();
		for(int i =0;i<lineas.size();i++) {

			String [] tokens = this.getString(this.filtrarSC(lineas.get(i)));
			ArrayList<String> linea = new ArrayList<String>();
			ArrayList<String> lineaCodigo = new ArrayList<String>();
			for(int j=0;j<tokens.length;j++) {
				if(!tokens[j].isEmpty() )
				{
					if(this.getTokenType(tokens[j]).trim().equals("NOTHING"))
					{
					System.out.println("Error lexico en la fila " + i + " cerca de >" + tokens[j] + "<");
					seguir = false;
					}
				linea.add(this.getTokenType(tokens[j]).trim());
				//System.out.println(tokens[j]+"-->"+this.getTokenType(tokens[j].trim())+" ");
				}
				lineaCodigo.add(tokens[j]);
			}
			codigo.add(lineaCodigo);
			//if(!linea.isEmpty())
			lineasDeTokens.add(linea);
		}
		//imprime las lineas de tokens
		/*for(int u = 0; u < lineasDeTokens.size();u++)
		{
			for(int x = 0; x < lineasDeTokens.get(u).size();x++)
			{
				System.out.print(lineasDeTokens.get(u).get(x));
			}
			System.out.println();
		}*/
	}
	public String filtrarSC(String linea)
	{
		int comienzo = -1;
		int i = 0;
		while(i < linea.length())
		{
			if(linea.charAt(i)=='"')
			{
				if(comienzo == -1)
					comienzo = i;
				else
				{
					linea = linea.substring(0, comienzo) + "cadena" + linea.substring(i+1, linea.length());
					i = comienzo + 5;
					comienzo = -1;
				}			
			}	
			i +=1;
		}
		int arroba = linea.indexOf("@");
		if(arroba != -1)
			linea = linea.substring(0, arroba);
		return linea;

	}
	public String [] getString(String linea){

		String [] tokens = linea.split("(?=\\=\\=)|(?<=\\=\\=)|(?=\\>)|(?<=\\>)|(?=\\<)|(?<=\\<)|\\ |\\	|"
				+ "(?<=\\+)|(?=\\+)|(?<=\\-)|(?=\\-)|(?<=\\*)|(?=\\*)|(?<=\\/)|(?=\\/)|(?<=\\=)|(?=\\=)|"
				+ "(?<=\\%)|(?=\\%)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=\\,)|(?=\\,)|(?<=\\;)|(?=\\;)|(?<=\\&)"
				+ "|(?=\\&)|(?<=\\|)|(?=\\|)|(?=\\{)|(?<=\\{)|(?=\\})|(?<=\\})");
		return tokens;		

	}
	public String getTokenType(String token) {

		if(token.matches("[0-9]+")) {
			return "numero";
		}
		else if(token.matches("is|not|global|return|yourCodeRunsHere|number|mission|assumingThat|asLongAs|assumingItFailed|againAssumingThat|legit|chain|chainLink|decimalNumber|cadena")) {
				return token;
		}
		else if(token.matches("true|false")) {
			return "boolean";
		}
		else if(token.matches("C[a-zA-Z0-9]+")) {
			return "NombreC";
		}
		else if(token.matches("B[a-zA-Z0-9]+")) {
			return "NombreB";
		}
		else if(token.matches("N[a-zA-Z0-9]+")) {
			return "NombreN";
		}
		else if(token.matches("S[a-zA-Z0-9]+")) {
			return "NombreS";
		}
		else if(token.matches("\\_[a-zA-Z0-9]+")) {
			return "id_" + token;
		}
		else if(token.matches("\\\'[a-z0-9A-Z]*\\\'")) {
			return "caracter";
		}
		else if(token.matches("\\+|\\-|\\*|\\/|\\%|\\<|\\>|\\=")){
			return token;
		}
		else if(token.matches("\\(|\\)|\\,|\\{|\\}")) {
			return token;
		}
		else if(token.matches("\\;")) {

			return token;
		}
		else if(token.matches("\\||\\&|\\!")) {
			return token;
		} 
		else 
			return "NOTHING";

	}

}
