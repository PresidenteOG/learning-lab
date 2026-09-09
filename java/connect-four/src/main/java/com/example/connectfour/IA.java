package com.example.connectfour;

/**
 * Clase IA: controla el comportamiento de la inteligencia artificial del juego Conecta 4.
 *
 * Utiliza el algoritmo Minimax con poda alfa-beta para decidir la mejor columna
 * donde colocar su ficha.
 */
public class IA {

    private int jugadorIA;      // Número del jugador controlado por la IA (1 o 2)
    private int jugadorHumano;  // Número del jugador rival
    private int profundidadMax; // Cuántos niveles de jugadas futuras analiza

    // --- NUEVAS VARIABLES ---
    // Guardamos el carácter (ficha) de cada jugador ('X' o 'O')
    private char charIA;
    private char charHumano;
    // -----------------------


    /**
     * Constructor de la IA.
     *
     * @param jugadorIA Qué número de jugador será la IA (1 o 2).
     * @param dificultad Nivel de dificultad ("facil", "normal", "dificil").
     */
    public IA(int jugadorIA, String dificultad) {
        this.jugadorIA = jugadorIA;
        this.jugadorHumano = (jugadorIA == 1) ? 2 : 1;

        // Asumimos que el Jugador 1 usa 'X' y el Jugador 2 usa 'O'
        this.charIA = (this.jugadorIA == 1) ? 'X' : 'O';
        this.charHumano = (this.jugadorHumano == 1) ? 'X' : 'O';

        // Ajusta la profundidad del análisis según la dificultad
        switch (dificultad.toLowerCase()) {
            case "facil" -> this.profundidadMax = 2;   // IA muy básica
            case "normal" -> this.profundidadMax = 4;  // IA intermedia
            case "dificil" -> this.profundidadMax = 7; // IA avanzada
            default -> this.profundidadMax = 4;
        }
    }

    /**
     * Elige la mejor columna donde colocar la ficha según el análisis Minimax.
     */
    public int elegirColumna(Tablero tab) {
        int mejorColumna = -1;
        int mejorValor = Integer.MIN_VALUE;

        // Si la dificultad es baja, elige columna aleatoria válida
        if (profundidadMax < 3) {
            boolean puesto = false;
            while (!puesto) {
                Tablero copia = copiarTablero(tab);
                mejorColumna = (int) (Math.random() * 7);
                if (copia.jugada(jugadorIA, mejorColumna)) {
                    puesto = true;
                }
            }
        } else {
            // Modo normal/difícil → usa Minimax
            for (int c = 0; c < 7; c++) {
                Tablero copia = copiarTablero(tab);

                // Si se puede jugar en esa columna
                if (copia.jugada(jugadorIA, c)) {
                    int valor = minimax(copia, profundidadMax - 1, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

                    // Guarda la jugada con mejor resultado
                    if (valor > mejorValor) {
                        mejorValor = valor;
                        mejorColumna = c;
                    }
                }
            }
        }
        return mejorColumna;
    }

    /**
     * Algoritmo Minimax con poda alfa-beta.
     */
    private int minimax(Tablero tab, int profundidad, boolean maximizando, int alpha, int beta) {

        // CASO BASE 1: alguien ha ganado
        if (Logica.revisionDeLineasXYD(tab)) {
            if (maximizando) {
                return -1000000 + (profundidadMax - profundidad); // perdió la IA
            } else {
                return 1000000 - (profundidadMax - profundidad); // ganó la IA
            }
        }

        // CASO BASE 2: profundidad máxima alcanzada
        if (profundidad == 0) {
            return evaluar(tab);
        }

        // TURNO DE LA IA (MAX)
        if (maximizando) {
            int maxEval = Integer.MIN_VALUE;
            for (int c = 0; c < 7; c++) {
                Tablero copia = copiarTablero(tab);
                if (copia.jugada(jugadorIA, c)) {
                    int eval = minimax(copia, profundidad - 1, false, alpha, beta);
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);
                    if (beta <= alpha) break; // poda
                }
            }
            return maxEval;

        // TURNO DEL HUMANO (MIN)
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int c = 0; c < 7; c++) {
                Tablero copia = copiarTablero(tab);
                if (copia.jugada(jugadorHumano, c)) {
                    int eval = minimax(copia, profundidad - 1, true, alpha, beta);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    if (beta <= alpha) break; // poda
                }
            }
            return minEval;
        }
    }

    /**
     * Evalúa un tablero de forma heurística.
     */
    private int evaluar(Tablero tab) {
        Casilla[][] tablero = tab.getTablero();
        int filas = tablero.length;
        int columnas = tablero[0].length;
        int puntuacion = 0;

        // Llamadas con las 4 direcciones que realmente existen en Conecta 4
        puntuacion += evaluarDireccion(tablero, filas, columnas, 0, 1);   // Horizontal →
        puntuacion += evaluarDireccion(tablero, filas, columnas, 1, 0);   // Vertical ↓
        puntuacion += evaluarDireccion(tablero, filas, columnas, 1, 1);   // Diagonal ↘
        puntuacion += evaluarDireccion(tablero, filas, columnas, -1, 1);  // Diagonal ↗

        return puntuacion;
    }

    /**
     * Recorre el tablero en una dirección específica (horizontal, vertical o diagonal)
     * y evalúa todas las líneas de 4 fichas posibles.
     *
     * Direcciones prácticas usadas en Conecta 4:
     *  ➜ (0, 1)   → Horizontal  derecha
     *  ➜ (1, 0)   → Vertical  hacia abajo
     *  ➜ (1, 1)   → Diagonal  descendente
     *  ➜ (-1, 1)  → Diagonal  ascendente
     *
     * Significado de los valores:
     *  - dirY = 1   → avanza hacia abajo
     *  - dirY = 0   → misma fila
     *  - dirY = -1  → hacia arriba
     *
     *  - dirX = 1   → hacia la derecha
     *  - dirX = 0   → misma columna
     */
    private int evaluarDireccion(Casilla[][] tablero, int filas, int columnas, int dirY, int dirX) {
        int puntuacion = 0;

        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {

                // Verifica que no se salga del tablero al mirar 4 fichas
                int yFinal = y + (3 * dirY);
                int xFinal = x + (3 * dirX);
                if (yFinal < 0 || yFinal >= filas || xFinal < 0 || xFinal >= columnas) {
                    continue;
                }

                // Forma la cadena de 4 fichas consecutivas
                StringBuilder linea = new StringBuilder(4);
                for (int casilla = 0; casilla < 4; casilla++) {
                    linea.append(tablero[y + casilla * dirY][x + casilla * dirX].dibujar());
                }

                // Evalúa esa línea y suma a la puntuación
                puntuacion += evaluarLinea(linea.toString());
            }
        }
        return puntuacion;
    }

    /**
     * Evalúa una línea de 4 fichas y devuelve una puntuación según su contenido.
     */
    private int evaluarLinea(String linea) {
        int contIA = 0;
        int contHumano = 0;
        char vacio = ' ';

        for (char c : linea.toCharArray()) {
            if (c == this.charIA) contIA++;
            else if (c == this.charHumano) contHumano++;
        }

        // Casos extremos
        if (contIA == 4) return 100000;
        if (contHumano == 4) return -100000;

        // Amenazas y oportunidades
        if (contHumano == 3 && contIA == 0) return -5000;
        if (contIA == 3 && contHumano == 0) return 500;
        if (contHumano == 2 && contIA == 0) return -50;
        if (contIA == 2 && contHumano == 0) return 10;

        return 0;
    }

    /**
     * Crea una copia independiente del tablero actual (para que la IA pueda simular jugadas).
     */
    private Tablero copiarTablero(Tablero original) {
        Casilla[][] viejo = original.getTablero();
        Tablero copia = new Tablero(viejo.length, viejo[0].length);
        for (int i = 0; i < viejo.length; i++) {
            for (int j = 0; j < viejo[i].length; j++) {
                if (viejo[i][j].isOcupado()) {
                    copia.getTablero()[i][j].jugada(viejo[i][j].getJugador());
                }
            }
        }
        return copia;
    }
}
