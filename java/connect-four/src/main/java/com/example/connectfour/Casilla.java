package com.example.connectfour;
public class Casilla {
    //Atributos de la clase
    private boolean ocupado;
    private int jugador;
    
    // Ocupa en una casilla
    public Casilla() {
        this.ocupado = false;
    }
    // Dibuja con circulos o X o nada
    public String dibujar(){
        if (ocupado) {
            if(jugador == 1){
                return "X";
            }else{
                return "O";
            }
        }else{
           return " ";
        }
    }

    // Si esta ocupado, devuelve ocupado
    public boolean isOcupado() {
        return ocupado;
    }
    // Devuelve el numero jugador
    public int getJugador() {
        return jugador;
    }
    // Jugada del jugador
    public void jugada(int jugador) {
        this.ocupado = true;
        this.jugador = jugador;
    }
}