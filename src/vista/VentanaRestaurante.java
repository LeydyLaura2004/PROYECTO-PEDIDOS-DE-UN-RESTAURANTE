package vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import modelo.*;
import util.ManejadorArchivos;

public class VentanaRestaurante extends JFrame {
    
    private JTextField txtMesa, txtCantidad;
    private JComboBox<String> comboProductos;
    private JTextArea areaTicket;
    private JLabel lblTotal;
    private Pedido miPedido;

    public VentanaRestaurante() {
        setTitle("Sistema de Pedidos");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        // --- Formulario ---
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(new TitledBorder("Datos del Pedido"));
        
        txtMesa = new JTextField();
        comboProductos = new JComboBox<>(new String[]{"Sopa de Maní", "Lawa de Choclo", "Saice", "Silpancho", "Pique Macho", "Majadito", "Refresco"});
        txtCantidad = new JTextField();
        JButton btnAgregar = new JButton("Agregar al Pedido");

        // Colores y diseño
        btnAgregar.setBackground(new Color(144, 238, 144));
        btnAgregar.setOpaque(true); 

        panelForm.add(new JLabel(" Nro. Mesa:")); panelForm.add(txtMesa);
        panelForm.add(new JLabel(" Producto:")); panelForm.add(comboProductos);
        panelForm.add(new JLabel(" Cantidad:")); panelForm.add(txtCantidad);
        panelForm.add(new JLabel("")); 
        panelForm.add(btnAgregar);

        // --- Ticket ---
        areaTicket = new JTextArea();
        areaTicket.setEditable(false);
        areaTicket.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaTicket);
        scroll.setBorder(new TitledBorder("Resumen del Ticket"));

        // --- Panel Inferior ---
        JPanel panelSur = new JPanel(new BorderLayout(5, 5));
        lblTotal = new JLabel("Total: 0 Bs", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        JButton btnFinalizar = new JButton("Entregar Orden");
        
        // Colores y diseño
        btnFinalizar.setBackground(new Color(135, 206, 250));
        btnFinalizar.setOpaque(true);
        
        panelSur.add(lblTotal, BorderLayout.NORTH);
        panelSur.add(btnFinalizar, BorderLayout.SOUTH);

        mainPanel.add(panelForm, BorderLayout.WEST);
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(panelSur, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarItem());
        btnFinalizar.addActionListener(e -> finalizarOrden());
    }

    private void agregarItem() {
        try {
            int numMesa = Integer.parseInt(txtMesa.getText());
            if (miPedido == null || miPedido.getNumeroMesa() != numMesa) {
                miPedido = new Pedido(new Mesa(numMesa));
            }
            
            String nombre = comboProductos.getSelectedItem().toString();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            
            // Aquí llamamos al método que calcula el precio real
            miPedido.agregarItem(new Plato(nombre, obtenerPrecio(nombre)), cantidad);
            
            actualizarTicket();
            txtCantidad.setText("");
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Por favor verifica los datos"); 
        }
    }

    // Lógica para asignar precios
    private double obtenerPrecio(String nombre) {
        switch (nombre) {
            case "Sopa de Maní": return 15.0;
            case "Saice": return 25.0;
            case "Pique Macho": return 45.0;
            case "Refresco": return 5.0;
            default: return 10.0;
        }
    }

    private void actualizarTicket() {
        areaTicket.setText("--- RESUMEN MESA " + miPedido.getNumeroMesa() + " ---\n");
        areaTicket.append(String.format("%-15s %-5s %-8s\n", "Prod.", "Cant.", "Total"));
        areaTicket.append("------------------------------------------\n");
        for (DetallePedido d : miPedido.getDetalles()) {
            areaTicket.append(String.format("%-15s x%-4d %-8.2f Bs\n", d.getNombre(), d.getCantidad(), d.getPrecioTotal()));
        }
        lblTotal.setText("Total: " + miPedido.calcularTotal() + " Bs");
    }

    private void finalizarOrden() {
        if (miPedido != null) {
            ManejadorArchivos.guardarTicket(areaTicket.getText());
            areaTicket.setText(""); 
            miPedido = null; 
            txtMesa.setText(""); 
            lblTotal.setText("Total: 0 Bs");
        }
    }

    public static void main(String[] args) {
        new VentanaRestaurante().setVisible(true);
    }
}