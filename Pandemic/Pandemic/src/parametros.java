import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;
import java.util.Scanner;

public class parametros {
    private static final String PARAMS_FILE = "parametros.xml";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Bucle principal del programa
        while (true) {
            // Mostrar el menú
            System.out.println("-- MENU --");
            System.out.println("1. Ver los parametros actuales");
            System.out.println("2. Cambiar los parametros");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            // Leer la opción seleccionada por el usuario
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el entero

            // Procesar la opción seleccionada
            switch (option) {
                case 1:
                	LeerYPrintear(); // Leer y mostrar datos del archivo XML
                    break;
                case 2:
                	ActualizarParametros(); // Actualizar datos en el archivo XML
                    break;
                case 3:
                    System.out.println("Ya estas fuera"); // Salir del programa
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Elige una de las opciones.");
            }
        }
    }

    // Método para leer y mostrar datos del archivo XML
    private static void LeerYPrintear() {
        try {
            File inputFile = new File(PARAMS_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Mostrar los datos actuales del archivo XML
            System.out.println("--- DATOS ACTUALES ---");
            NodeList nodeList = doc.getElementsByTagName("parametros");
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                NodeList params = element.getChildNodes();
                for (int i = 0; i < params.getLength(); i++) {
                    Node paramNode = params.item(i);
                    if (paramNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element paramElement = (Element) paramNode;
                        String nombre = paramElement.getNodeName();
                        String valor = paramElement.getTextContent();
                        System.out.println(nombre + ": " + valor);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo " + PARAMS_FILE);
            e.printStackTrace();
        }
    }

    // Método para actualizar datos en el archivo XML
    private static void ActualizarParametros() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz del archivo XML
            Element rootElement = doc.createElement("parametros");
            doc.appendChild(rootElement);

            Scanner scanner = new Scanner(System.in);

            // Definir los nombres de los parámetros
            String[] paramNames = {"numCiudadesInfectadasInicio", "numCuidadesInfectadasRonda", "numEnfermedadesActivasDerrota", "numBrotesDerrota"};

            // Solicitar al usuario que ingrese nuevos valores para los parámetros
            for (String paramName : paramNames) {
                Element parametro = doc.createElement(paramName);
                System.out.print("Dime el nuevo valor para " + paramName + ": ");
                String nuevoValor = scanner.nextLine();
                parametro.appendChild(doc.createTextNode(nuevoValor));
                rootElement.appendChild(parametro);
            }

            // Escribir los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(PARAMS_FILE));
            transformer.transform(source, result);

            System.out.println("Los datos se han guardado");
        } catch (Exception e) {
            System.out.println("Error al guardar");
            e.printStackTrace();
        }
    }
}
