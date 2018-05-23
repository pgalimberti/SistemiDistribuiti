package Serializzazione;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class es2_deserializzazione_primitive {
	
	public static void main (String args[])   {
		
		FileInputStream in = null, in2 = null, in3 = null;
		ObjectInputStream inputStream = null, inputStream2 = null, inputStream3 = null;
		try {
			in = new FileInputStream("src/output/o1.txt");
			inputStream = new ObjectInputStream(in);
			int integer = (int)inputStream.readObject();			
			System.out.println("Int letto : " + integer);
			
			in2 = new FileInputStream("src/output/o2.txt");
			inputStream2 = new ObjectInputStream(in2);
			boolean booleano = (boolean)inputStream2.readObject();			
			System.out.println("Boolean letto : " + booleano);
			
			in3 = new FileInputStream("src/output/o3.txt");
			inputStream3 = new ObjectInputStream(in3);
			String stringa = (String)inputStream3.readObject();
			System.out.println("String letto : " + stringa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
