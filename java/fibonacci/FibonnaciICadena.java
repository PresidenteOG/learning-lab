
import java.util.Scanner;

/**
 *
 * @author Daniel Adanegbe
 */
public class FibonnaciICadena {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int SumadorNext = 1;
        int SumaAnterior = 0;
       //Cadena
       //String Palabra = in.nextLine();
       //System.out.println("Palabra Reversa: " + Parte2(Palabra));
       //Fibonnaci
       //int Valor = in.nextInt();
       //Parte1();
       //System.out.println(FibonnaciR(in.nextInt()));
    }

    public static void Parte1() {
        Scanner in = new Scanner(System.in);
        System.out.println("Serie Fibonacci, hasta cuanto quieres llegar?");
        int Valor = in.nextInt();
        int Temp;
        int SumadorNext = 1;
        int SumaAnterior = 0;
        for (int A = 0; A < Valor; A++) {
            System.out.println("Cuadro " + "[" + A + "]: " + (SumaAnterior + SumadorNext));
            Temp = SumaAnterior + SumadorNext;
            SumaAnterior = SumadorNext;
            SumadorNext = Temp;

        }
    }

    public static void Parte1A(int SumaAnterior, int SumadorNext, int Veces, int Count) {
        if (Veces == 0) {
            return;
        } else {
            int Temp;
            int a,b,d;
            a = SumaAnterior;
            b = SumadorNext;
            d = Count;
            Count++;
            //System.out.println("Cuadro " + "[" + Count + "]: " + (SumaAnterior + SumadorNext));
            Temp = SumaAnterior + SumadorNext;
            SumaAnterior = SumadorNext;
            SumadorNext = Temp;
            Parte1A(SumaAnterior, SumadorNext, Veces - 1, Count);
            System.out.println("Cuadro " + "[" + d + "]: " + (a + b));
        }
    }
    public static String Parte2(String palabra){
        System.out.println(palabra);
        if (palabra == null || palabra.length() == 1){
            return palabra;
        }
        return palabra.charAt(palabra.length() - 1) + Parte2(palabra.substring(0,palabra.length() - 1));
    }
    public static Integer FibonnaciR(int n){
        if (n<=1) return n;
        else return FibonnaciR(n-1) + FibonnaciR(n-2);
    }
}