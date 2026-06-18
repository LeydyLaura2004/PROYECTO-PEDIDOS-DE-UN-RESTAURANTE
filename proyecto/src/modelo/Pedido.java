package modelo;
import java.util.ArrayList;

public class Pedido {
    private Mesa mesa; // Agregación
    private ArrayList<DetallePedido> listaDetalles; // Composición

    public Pedido(Mesa mesa) {
        this.mesa = mesa;
        this.listaDetalles = new ArrayList<>();
    }

    public void agregarItem(ItemMenu item, int cantidad) {
        listaDetalles.add(new DetallePedido(item, cantidad));
    }

    public double calcularTotal() {
        double total = 0;
        for (DetallePedido d : listaDetalles) {
            total += d.getSubtotal();
        }
        return total;
    }

    // --- MÉTODOS DE ACCESO (Getters) ---
    
    public int getNumeroMesa() {
        return mesa.getNumero();
    }

    public ArrayList<DetallePedido> getDetalles() {
        return listaDetalles;
    }
}