package com.example.connectfour;
public class Logica {
    // Mira si hay 4 en línea (horizontal, vertical o diagonales)
    public static boolean revisionDeLineasXYD(Tablero Tabla) {
        Casilla[][] Tablero = Tabla.getTablero();   // Matriz de casillas
        int Fila = Tablero.length;                  // Nº de filas (ej. 6)
        int Columna = Tablero[0].length;            // Nº de columnas (ej. 7)
        // Direcciones a comprobar: → (0,1), ↓ (1,0), ↘ (1,1), ↙ (1,-1)
        int[][] Direcciones = {
            { 0,  1 },   // Horizontal →
            { 1,  0 },   // Vertical   ↓
            { 1,  1 },   // Diagonal   ↘
            { 1, -1 }    // Diagonal   ↙
        };
        // Recorre todas las posiciones del tablero
        for (int y = 0; y < Fila; y++) {
            for (int x = 0; x < Columna; x++) {
                // Letra actual de la casilla (se espera "X", "O" o " " vacío)
                String LetraStr = Tablero[y][x].dibujar();
                if (LetraStr == null || LetraStr.isEmpty() || LetraStr.charAt(0) == ' ')
                    continue; // Casilla vacía: no hay línea que empiece aquí
                char Letra = LetraStr.charAt(0); // 'X' o 'O'
                // Prueba las 4 direcciones desde (y, x)
                for (int[] dir : Direcciones) {
                    int direccionY = dir[0];
                    int direccionX = dir[1];
                    // Comprueba que caben 3 pasos más en esta dirección
                    int yFin = y + 3 * direccionY;
                    int xFin = x + 3 * direccionX;
                    if (yFin < 0 || yFin >= Fila || xFin < 0 || xFin >= Columna)
                        continue; // Se saldría del tablero
                    // Compara las siguientes 3 celdas con la letra actual
                    boolean cuatro = true;
                    for (int k = 1; k < 4; k++) {
                        String Siguiente = Tablero[y + k * direccionY][x + k * direccionX].dibujar();
                        if (Siguiente == null || Siguiente.isEmpty() || Siguiente.charAt(0) != Letra) {
                            cuatro = false;
                            break;
                        }
                    }
                    if (cuatro) return true; // Encontrado 4 en línea
                }
            }
        }
        // Si no se encontró ninguna línea de 4
        return false;
    }
}
