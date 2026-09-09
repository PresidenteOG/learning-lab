package com.example.connectfour;

import processing.core.PApplet;
import javax.swing.JOptionPane;

public class Main extends PApplet {

    // -------------------------------------------------
    // * Variables (dejadas tal cual)
    // -------------------------------------------------
    // Variables para la animación de la ficha
    boolean animandoCaida = false;  // Booleano de frenar y evadir el sobre pressing (Multiple fichas en un turno)
    float fichaX, fichaY;           // posición actual de la ficha animada
    int fichaColumnaDestino = -1;   // Posicion ultima de la columna
    int fichaFilaDestino = -1;      // Posicion ultima de la fila
    int jugadorFicha = 0;           // 1/2 segun el turno
    float velocidadCaida = 20;      // Velocidad de pixel de Caida

    // Variables para la tabla
    int filas = 6, columnas = 7;    // Cantidad de Filas y Columnas
    int tam = 100;                  // Tamaño actual
    float margenX, margenY;         // Margen X y Y de la tabla
    int estadoJuego = 0;            // 0=inicio, 1=menú, 2=config partida, 3=jugando
    int turno = 1;                  // Turno actual del jugador
    int modoJuego = 2;              // 1=PVP, 2=IA
    String dificultadIA = "normal"; // Difficultad de la IA
    int turnosTotales = 0;          // Turnos totales
    boolean mostrandoPuntuaciones = false;  //Mostracion Puntuaciones 
    String[] opcionesMenu = {       // Opciones Menu
        "Jugar", 
        "Salir" 
    };    
    
    // Variables de Partida
    float tiempoJ1, tiempoJ2;       //Tiempo de Jugador 1 y 2
    float ultimoFrame;              // Ultimo Frame
    float deltaTime;                // Tiempo actual

    // Configuración botones
    float btnModoX, btnModoY, btnModoW, btnModoH;
    float btnDifX, btnDifY, btnDifW, btnDifH;
    float btnStartX, btnStartY, btnStartW, btnStartH;

    // Variables de clases
    Tablero tableroObj;             // Tablero
    IA ia;                          // IA
    Puntuacion Puntuacion;          // Tabla


    // =================================================
    // 1) Ciclo de vida (Processing): main, settings, setup, draw
    // =================================================
    public static void main(String[] args) {
        PApplet.main("com.example.connectfour.Main");
    }

    public void settings() {
        size(1280, 740);
    }

    public void setup() {
        // Texto alineado centralmente y tamaño 32
        textAlign(CENTER, CENTER);
        textSize(32);

        // Crea un nuevo tablero con las filas y columnas
        tableroObj = new Tablero(filas, columnas);

        // Cambia los márgenes X y Y de la tabla
        margenX = width * 0.1f;
        margenY = (height - filas * tam) / 2f + 40;

        // Si la selección de modo de juego es 2, se prepara la IA con su dificultad
        if (modoJuego == 2) ia = new IA(2, dificultadIA);

        // Tiempo
        ultimoFrame = millis();
        deltaTime = 0;

        // Puntuación
        Puntuacion = new Puntuacion(this);
    }

    public void draw() {
        // Delta time
        float ahora = millis();
        deltaTime = ahora - ultimoFrame;
        ultimoFrame = ahora;

        // Fondo
        background(80, 100, 180);

        // Mostrar tabla de puntuaciones si corresponde
        if (mostrandoPuntuaciones) {
            Puntuacion.mostrar();
            return;
        }

        // Estados de juego
        switch (estadoJuego) {
            case 0 -> dibujarInicio();
            case 1 -> dibujarMenu();
            case 2 -> dibujarConfigPartida();
            case 3 -> dibujarPartida();
        }
    }


    // =================================================
    // 2) Pantallas UI: inicio, menú y configuración
    // =================================================
    private void dibujarInicio() {
        textAlign(CENTER, CENTER);
        fill(255);
        textSize(80);
        text("CONECTA 4", width / 2, height / 2 - 100);
        textSize(36);
        text("Proyecto 1", width / 2, height / 2 - 40);
        textSize(28);
        text("Haz clic para empezar", width / 2, height / 2 + 40);
    }

    private void dibujarMenu() {
        textAlign(CENTER, CENTER);
        textSize(72);
        fill(255);
        text("CONECTA 4", width / 2, height / 4);
        textSize(40);

        // Opciones como botones
        for (int i = 0; i < opcionesMenu.length; i++) {
            float y = height / 2 + i * 100;
            float w = 300, h = 60;
            fill(255);
            rect(width / 2 - w / 2, y - h / 2, w, h, 20);
            fill(0);
            text(opcionesMenu[i], width / 2, y);
        }
    }

    private void manejarClickMenu() {
        for (int i = 0; i < opcionesMenu.length; i++) {
            float y = height / 2 + i * 100;
            float w = 300, h = 60;
            if (dentroRect(width / 2 - w / 2, y - h / 2, w, h)) {
                switch (i) {
                    case 0 -> estadoJuego = 2; // Ir a configuración
                    case 1 -> exit();          // Salir
                }
            }
        }
    }

    private void dibujarConfigPartida() {
        textAlign(CENTER, CENTER);
        textSize(60);
        fill(255);
        text("CONFIGURAR PARTIDA", width / 2, height / 6);

        // Botones modo
        btnModoW = 260; btnModoH = 60;
        btnModoX = width / 2 - btnModoW / 2;
        btnModoY = height / 2 - 40;

        textSize(28);
        fill(modoJuego == 1 ? color(180, 255, 180) : color(255));
        rect(btnModoX - 140, btnModoY - btnModoH / 2, btnModoW, btnModoH, 18);
        fill(0); text("Jugador vs Jugador", btnModoX - 140 + btnModoW / 2, btnModoY);

        fill(modoJuego == 2 ? color(180, 255, 180) : color(255));
        rect(btnModoX + 140, btnModoY - btnModoH / 2, btnModoW, btnModoH, 18);
        fill(0); text("Jugador vs IA", btnModoX + 140 + btnModoW / 2, btnModoY);

        // Botones dificultad (solo si IA)
        btnDifW = 160; btnDifH = 50;
        btnDifX = width / 2 - (btnDifW * 1.5f);
        btnDifY = btnModoY + 120;

        if (modoJuego == 2) {
            textSize(24);
            fill(255);
            text("Dificultad:", width / 2, btnDifY - 40);

            boolean sobreFac = dentroRect(btnDifX, btnDifY - btnDifH / 2, btnDifW, btnDifH);
            fill(dificultadIA.equals("facil") ? color(0, 255, 0) : (sobreFac ? color(255, 230, 150) : color(255)));
            rect(btnDifX, btnDifY - btnDifH / 2, btnDifW, btnDifH, 12);
            fill(0); text("Fácil", btnDifX + btnDifW / 2, btnDifY);

            boolean sobreNor = dentroRect(btnDifX + btnDifW + 20, btnDifY - btnDifH / 2, btnDifW, btnDifH);
            fill(dificultadIA.equals("normal") ? color(255, 255, 0) : (sobreNor ? color(255, 230, 150) : color(255)));
            rect(btnDifX + btnDifW + 20, btnDifY - btnDifH / 2, btnDifW, btnDifH, 12);
            fill(0); text("Normal", btnDifX + btnDifW + 20 + btnDifW / 2, btnDifY);

            boolean sobreDif = dentroRect(btnDifX + 2 * (btnDifW + 20), btnDifY - btnDifH / 2, btnDifW, btnDifH);
            fill(dificultadIA.equals("dificil") ? color(255, 0, 0) : (sobreDif ? color(255, 230, 150) : color(255)));
            rect(btnDifX + 2 * (btnDifW + 20), btnDifY - btnDifH / 2, btnDifW, btnDifH, 12);
            fill(0); text("Difícil", btnDifX + 2 * (btnDifW + 20) + btnDifW / 2, btnDifY);
        }

        // Botón empezar
        btnStartW = 260; btnStartH = 70;
        btnStartX = width / 2 - btnStartW / 2;
        btnStartY = height - 120;
        fill(0, 200, 255);
        rect(btnStartX, btnStartY - btnStartH / 2, btnStartW, btnStartH, 18);
        fill(255); textSize(28);
        text("Empezar partida", btnStartX + btnStartW / 2, btnStartY);

        textSize(22); fill(255);
        text("Pulsa ESC para volver atrás", width / 2, btnStartY + 80);
    }

    private void manejarClickConfigPartida() {
        if (dentroRect(btnModoX - 140, btnModoY - btnModoH / 2, btnModoW, btnModoH)) modoJuego = 1;
        if (dentroRect(btnModoX + 140, btnModoY - btnModoH / 2, btnModoW, btnModoH)) modoJuego = 2;

        if (modoJuego == 2) {
            if (dentroRect(btnDifX, btnDifY - btnDifH / 2, btnDifW, btnDifH)) dificultadIA = "facil";
            if (dentroRect(btnDifX + btnDifW + 20, btnDifY - btnDifH / 2, btnDifW, btnDifH)) dificultadIA = "normal";
            if (dentroRect(btnDifX + 2 * (btnDifW + 20), btnDifY - btnDifH / 2, btnDifW, btnDifH)) dificultadIA = "dificil";
        }

        if (dentroRect(btnStartX, btnStartY - btnStartH / 2, btnStartW, btnStartH)) iniciarPartida();
    }


    // =================================================
    // 3) Control de partida y render del tablero
    // =================================================
    private void iniciarPartida() {
        tableroObj = new Tablero(filas, columnas);
        estadoJuego = 3;
        turno = 1;
        turnosTotales = 0;
        tiempoJ1 = tiempoJ2 = 0;
        ultimoFrame = millis();
        if (modoJuego == 2) ia = new IA(2, dificultadIA);
    }

    private void dibujarPartida() {
        textAlign(CENTER, CENTER);
        background(60, 90, 150);
        textSize(48);
        fill(255);
        text("Conecta 4", width / 1.3f, margenY / 2);

        dibujarTablero();

        // Ficha “flotante”
        if (!animandoCaida && tableroObj.isJugando()) {
            if (mouseX >= margenX && mouseX <= margenX + columnas * tam) {
                int col = (int) ((mouseX - margenX) / tam);
                if (col >= 0 && col < columnas) {
                    float x = margenX + col * tam + tam / 2f;
                    float y = margenY - tam / 2f;
                    fill(turno == 1 ? color(255, 0, 0) : color(0, 0, 255));
                    ellipse(x, y, tam * 0.8f, tam * 0.8f);
                }
            }
        }
        // Animación de caída
        else if (animandoCaida) {
            fill(jugadorFicha == 1 ? color(255, 0, 0) : color(0, 0, 255));
            ellipse(fichaX, fichaY, tam * 0.8f, tam * 0.8f);

            fichaY += velocidadCaida;
            if (fichaY >= margenY + fichaFilaDestino * tam + tam / 2f) {
                fichaY = margenY + fichaFilaDestino * tam + tam / 2f;
                animandoCaida = false;

                tableroObj.jugada(jugadorFicha, fichaColumnaDestino);
                turnosTotales++;

                if (Logica.revisionDeLineasXYD(tableroObj)) {
                    tableroObj.setJugando(false);
                } else {
                    turno = (turno == 1) ? 2 : 1;
                    if (modoJuego == 2 && turno == 2 && tableroObj.isJugando()) {
                        iniciarCaidaIA();
                    }
                }
            }
        }

        // Panel lateral
        float panelLeft = width * 0.68f;
        float panelWidth = width * 0.28f;
        fill(30, 30, 40, 120);
        rect(panelLeft, margenY - 20, panelWidth, filas * tam + 40, 20);

        textAlign(LEFT, CENTER);
        fill(255);
        textSize(28);

        // Info de partida
        if (tableroObj.isJugando()) {
            if (modoJuego == 1) {
                if (turno == 1) tiempoJ1 += deltaTime / 1000f;
                else tiempoJ2 += deltaTime / 1000f;

                text("Jugador 1: " + formatoTiempo(tiempoJ1), panelLeft + 20, height / 3);
                text("Jugador 2: " + formatoTiempo(tiempoJ2), panelLeft + 20, height / 3 + 40);
            } else {
                if (turno == 1) tiempoJ1 += deltaTime / 1000f; // Tiempo total de partida
                text("Tiempo total: " + formatoTiempo(tiempoJ1), panelLeft + 20, height / 3);
            }

            textSize(30);
            String turnoTexto = (modoJuego == 2 && turno == 2) ? "IA" : "Jugador " + turno;
            text("Turno: " + turnoTexto, panelLeft + 20, height / 2);

            float btnX = panelLeft + 20;
            float btnY = height / 2 + 100;
            float btnW = panelWidth - 40;
            float btnH = 50;
            textAlign(CENTER, CENTER);
            fill(100, 100, 255);
            rect(btnX, btnY - btnH / 2, btnW, btnH, 12);
            fill(255);
            textSize(20);
            if (modoJuego == 2) {
                text("X para ver los ganadores vs la IA", btnX + btnW / 2, btnY);
            } else {
                text("X para ver los ganadores de jugadores", btnX + btnW / 2, btnY);
            }

        } else {
            // Mostrar ganador y guardar puntuación
            String ganador = (modoJuego == 2 && turno == 2) ? "IA" : "Jugador " + (turno == 1 ? 2 : 1);
            textSize(34);
            text("Ganador: " + ganador, panelLeft + 20, height / 2);
            textSize(20);
            text("Presiona ENTER para volver al menú", panelLeft + 20, height / 2 + 50);
            guardarPuntuacion(ganador);
            noLoop();
        }
    }

    private void dibujarTablero() {
        float tableroW = columnas * tam;
        float tableroH = filas * tam;
        fill(30, 60, 120);
        rect(margenX - 20, margenY - 20, tableroW + 40, tableroH + 40, 30);

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                int j = tableroObj.getTablero()[f][c].getJugador();
                fill(switch (j) {
                    case 1 -> color(255, 0, 0);
                    case 2 -> color(0, 0, 255);
                    default -> color(255);
                });
                ellipse(margenX + c * tam + tam / 2f, margenY + f * tam + tam / 2f, tam * 0.8f, tam * 0.8f);
            }
        }
    }


    // =================================================
    // 4) Entrada del usuario: ratón y teclado
    // =================================================
    public void mousePressed() {
        switch (estadoJuego) {
            case 0 -> estadoJuego = 1;
            case 1 -> manejarClickMenu();
            case 2 -> manejarClickConfigPartida();
            case 3 -> manejarClickJuego();
        }
    }

    private void manejarClickJuego() {
        if (!tableroObj.isJugando() || animandoCaida) return;

        int col = (int) ((mouseX - margenX) / tam);
        if (mouseY < margenY + filas * tam && col >= 0 && col < columnas) {
            int fila = tableroObj.getFilaDisponible(col);
            if (fila != -1) {
                iniciarAnimacionCaida(turno, col, fila);
            }
        }
    }

    public void keyPressed() {
        if (key == ESC) {
            key = 0;
            if (mostrandoPuntuaciones) {
                mostrandoPuntuaciones = false;
            } else {
                estadoJuego = 1;
            }
            loop();
        }

        if ((key == ENTER || key == '\n') && !tableroObj.isJugando()) {
            estadoJuego = 1;
            loop();
        }

        if (key == 'x' || key == 'X') {
            if (mostrandoPuntuaciones) {
                mostrandoPuntuaciones = false;
            } else {
                Puntuacion.recargar();
                mostrandoPuntuaciones = true;
            }
        }
    }


    // =================================================
    // 5) Animaciones
    // =================================================
    private void iniciarAnimacionCaida(int jugador, int col, int fila) {
        animandoCaida = true;
        jugadorFicha = jugador;
        fichaColumnaDestino = col;
        fichaFilaDestino = fila;
        fichaX = margenX + col * tam + tam / 2f;
        fichaY = margenY - tam / 2f;
    }

    private void iniciarCaidaIA() {
        int colIA = ia.elegirColumna(tableroObj);
        int filaIA = tableroObj.getFilaDisponible(colIA);
        iniciarAnimacionCaida(2, colIA, filaIA);
    }


    // =================================================
    // 6) Utilidades y puntuaciones
    // =================================================
    private boolean dentroRect(float x, float y, float w, float h) {
        return mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h;
    }

    private String formatoTiempo(float segs) {
        int tot = (int) Math.floor(segs);
        int minutos = tot / 60;
        int segundos = tot % 60;
        return nf(minutos, 2) + ":" + nf(segundos, 2);
    }

    private void guardarPuntuacion(String ganador) {
        // Si la IA ha ganado, no pedimos nombre ni guardamos
        if (ganador.equalsIgnoreCase("IA")) return;

        // Calculamos tiempo final (en segundos)
        int tiempoFinal = (modoJuego == 1) 
            ? (int) (tiempoJ1 + tiempoJ2)
            : (int) tiempoJ1; // en modo IA, tiempoJ1 es el tiempo total

        // Pide nombre del ganador
        String nombrePrompt = "Introduce el nombre del ganador (" + ganador + "):";
        String nombreGanador = JOptionPane.showInputDialog(
            null, nombrePrompt, "Nombre del ganador", JOptionPane.QUESTION_MESSAGE
        );

        if (nombreGanador == null || nombreGanador.trim().isEmpty()) {
            if (ganador.toLowerCase().startsWith("jugador")) nombreGanador = ganador;
            else nombreGanador = "Ganador";
        }

        long tiempoLong = (long) tiempoFinal;

        // Guardamos según el modo de juego
        if (modoJuego == 2) {
            gestorPuntuaciones.guardarPuntuacionIA(nombreGanador, tiempoLong, turnosTotales, dificultadIA);
        } else {
            gestorPuntuaciones.guardarPuntuacionJugadores(nombreGanador, tiempoLong, turnosTotales);
        }
    }
}
