import java.util.ArrayList;

public class datosPartida {


	public ArrayList<Ciudades> ciudades = new ArrayList<>();
	private ArrayList<Virus> virus = new ArrayList<>();
	private ArrayList<Vacunas> vacunas = new ArrayList<>();
	private int brotes;
	private int rondas;
	private float pDesarrollo;
	private int acciones;
	 
	 public datosPartida(ArrayList<Ciudades> ciudades, ArrayList<Virus> virus, ArrayList<Vacunas> vacunas, int brotes,
				int rondas, float pDesarrollo, int acciones) {
			super();
			this.ciudades = ciudades;
			this.virus = virus;
			this.vacunas = vacunas;
			this.brotes = brotes;
			this.rondas = rondas;
			this.pDesarrollo = pDesarrollo;
			this.acciones = acciones;
		}
	 
	public ArrayList<Ciudades> getCiudades() {
		return ciudades;
	}
	public void setCiudades(ArrayList<Ciudades> ciudades) {
		this.ciudades = ciudades;
	}
	public ArrayList<Virus> getVirus() {
		return virus;
	}
	public void setVirus(ArrayList<Virus> virus) {
		this.virus = virus;
	}
	public ArrayList<Vacunas> getVacunas() {
		return vacunas;
	}
	public void setVacunas(ArrayList<Vacunas> vacunas) {
		this.vacunas = vacunas;
	}
	public int getBrotes() {
		return brotes;
	}
	public void setBrotes(int brotes) {
		this.brotes = brotes;
	}
	public int getRondas() {
		return rondas;
	}
	public void setRondas(int rondas) {
		this.rondas = rondas;
	}
	public float getpDesarrollo() {
		return pDesarrollo;
	}
	public void setpDesarrollo(float pDesarrollo) {
		this.pDesarrollo = pDesarrollo;
	}
	public int getAcciones() {
		return acciones;
	}
	public void setAcciones(int acciones) {
		this.acciones = acciones;
	}
	 
	public void modificarCiudad(String nCiudad, int modificacion) {
		
		
	} 
	public void modificarVacuna(String nvacuna, float modificacion) {
		
		
	} 
	
	public void cargarDatos() {
	
		
	} 
	
	 
}
