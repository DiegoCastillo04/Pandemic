import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class txtCiudades {
    
	public static ArrayList<String> frases = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    public static ArrayList<Ciudades> listaCiudades = new ArrayList<>();

    public static void main(String[] args) {
        String nombreFichero = "ciudades.txt";
        try {
            FileReader fileReader = new FileReader(nombreFichero);
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
        
        imprimirCiudadesConDistancias();
   
     		//Se utiliza para darle un nombre y la ruta al fichero
     		String nombreFicheroEscrito = "CiudadesRedactadas.txt";
     		
     		try {
     			//Se utiliza para crear el fichero con el nombre indicado en la variable nombreFichero
     			FileWriter fileWriter = new FileWriter(nombreFicheroEscrito, false);
     			//Se utiliza para escribir en el fichero creado por el FileWriter
     			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
     			Scanner scanner = new Scanner(System.in);
     			String frase = "";
     			 
     			
     				for (int i = 0; i < frases.size(); i++) {
     					frase = frases.get(i);
         				
         				//Escribimos en el fichero
         				bufferedWriter.write(frase);
         			
         				//A�adimos un \n en el fichero
         				bufferedWriter.newLine();
					}
     			
     	
     			//Aqu� cerramos todos los objetos
     			scanner.close();
     			bufferedWriter.close();
     			fileWriter.close();
     			System.out.println("El fichero ha sido modificado correctamente");
     		} catch (IOException e) {
     			System.out.println("Ha habido un error de escritura: " + e);
     		}
     		

     	}

  
    
    private static void imprimirCiudadesConDistancias() {
        int count = 1; // Para numerar las ciudades
        for (Ciudades ciudad : listaCiudades) {
                      
            for (String colindante : ciudad.getColindantes()) {
                Ciudades colindanteCiudad = obtenerCiudad(colindante);
                if (colindanteCiudad != null) {
                    double distancia = ciudad.pitagoras(colindanteCiudad);
                  
                }
            }
            count++;
        }
    }

    public static Ciudades obtenerCiudad(String nombreCiudad) {
        for (Ciudades ciudad : listaCiudades) {
            if (ciudad.getNombreciudad().equals(nombreCiudad)) {
                return ciudad;
            }
        }
        return null;
    }
    
    public static ArrayList<Ciudades> crearArrayCiudades() {
    	
    	main(null);
    	
    	
		return listaCiudades;
    	 	
		
	}
    
    
  
    
    
    
    
    
    
}
