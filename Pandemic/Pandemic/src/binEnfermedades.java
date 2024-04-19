import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

public class binEnfermedades {

	public static ArrayList<Ciudades> listaCiudades = new ArrayList<>();
	public static ArrayList<String> frases = new ArrayList<>();
	public static void main(String[] args) {
		
		String nombreFichero1 = "ciudades.txt";
		
        try {
            FileReader fileReader = new FileReader(nombreFichero1);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea = null;
            do {
                linea = bufferedReader.readLine();
                if (linea != null) {
                    listaCiudades.add(Programa1.crearObjCiudades(linea));   
                }
            } while (linea != null);

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Ha habido un error al intentar abrir el fichero" + e);
        }
        
        for (int i = 0; i < listaCiudades.size(); i++) {
			System.out.println(listaCiudades.get(i).getNombreciudad() + " - " + enfermedad(listaCiudades.get(i).getVirus()));
			frases.add(listaCiudades.get(i).getNombreciudad() + " - " + enfermedad(listaCiudades.get(i).getVirus()));
		}
        
        
	 
        
		
		//Ponemos el nombre del fichero que vamos a escribir. Si no existe lo crea.
				String nombreFichero = "ciudades-enfermedad.bin";
				
				try {
					//Lo utilizamos para crear el fichero a partir del nombre
					FileOutputStream fileOutputStream = new FileOutputStream(nombreFichero);
					//Este objeto traduce todos los datos de Java a binario
					DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
					
					
					//El writeUTF se utiliza para escribir Strings
					for (int i = 0; i < frases.size(); i++) {
						dataOutputStream.writeUTF(frases.get(i));
					}
					

					dataOutputStream.close();
					fileOutputStream.close();
				//Capturamos el error cuando el fichero indicado no exista
				} catch (FileNotFoundException e1) {
					System.out.println("Fichero no encontrado " + e1);
				//Capturamos el error si ha habido un error de escrtura
				} catch (IOException e) {
					System.out.println("Error en la escritura del fichero " + e);
				//Capturamos el error si en el scanner nos env�an una letra en vez de un n�mero en el nextInt()
				} catch (InputMismatchException e2) {
					System.out.println("Ha habido al leer los datos del usuario");
				//Capturamos cualquier otra excepci�n
				} catch (Exception e3) {
					System.out.println("Ha habido un error no controlado " + e3);
				}  
      
	 }
	
	 public static String enfermedad(int numero) {
	        switch(numero) {
	            case 0:
	                return "alfa";
	            case 1:
	                return "beta";
	            case 2:
	                return "gamma";
	            case 3:
	                return "delta";
	            default:
	                return "Número inválido";
	        }
	    }
}