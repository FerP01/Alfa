package ventana;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CogerPalabras {
	public CogerPalabras(String archivoAbierto) throws IOException{
		
		File TextFile = new File(archivoAbierto); 
		Scanner sc = new Scanner(TextFile);
		String resultado="";
		
	while(sc.hasNextLine()){
		String linea = sc.nextLine();
		resultado += escanearLinea(linea) + "\n";
	}
	sc.close();
	
	
		
		FileWriter TextOut = new FileWriter(TextFile, true);
		TextOut.write(resultado);
		TextOut.close();
	}
	
	public String escanearLinea(String linea){
		Scanner sc = new Scanner(linea);
		sc.useDelimiter(".");
		String num = sc.next();
		String pal = sc.next();
		sc.close();
		return pal;
	}
	
	public static void main(String args[]) throws IOException{
		CogerPalabras p = new CogerPalabras("C:\\Users\\Fer\\Desktop\\PruebasJava\\500PalabrasEspañol.txt");
	}
}
