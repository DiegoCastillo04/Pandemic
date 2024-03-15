import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Programa1 {
	//Variables :)
	private static String nombreciudad = "";
	private static int[] coords = new int[3];
	private static Scanner scan = new Scanner(System.in);
public static void main(String[] args) {

	String nombreFichero = "ciudades.txt";

	//Este es tu codigo profe, Gracias <3
	try {
		// Creamos un objeto de tipo FileReader para abrir un fichero de lectura
		FileReader fileReader = new FileReader(nombreFichero);
		// Utilizamos el Buffered para recibir lo que hay en el fichero y transformarlo
		// en c�digo Java
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String linea = null;
		do {
			// Leemos el contenido del fichero
			linea = bufferedReader.readLine();

			// Si no ha acabado de leer el fichero, printamos el valor de l�nea
			if (linea != null) {
				separarDatos(linea);
				
			}
		} while (linea != null);

		bufferedReader.close();
		fileReader.close();

	} catch (IOException e) {
		System.out.println("Ha habido un error al intentar abrir el fichero" + e);
	}


	
	
	
	}

private static void separarDatos(String datos) {
		
		//Split :P
		String[] frasePartida = datos.split(";");
		
		//Partir las comas :D
		String[] separarCoords = frasePartida[2].split(",");
		
		//Asignar valores :V
		nombreciudad = frasePartida[0];
		coords[0] = Integer.parseInt(frasePartida[1]);
		coords[1] = Integer.parseInt(separarCoords[0]);
		coords[2] = Integer.parseInt(separarCoords[1]);
		
		 ArrayList<String> ciudades = new ArrayList<>(Arrays.asList(frasePartida[3].split(",")));
		 
		 System.out.println("Silco actua en " + nombreciudad + " con los numeros " + coords[0] + ", " + coords[1] + " y " + coords[2] + ", y cuyas ciudades colindantes son " + frasePartida[3] );
}
}


