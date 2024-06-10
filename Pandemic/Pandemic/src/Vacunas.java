public class Vacunas {

    private String nombre;
    private String color;
    private float porcentage;

    public Vacunas(String nombre, String color, float porcentage) {
        super();
        this.nombre = nombre;
        this.color = color;
        this.porcentage = porcentage;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(float porcentage) {
        this.porcentage = porcentage;
    }

    public void desarrollarVacuna(int numero) {
        this.porcentage += numero;
    }
    
}
