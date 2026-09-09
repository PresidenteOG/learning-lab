package sudoku;

/**
 *
 * @author Daniel Adanegbe
 */
import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[][] Tabla = new String[9][9];
        String[] Cuadro = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Tabla[i][j] = "[ ]";
            }
        }
        boolean a = false;
        while (!a) {
            System.out.println("Sudoku start");
            // String yes = in.nextLine();
            mostrartabla(Tabla);
            System.out.println("Recursiva Simple o Random?");
            String switcher = in.nextLine();
            Tabla = recursiva(Tabla, a, 0, 0, 0);
        }

    }

    public static void mostrartabla(String[][] Tabla) {
        for (int j = 0; j < 9; j++) { // filas
            for (int k = 0; k < 9; k++) { // columnas
                System.out.print(Tabla[j][k] + " ");
                if ((k + 1) % 3 == 0) {
                    System.out.print("   "); // separador cada 3
                }
            }
            System.out.println();
            if ((j + 1) % 3 == 0) {
                System.out.println(); // separador cada 3 filas
            }
        }
    }

    public static String[][] recursiva(String[][] Tabla, boolean a, int Cuadro, int PosicionA, int PosicionB) {

        int Valor = 1;
        for (int i = PosicionA; i < 9; i += 3) {
            //System.out.println(i);
            for (int b = PosicionB; b < 9; b += 3) {
                //System.out.println("Pos:" + i);
                //System.out.println("Pas:" + b);
                Tabla[i][b] = "[" + Valor + "]";
                //System.out.println(Tabla[i][b]);
                for (int c = Valor; c < 10; c++) {
                    Valor++;
                    c = 10;
                }
            }
        }
        PosicionB++;
        if (PosicionA % 3 == 0 && PosicionA != 0) {
            return Tabla;
        } else if (PosicionB % 3 == 0 && PosicionB != 0) {
            PosicionB = 0;
            PosicionA++;
        }
        // PosicionA++;
        /*
        PosicionA++;
        
        if (PosicionA % 2 == 0 && PosicionA != 0) {
            PosicionA = 0;
            PosicionB++;
        } else if (PosicionB % 2 == 0 && PosicionB != 0) {
            a = true;
            return Tabla;
        }
        return recursiva(Tabla, a, Cuadro, PosicionA, PosicionB);
         */
        return recursiva(Tabla, a, Cuadro, PosicionA, PosicionB);
    }
}
