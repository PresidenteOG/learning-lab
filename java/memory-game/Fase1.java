package Memory;

import java.util.Scanner;

/**
 *
 * @author danie
 */
public class Fase1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        fase1(in);
    }

    public static void fase1(Scanner in) {
        char[] Palabras = {'A', 'B', 'C', 'D', 'E', 'A', 'B', 'C', 'D', 'E'};
        char[] Boxs = {'?', '?', '?', '?', '?', '?', '?', '?', '?', '?'};
        int Puntos = 0;
        int[] Posiciones;
        while (Puntos != Palabras.length / 2) {
            mostarposicion(Boxs);
            Posiciones = compararposicion(Boxs, in);
            Boxs = actualizarbox(Palabras, Boxs, Posiciones);
            Puntos += puntaje(Palabras, Posiciones);
        }
        mostarposicion(Boxs);
        System.out.println("COMPLETADO!");
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

    public static char[] actualizarbox(char[] Palabras, char[] Boxs, int[] Posiciones) {
        if (Palabras[Posiciones[0]] == Palabras[Posiciones[1]]) {
            Boxs[Posiciones[0]] = Palabras[Posiciones[0]];
            Boxs[Posiciones[1]] = Palabras[Posiciones[1]];
            System.out.println("Acertado!");
        } else {
            char[] Temp = {'?', '?', '?', '?', '?', '?', '?', '?', '?', '?'};
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
