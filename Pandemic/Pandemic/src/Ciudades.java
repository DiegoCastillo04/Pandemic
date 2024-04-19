import java.util.ArrayList;

public class Ciudades {

	private String nombreciudad;
	private int[] coords = new int[2];
	private int virus;
	private ArrayList<String> colindantes = new ArrayList<>();

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


    public double pitagoras(Ciudades colindante) {
        // Calcular la diferencia en las coordenadas X y Y
        double diferenciaX = coords[0] - colindante.coords[0];
        double diferenciaY = coords[0] - colindante.coords[1];

        // Aplicar el teorema de Pitágoras
        double distancia = Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
        
         distancia = Math.round(distancia * 100.0) / 100.0;
        // Imprimir la distancia
        

        return distancia;
    }
	public void aumentarinfeccion(int numero) {
		
		this.virus += numero;
		
	}
	public void disminuirInfeccion(int numero) {
		
		this.virus -= numero;
		
	}
	public void propagarInfeccion(int numero) {
		
		
		
	}
}

