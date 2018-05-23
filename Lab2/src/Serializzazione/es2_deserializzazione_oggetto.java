package Serializzazione;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class es2_deserializzazione_oggetto {
	
	public static void main (String args[])   {
		
		//Person persona = new Person(nome, cognome, eta);
		
		FileInputStream in = null;
		ObjectInputStream inputStream = null;
		
		try {			
			in = new FileInputStream("src/output/output.txt");
			inputStream = new ObjectInputStream(in);			
			Person persona = (Person)inputStream.readObject();	
			System.out.println("Nome : " + persona.getNome());
			System.out.println("Cognome : " + persona.getCognome());
			System.out.println("Età : " + persona.getEta());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
