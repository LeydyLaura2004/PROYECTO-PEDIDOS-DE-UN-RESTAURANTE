package modelo;

public class DetallePedido {
    private ItemMenu item; 
    private int cantidad;

    public DetallePedido(ItemMenu item, int cantidad) {
        this.item = item;
        this.cantidad = cantidad;
    }

    public double getSubtotal() { return item.getPrecio() * cantidad; }
    
    public String getDetalleTexto() {
        return cantidad + " x " + item.getNombre() + " = Bs " + getSubtotal();
    }

    // --- AGREGA ESTOS 3 MÉTODOS PARA QUE LA VENTANA FUNCIONE ---
    
    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() {
        return getSubtotal(); // Llama a tu método existente
    }

    public String getNombre() {
        return item.getNombre(); // Accede al nombre del objeto item
    }
}