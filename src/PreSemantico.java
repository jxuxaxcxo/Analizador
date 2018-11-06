import java.util.ArrayList;


public class PreSemantico {

	
	ArrayList<ArrayList<String>> lineasDeTokens;
	
	ArrayList<ArrayList<String>> tablaSimbolos;
	
	ArrayList<ArrayList<String>> nuevasLineasDeTokens;

	ArrayList<String> tipos;
	
	public PreSemantico(ArrayList<ArrayList<String>> lineasDeTokens) {
		
		this.lineasDeTokens = lineasDeTokens;
		
		tablaSimbolos = new ArrayList<ArrayList<String>>();
		
		nuevasLineasDeTokens = lineasDeTokens;

		tipos = new ArrayList<String>();
		tipos.add("number");
		tipos.add("chain");
		tipos.add("chainLink");
		tipos.add("legit");
		
		
	}
	
	
	
	public void crearTablaDeSimbolos() {
		
		
		for(int i = 0; i < lineasDeTokens.size();i++)
		{
			for(int j = 0; j < lineasDeTokens.get(i).size();j++)
			{
				
				if(lineasDeTokens.get(i).get(j).equals("mission")) {
					this.resetTablaSimbolos();
				}
				
					
				if(lineasDeTokens.get(i).get(j).contains("id_")&&
							!this.enTabla(lineasDeTokens.get(i).get(j))&&
							tipos.contains(lineasDeTokens.get(i).get(j-1))) {
						
					
						ArrayList<String> actual = new ArrayList<String>();
						actual.add(lineasDeTokens.get(i).get(j));
						actual.add(lineasDeTokens.get(i).get(j-1));
						
						
						if(j>1&&lineasDeTokens.get(i).get(j-2).equals("global")) {
							actual.add("global");
							nuevasLineasDeTokens.get(i).set(j,this.getNewName(lineasDeTokens.get(i).get(j-1)));
						}
						else {
							actual.add("local");
							nuevasLineasDeTokens.get(i).set(j,this.getNewName(lineasDeTokens.get(i).get(j-1)));
						}
						
						tablaSimbolos.add(actual);

						
				}
					
				//En caso de que ya haya sido ingresado a la tabla	
				else if(lineasDeTokens.get(i).get(j).contains("id_")&&
						this.enTabla(lineasDeTokens.get(i).get(j))) {
					
						nuevasLineasDeTokens.get(i).set(j,this.getTipoEnTabla(lineasDeTokens.get(i).get(j)));
					
				}
					
				
			}
		}
		
	}
	
	public void resetTablaSimbolos() {
		
		int i=0;
		while(i<tablaSimbolos.size()) {
			if(tablaSimbolos.get(i).get(2).equals("local")) {
				tablaSimbolos.remove(i);
				i--;
			}
			i++;
			
		}
		
		this.imprimirTablaSimbolos();
		
	}
	
	public void imprimirTablaSimbolos() {
		
		
		for(int i = 0; i<tablaSimbolos.size();i++) {
			
			//System.out.println("####"+tablaSimbolos.get(i).get(0)+"..."+tablaSimbolos.get(i).get(1)+"..."+tablaSimbolos.get(i).get(2));
				
		}
			
		
	}
	
	public String getTipoEnTabla(String palabra) {
		
		for(int i =0;i<tablaSimbolos.size();i++) {
			
			if(tablaSimbolos.get(i).get(0).equals(palabra))
				return this.getNewName(tablaSimbolos.get(i).get(1));
		}
		
		return "";
		
		
	}
	
	public boolean enTabla(String palabra) {
		
		boolean result = false;
		for(int i =0;i<tablaSimbolos.size();i++) {
			if(palabra.equals(tablaSimbolos.get(i).get(0)))
				result=true;
			
		}
		
		return result;
	}
	
	public int getIndexOnTabla(String palabra) {
		
		for(int i=0;i<tablaSimbolos.size();i++) {
			if(tablaSimbolos.get(i).get(0).equals(palabra))
				return i;
		}
		
		return -1;
		
	}
	
	public String getNewName(String tipo) {
		 
				
		if(tipo.equals("number")) {
			return "identificadorN";
		}
		else if(tipo.equals("chain")) {
			return "identificadorS";
		}
		else if(tipo.equals("chainLink")) {
			return "identificadorC";
		}
		else if(tipo.equals("legit")) {
			return "identificadorB";
		}
		
		else 
			return null;
		
	}
	
	public void imprimirNuevasLineas() {
		
		for(int u = 0; u < nuevasLineasDeTokens.size();u++)
		{
			for(int x = 0; x < nuevasLineasDeTokens.get(u).size();x++)
			{
				System.out.print(nuevasLineasDeTokens.get(u).get(x)+" ");
			}
			System.out.println();
		}
	}
}