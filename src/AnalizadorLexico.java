import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalizadorLexico {

	
	public ArrayList<String> lineas ;
	 
	public ArrayList<String> lineasDeTokens;
	public AnalizadorLexico() throws IOException {
		
		lineasDeTokens = new ArrayList<String>();
		lineas = new ArrayList<String> ();
		File file = new File("C:\\Users\\joaquin\\Desktop\\test.txt"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st; 
		  while ((st = br.readLine()) != null) {
			  
			  lineas.add(st);
			  System.out.println(st); 
		  } 
		  
		  

		  
		  for(int i =0;i<lineas.size();i++) {
			  
			  String [] tokens = this.getString(lineas.get(i));
			  
			  String linea="";
			  
			  for(int j=0;j<tokens.length;j++) {
				  
				  linea +=this.getTokenType(tokens[j]).trim();
				  System.out.println(tokens[j]+"-->"+this.getTokenType(tokens[j].trim())+" ");
				 
				  
			  }
			  lineasDeTokens.add(linea);

			  
		  }
		  
		  
}
	
		
	public String [] getString(String linea){
		
		String [] tokens = linea.split("(?=\\>)|(?<=\\>)|(?=\\<)|(?<=\\<)|\\ |"
				+ "(?<=\\+)|(?=\\+)|(?<=\\-)|(?=\\-)|(?<=\\*)|(?=\\*)|(?<=\\/)|(?=\\/)|(?<=\\=)|(?=\\=)|"
				+ "(?<=\\%)|(?=\\%)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=\\,)|(?=\\,)|(?<=\\;)|(?=\\;)|(?<=\\&)"
				+ "|(?=\\&)|(?<=\\|)|(?=\\|)");
		
			
		
		ArrayList<String> tokens2= new ArrayList<String>();
		
		
		return tokens;		
		
	}
	
	
	public String getTokenType(String token) {
		
		if(token.matches("[0-9]+")) {
			return "NUMBER";
		}
		else if(token.matches("number|mission|assumingThat|asLongAs|assumingItFailed|againAssumingThat|legit|chain|chainLink|decimalNumber")) {
			return "RESERVADA";
		}
		else if(token.matches("true|false")) {
			return "BOOLEANO";
		}
		else if(token.matches("[\\[a-zA-Z0-9]+")) {
			return "NOMBRE";
		}
		else if(token.matches("[\\_\\$][a-zA-Z0-9]+")) {
			return "IDENTIFICADOR";
		}
		else if(token.matches("\\\"[a-z0-9A-Z]*\\\"")) {
			return "STRING";
		}
		else if(token.matches("\\\'[a-z0-9A-Z]*\\\'")) {
			return "CHAR";
		}
		else if(token.matches("\\+|\\-|\\*|\\/|\\%|\\<|\\>|\\=")){
			return "OPERADOR";
		}
		else if(token.matches("\\(|\\)|\\,")) {
			return "DELIMITADOR";
		}
		else if(token.matches("\\;")) {
			return "FIN DE LINEA";
		}
		else if(token.matches("\\||\\&|\\!")) {
			return "OPERB";
		} 
		else 
			return "NOTHING";
		
	}
	
	public ArrayList<String> getLineasDeTokens(){
		return lineasDeTokens;
	}
	
}
