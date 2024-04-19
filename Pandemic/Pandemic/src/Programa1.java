import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Programa1 {
	//Variables :)
	
	public static ArrayList<Ciudades> listaCiudades= new ArrayList<>();
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
				Ciudades c = crearObjCiudades(linea);
				listaCiudades.add(c);
				
				
			}
		} while (linea != null);

		bufferedReader.close();
		fileReader.close();

	} catch (IOException e) {
		System.out.println("Ha habido un error al intentar abrir el fichero" + e);
	}


	
	
	
	}

public static Ciudades crearObjCiudades(String datos) {
		
		//Split :P
		String[] frasePartida = datos.split(";");
		
		//Partir las comas :D
		String[] separarCoords = frasePartida[2].split(",");
		int[] coords = new int[2];
		ArrayList<String> Colindantes = new ArrayList<>();
		String[] colindantes = frasePartida[3].split(",");
		
		for (int i = 0; i < colindantes.length; i++) {
			Colindantes.add(colindantes[i]);
		}		
		
		//Asignar valores :V
		String nombreciudad = frasePartida[0];
		coords[0] = Integer.parseInt(separarCoords[0]);
		coords[1] = Integer.parseInt(separarCoords[1]);
		
		return new Ciudades(frasePartida[0], coords, Integer.parseInt(frasePartida[1]), Colindantes);
		 
}
}


