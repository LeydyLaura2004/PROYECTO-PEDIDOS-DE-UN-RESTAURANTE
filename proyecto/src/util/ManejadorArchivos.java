package util;

import java.io.*;

public class ManejadorArchivos {
    // Definimos la carpeta y el archivo
    private static final String CARPETA = "data";
    private static final String NOMBRE_ARCHIVO = "ticket.txt";

    public static void guardarTicket(String contenido) {
        // 1. Aseguramos que la carpeta exista
        File directory = new File(CARPETA);
        if (!directory.exists()) {
            directory.mkdirs(); // Crea la carpeta si no existe
        }

        // 2. Definimos la ruta completa
        String rutaCompleta = CARPETA + File.separator + NOMBRE_ARCHIVO;

        // 3. Escribimos en esa ruta
        try (PrintWriter out = new PrintWriter(new FileWriter(rutaCompleta, true))) {
            out.println("--- NUEVO PEDIDO ---");
            out.println(contenido);
            out.println("--------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}