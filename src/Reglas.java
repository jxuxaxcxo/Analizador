import java.util.ArrayList;

public class Reglas {
	
	
	ArrayList<String[]> rules;

	public Reglas() {
		
		
		rules= new ArrayList<String []>();
		
		
		
		String[] regla2 = new String [] {"FUNCION","NOMBREDELIMITADORPARAMETRODELIMITADOR"};
		String[] regla3 = new String [] {"PARAMETRO","PARAMETRO,PARAMETRO"};
		String[] regla4 = new String [] {"PARAMETRO","Q"};
		String[] regla5 = new String [] {"Q","IDENTIFICADOR"};
		String[] regla6 = new String [] {"Q","NUMBER"};
		
		String[] regla7 = new String [] {"BOOLEANO","DELIMITADORBOOLEANODELIMITADOR"};
		String[] regla8 = new String [] {"BOOLEANO","BOOLEANOOPERBBOLEANO"};
		String[] regla9 = new String [] {"BOOLEANO","BOOLEANOOPERBBOOLEANO"};
		//String[] regla10 = new String [] {"TODO","BOOLEANO"};

		

		
	//	rules.add(regla1);
		rules.add(regla2);
		rules.add(regla3);
		rules.add(regla4);
		rules.add(regla5);
		rules.add(regla6);
		
		rules.add(regla7);
		rules.add(regla8);
		rules.add(regla9);
		//rules.add(regla10);


	

//		rules.add(regla6);


		
		
	
	}
	
	public ArrayList<String[]> getRules() {
		return rules;
	}
	
	

}
