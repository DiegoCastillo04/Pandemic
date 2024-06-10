import java.awt.Color;
import java.util.ArrayList;

public class Ciudades {

    private String nombreciudad;
    private int[] coords = new int[2];
    private int virus;
    private ArrayList<String> colindantes = new ArrayList<>();
    private int infeccion;
    private Color colorOriginal;
	private int vacunaAsociada;
    
    public Ciudades(String nombreciudad, int[] coords, int virus, ArrayList<String> colindantes) {
        super();
        this.nombreciudad = nombreciudad;
        this.coords = coords;
        this.virus = virus;
        this.colindantes = colindantes;
    }

    public String getNombreciudad() {
        return nombreciudad;
    }

    public void setNombreciudad(String nombreciudad) {
        this.nombreciudad = nombreciudad;
    }

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public int getVirus() {
        return virus;
    }

    public void setVirus(int virus) {
        this.virus = virus;
    }

    public ArrayList<String> getColindantes() {
        return colindantes;
    }

    public void setColindantes(ArrayList<String> colindantes) {
        this.colindantes = colindantes;
    }

    public int getInfeccion() {
        return infeccion;
    }

    public void setInfeccion(int infeccion) {
        this.infeccion = infeccion;
    }

    public double pitagoras(Ciudades colindante) {
        // Calcular la diferencia en las coordenadas X y Y
        double diferenciaX = coords[0] - colindante.coords[0];
        double diferenciaY = coords[0] - colindante.coords[1];

        // Aplicar el teorema de Pitágoras
        double distancia = Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));

        distancia = Math.round(distancia * 100.0) / 100.0;

        return distancia;
    }

    public void aumentarinfeccion(int numero) {
    	this.infeccion += numero;
        // Asegurarse de que la infección no supere el límite de 3
        if (this.infeccion > 3) {
            this.infeccion = 3;
        }
    }

    public void disminuirInfeccion(int numero) {
    	this.infeccion -= numero;
        if (this.infeccion < 0) {
            this.infeccion = 0; // Asegurar que la infección no sea negativa
        }
    }

    public void propagarInfeccion(ArrayList<Ciudades> ciudadesArraylist) {
        for (String colindanteNombre : colindantes) {
            for (Ciudades ciudad : ciudadesArraylist) {
                if (ciudad.getNombreciudad().equals(colindanteNombre)) {
                    ciudad.aumentarinfeccion(0); // Aumentar la infección en la ciudad colindante
                    break; // Una vez que se ha encontrado la ciudad colindante, salimos del bucle
                }
            }
        }
}
    

    // Constructor y otros métodos

    public Color getColorOriginal() {
        return colorOriginal;
    }

    public void setColorOriginal(Color colorOriginal) {
        this.colorOriginal = colorOriginal;
    }
    public int getVacunaAsociada() {
        return vacunaAsociada;
    }

    public void setVacunaAsociada(int vacunaAsociada) {
        this.vacunaAsociada = vacunaAsociada;
    }



}
