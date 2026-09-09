package Memory;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author danie
 */
public class Fase2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        fase2(in);
    }

    public static void fase2(Scanner in) {
        boolean a = false;
        int Cantidad = 0;
        while (!a) {
            System.out.println("Cantidad de casillas:");
            Cantidad = in.nextInt();
            if (Cantidad % 2 == 0 && Cantidad >= 2 && Cantidad <= 50) {
                a = true;
            } else {
                System.out.println("Debe ser Minimo 2,Maximo 50 y Par.");
            }
        }

        char[] Palabras = randomposition(Cantidad);
        char[] Boxs = interrogantes(Cantidad);
        int Puntos = 0;
        int[] Posiciones;
        while (Puntos != Cantidad) {
            mostarposicion(Boxs);
            Posiciones = compararposicion(Boxs, in);
            Boxs = actualizarbox(Palabras, Boxs, Posiciones,Cantidad);
            Puntos += puntaje(Palabras, Posiciones);
        }
        mostarposicion(Boxs);
        System.out.println("COMPLETADO!");
    }
    
    public static char[] interrogantes(int Cantidad) {
        char[] Cadena = new char[Cantidad*2];
        for (int i = 0; i < Cantidad*2 ; i++){
            Cadena[i] = '?';
        }
        return Cadena;
    }
    public static char[] randomposition(int Cantidad) {
        char[] Cadena = new char[Cantidad*2];
        ArrayList<Character> random = new ArrayList<>();
        String Palabras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Seleccionado = Palabras.substring(0, Cantidad);
        for (int i = 0; i < Seleccionado.length(); i++) {
            random.add(Palabras.charAt(i));
        }
        int posicion = 0;
        for (int i = 0; i < 2; i++) {
            Collections.shuffle(random);
            for (Character letra : random) {
                Cadena[posicion] = letra;
                posicion++;
            }
        }
        return Cadena;
    }
    public static void mostarposicion(char[] Palabras) {
        for (char A : Palabras) {
            System.out.print("[" + A + "] ");
        }
        System.out.println("");
    }
    public static int[] compararposicion(char[] Boxs, Scanner in) {
        boolean a = false;
        int Pos1 = 0;
        int Pos2 = 0;
        while (!a) {
            System.out.println("------");
            System.out.print("Posicion 1: ");
            Pos1 = in.nextInt() - 1;
            System.out.print("Posicion 2: ");
            Pos2 = in.nextInt() - 1;
            System.out.println("------");
            if (Pos1 != Pos2 && Pos1 < Boxs.length && Pos1 > -1 && Pos2 < Boxs.length && Pos2 > -1 && Boxs[Pos1] == '?' && Boxs[Pos2] == '?') {
                a = true;
            } else {
                System.out.println("Error de posiciones");
            }
        }
        int[] Posiciones = {Pos1, Pos2};
        return Posiciones;
    }
    public static char[] actualizarbox(char[] Palabras, char[] Boxs, int[] Posiciones,int Cantidad) {
        if (Palabras[Posiciones[0]] == Palabras[Posiciones[1]]) {
            Boxs[Posiciones[0]] = Palabras[Posiciones[0]];
            Boxs[Posiciones[1]] = Palabras[Posiciones[1]];
            System.out.println("Acertado!");
        } else {
            char[] Temp = interrogantes(Cantidad);
            Temp[Posiciones[0]] = Palabras[Posiciones[0]];
            Temp[Posiciones[1]] = Palabras[Posiciones[1]];
            mostarposicion(Temp);
            System.out.println("[No acertado!]");
        }
        return Boxs;
    }
    public static int puntaje(char[] Palabras, int[] Posiciones) {
        if (Palabras[Posiciones[0]] == Palabras[Posiciones[1]]) {
            return 1;
        } else {
            return 0;
        }
    }
}
