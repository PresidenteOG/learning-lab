package com.example.connectfour;
import java.io.*;
import java.nio.file.*;
import java.util.*;
/**
 * Su función principal es guardar y cargar los resultados de las partidas,
 * tanto de los modos contra la IA como de jugador vs jugador.
 */
public class gestorPuntuaciones {
    // Constantes con los nombres de los archivos donde se guardan las puntuaciones
    private static final String ARCHIVO_IA = "puntuaciones_ia.txt";
    private static final String ARCHIVO_JUGADORES = "puntuaciones_jugadores.txt";
    /**
     * Clase interna que representa una puntuación. Contiene toda la información que queremos guardar.
     */
    public static class puntuacion {
        public final String nombre;
        public final long tiempo;
        public final int turnos;
        public final String dificultad;
        // Constructor para partidas
        public puntuacion(String nombre, long tiempo, int turnos, String dificultad) {
            this.nombre = nombre;
            this.tiempo = tiempo;
            this.turnos = turnos;
            this.dificultad = dificultad;
        }
    }
    /**
     * Metodo para guardar una puntuación en un archivo.
     * Recibe el nombre del archivo y una línea de texto que se añadirá al final del fichero.
     */
    private static void guardarPuntuacion(String archivo, String linea) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo, true)))) {
            pw.println(linea); // escribe la puntuación en el archivo
        } catch (IOException e) {
            System.err.println("Error al guardar puntuación en " + archivo + ": " + e.getMessage());
        }
    }
    /**
     * Guarda una puntuación de una partida contra la IA.
     */
    public static void guardarPuntuacionIA(String nombre, long tiempo, int turnos, String dificultad) {
        guardarPuntuacion(ARCHIVO_IA, nombre + "," + tiempo + "," + turnos + "," + dificultad);
    }
    /**
     * Guarda una puntuación de una partida entre dos jugadores.
     */
    public static void guardarPuntuacionJugadores(String nombre, long tiempo, int turnos) {
        guardarPuntuacion(ARCHIVO_JUGADORES, nombre + "," + tiempo + "," + turnos);
    }
    /**
     * Carga todas las puntuaciones de partidas contra la IA desde su archivo.
     * Devuelve una lista ordenada por el número de turnos (de menor a mayor).
     */
    public static List<puntuacion> cargarPuntuacionesIA() {
        return cargarPuntuaciones(ARCHIVO_IA, true);
    }
    /**
     * Carga todas las puntuaciones de partidas entre jugadores desde su archivo.
     * También devuelve la lista ordenada por turnos.
     */
    public static List<puntuacion> cargarPuntuacionesJugadores() {
        return cargarPuntuaciones(ARCHIVO_JUGADORES, false);
    }
    /**
     * Metodo privado y reutilizable que carga puntuaciones desde un archivo.
     * Si 'conDificultad' es true, espera 4 datos por línea (nombre, tiempo, turnos, dificultad).
     * Si es false, espera 3 datos (nombre, tiempo, turnos).
     * Devuelve una lista de objetos 'puntuacion' ordenada por turnos.
     */
    private static List<puntuacion> cargarPuntuaciones(String archivo, boolean conDificultad) {
        List<puntuacion> lista = new ArrayList<>();
        Path path = Paths.get(archivo);
        // Si el archivo no existe, devolvemos una lista vacía para evitar errores
        if (!Files.exists(path)) return lista;
        try {
            // Recorremos cada línea del archivo
            for (String linea : Files.readAllLines(path)) {
                String[] datos = linea.split(",");
                // Verificamos que la línea tenga la cantidad de datos esperada
                if ((conDificultad && datos.length >= 4) || (!conDificultad && datos.length >= 3)) {
                    String nombre = datos[0];
                    long tiempo = Long.parseLong(datos[1]);
                    int turnos = Integer.parseInt(datos[2]);
                    String dificultad = conDificultad ? datos[3] : "";
                    // Creamos y añadimos la puntuación a la lista
                    lista.add(new puntuacion(nombre, tiempo, turnos, dificultad));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar puntuaciones de " + archivo + ": " + e.getMessage());
        }
        // Ordenamos la lista por número de turnos, de menos a mas
        lista.sort(Comparator.comparingInt(p -> p.turnos));
        return lista;
    }
}
