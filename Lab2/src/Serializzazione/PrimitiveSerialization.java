package Serializzazione;

import java.io.*;

public class PrimitiveSerialization {
    public static void main (String args[])   {
        int integer = 10;
        boolean bool = true;
        String string = "prova di serializzazione di una stringa";

        FileOutputStream fos1 = null, fos2 = null, fos3 = null;
        ObjectOutputStream out1 = null, out2 = null, out3 = null;
        try {
        	File theDir = new File("src/output");
			if(!theDir.exists()) 
				theDir.mkdirs();
            fos1 = new FileOutputStream("src/output/o1.txt");
            out1 = new ObjectOutputStream(fos1);
            out1.writeObject(integer);
            out1.close();

            fos2 = new FileOutputStream("src/output/o2.txt");
            out2 = new ObjectOutputStream(fos2);
            out2.writeObject(bool);
            out2.close();

            fos3 = new FileOutputStream("src/output/o3.txt");
            out3 = new ObjectOutputStream(fos3);
            out3.writeObject(string);
            out3.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
