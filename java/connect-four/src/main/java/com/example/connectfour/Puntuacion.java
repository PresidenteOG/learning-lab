package com.example.connectfour;

import processing.core.PApplet;
import java.util.List;

/**
 * Clase encargada de mostrar las puntuaciones en pantalla. Muestra dos
 * columnas: puntuaciones VS IA y puntuaciones VS Jugadores. Utiliza la librería
 * Processing 
 */
public class Puntuacion {

    private PApplet app;
    private List<gestorPuntuaciones.puntuacion> puntuacionesIA;
    private List<gestorPuntuaciones.puntuacion> puntuacionesJugadores;

    /**
     * Constructor: inicializa el PApplet y carga las puntuaciones desde el
     * gestor.
     */
    public Puntuacion(PApplet app) {
        this.app = app;
        // Cargar las puntuaciones desde los archivos o memoria
        this.puntuacionesIA = gestorPuntuaciones.cargarPuntuacionesIA();
        this.puntuacionesJugadores = gestorPuntuaciones.cargarPuntuacionesJugadores();
    }

    /**
     * Recarga las listas de puntuaciones, útil si se actualizan mientras se
     * ejecuta el programa.
     */
    public void recargar() {
        this.puntuacionesIA = gestorPuntuaciones.cargarPuntuacionesIA();
        this.puntuacionesJugadores = gestorPuntuaciones.cargarPuntuacionesJugadores();
    }

    /**
     * Dibuja la pantalla de puntuaciones completa. Organiza las puntuaciones en
     * dos columnas
     */
    public void mostrar() {
        // Fondo de la pantalla
        app.background(20); 

        // Configuración del texto del título
        app.fill(255); // Color 
        app.textAlign(PApplet.CENTER); // Centrar texto horizontalmente
        app.textSize(32); // Tamaño del texto
        app.text("PUNTUACIONES", app.width / 2, 60); 

        // Configuración de los encabezados de las columnas
        app.textSize(20); // Tamaño del texto
        app.fill(200, 220, 255); // Color
        app.text("VS IA", app.width / 4, 120); // Encabezado izquierda

        app.fill(255, 200, 200); // Color
        app.text("VS JUGADORES", app.width * 3 / 4, 120); // Encabezado derecha

        // Configuración del texto de las puntuaciones
        app.textSize(16); // Tamaño del texto
        app.fill(255); // Color  

        int yStart = 160;

        // Jugador vs IA
        for (int i = 0; i < puntuacionesIA.size() && i < 20; i++) {
            gestorPuntuaciones.puntuacion p = puntuacionesIA.get(i);
            String texto = (i + 1) + ". " + p.nombre + "  |  " + p.tiempo + "s  |  " + p.turnos + " turnos  |  "
                    + p.dificultad;
            app.text(texto, app.width / 4, yStart + (i * 25));
        }

        // Jugador vs jugador
        for (int i = 0; i < puntuacionesJugadores.size(); i++) {
            gestorPuntuaciones.puntuacion p = puntuacionesJugadores.get(i);
            String texto = (i + 1) + ". " + p.nombre + "  |  " + p.tiempo + "s  |  " + p.turnos + " turnos";
            app.text(texto, app.width * 3 / 4, yStart + (i * 25)); // Dibujar en la columna derecha
        }

        // Texto inferior para indicar cómo volver al juego
        app.fill(180); // Color 
        app.textSize(14); // Tamaño del texto
        app.text("Presiona 'ESC' para volver al juego", app.width / 2, app.height - 40); 
    }
}
