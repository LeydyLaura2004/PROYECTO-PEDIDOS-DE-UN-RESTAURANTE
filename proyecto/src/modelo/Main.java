package modelo;

public class Main {
    public static void main(String[] args) {
        // 1. Creamos una Mesa (Agregación)
        Mesa mesa1 = new Mesa(5);
        
        // 2. Creamos un Pedido para esa mesa
        Pedido miPedido = new Pedido(mesa1);
        
        // 3. Creamos productos (Polimorfismo: Tratamos a Platos y Bebidas como ItemMenu)
        ItemMenu pizza = new Plato("Pizza Familiar", 50.0);
        ItemMenu coca = new Bebida("Coca Cola", 10.0);
        
        // 4. Agregamos al pedido
        miPedido.agregarItem(pizza, 1); // 1 Pizza
        miPedido.agregarItem(coca, 2);  // 2 Coca Colas
        
        // 5. Probamos el resultado en consola
        System.out.println("--- Resumen del Pedido (Mesa " + miPedido.getNumeroMesa() + ") ---");
        
        // Aquí iteramos sobre los detalles
        for (DetallePedido d : miPedido.getDetalles()) {
            System.out.println(d.getDetalleTexto());
        }
        
        System.out.println("------------------------------------");
        System.out.println("Total a pagar: bs" + miPedido.calcularTotal());
    }
}