package Serializzazione;

import java.io.*;
import java.util.Scanner;

public class PersonSerialization {
	public static void main (String args[])   {
		String nome;
		String cognome;
		int eta;
		
		Scanner scanner = new Scanner(System.in);
		Person persona;
		
		System.out.println("inserire il nome");
		nome = scanner.nextLine();
		System.out.println("inserire il cognome");
		cognome = scanner.nextLine();
		System.out.println("inserire l'eta");
		eta = scanner.nextInt();
		persona = new Person(nome, cognome, eta);
		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			File theDir = new File("src/output");
			if(!theDir.exists()) 
				theDir.mkdirs();
			fos = new FileOutputStream("src/output/output.txt");
			out = new ObjectOutputStream(fos);
			out.writeObject(persona);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
