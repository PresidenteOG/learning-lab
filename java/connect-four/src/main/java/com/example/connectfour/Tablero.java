package com.example.connectfour;

public class Tablero {

    // ATRIBUTOS
    // Matriz bidimensional que representa el tablero del juego.
    // Cada posición es una casilla (vacía o ocupada por un jugador).
    private Casilla[][] tablero;

    // Indica si el juego sigue en curso o ha terminado.
    private boolean jugando;

    // CONSTRUCTOR
    public Tablero(int filas, int columnas) {
        // Crea el tablero con el número de filas y columnas indicadas.
        tablero = new Casilla[filas][columnas];
        jugando = true; // Al crear el tablero, el juego comienza activo.
        llenartablero(); // Inicializa todas las casillas como vacías.
    }

    // GETTERS Y SETTERS
    // Devuelve el tablero completo (matriz de casillas)
    public Casilla[][] getTablero() {
        return tablero;
    }

    // Devuelve si el juego está en marcha o no.
    public boolean isJugando() {
        return jugando;
    }

    // Permite cambiar el estado del juego (por ejemplo, a false cuando alguien gana)
    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }

    // MÉTODOS DEL TABLERO
    public void llenartablero() {
        // Recorre todas las filas y columnas del tablero
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                // Crea una nueva casilla vacía en cada posición
                tablero[i][j] = new Casilla();
            }
        }
    }

    /**
     * Intenta realizar una jugada en una columna.
     *
     * @param jugador → número del jugador (por ejemplo, 1 o 2)
     * @param columna → la columna donde el jugador quiere tirar su ficha
     * @return true si la jugada se realizó correctamente, false si no fue
     * posible.
     */
    public boolean jugada(int jugador, int columna) {
        // Si la columna está fuera de los límites del tablero, no se puede jugar.
        if (columna < 0 || columna >= tablero[0].length) {
            return false;
        }
        // Si la parte superior de la columna ya está ocupada, significa que está llena.
        if (tablero[0][columna].isOcupado()) {
            return false;
        }
        // Recorre la columna desde abajo hacia arriba buscando la primera casilla libre.
        for (int i = tablero.length - 1; i >= 0; i--) {
            if (!tablero[i][columna].isOcupado()) {
                // Marca la casilla como ocupada por el jugador que ha tirado la ficha.
                tablero[i][columna].jugada(jugador);
                return true; // Jugada realizada con éxito.
            }
        }
        // Si no se encontró espacio (columna llena), devuelve false.
        return false;
    }

    /**
     * Busca la primera fila libre en una columna (de abajo hacia arriba).
     *
     * @param columna → columna que se quiere comprobar.
     * @return número de la fila disponible, o -1 si no hay hueco.
     */
    public int getFilaDisponible(int columna) {
        // Si la columna no existe (fuera del tablero), devuelve -1.
        if (columna < 0 || columna >= tablero[0].length) {
            return -1;
        }
        // Recorre la columna desde la parte inferior hacia arriba.
        for (int f = tablero.length - 1; f >= 0; f--) {
            // Si encuentra una casilla vacía (jugador = 0), devuelve esa fila.
            if (tablero[f][columna].getJugador() == 0) {
                return f;
            }
        }
        // Si no hay ninguna casilla vacía en esa columna, devuelve -1.
        return -1;
    }

    public boolean tableroLleno() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (!tablero[i][j].isOcupado()) {
                    return false; // Todavía hay espacio libre
                }
            }
        }
        return true; // Todas las casillas están ocupadas
    }
}
