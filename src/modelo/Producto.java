package modelo;

public abstract class Producto implements ItemMenu {
    protected String nombre;
    protected double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() { return nombre; }
    
    @Override
    public double getPrecio() { return precio; }
}