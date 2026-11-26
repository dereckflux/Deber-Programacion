import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Ejercicio 1. Registro de calificaciones
 * Conceptos: add, get, size, recorrido con for-each.
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        // Locale.US para que el decimal se escriba con punto (8.5)
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        ArrayList<Double> notas = new ArrayList<>();

        System.out.print("¿Cuántas notas desea ingresar? ");
        int cantidad = sc.nextInt();
        while (cantidad <= 0) {
            System.out.print("La cantidad debe ser mayor que 0. Ingrese de nuevo: ");
            cantidad = sc.nextInt();
        }

        // Lectura y validación de cada nota (rango 0 a 10)
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nota " + i + ": ");
            double nota = sc.nextDouble();
            while (nota < 0 || nota > 10) {
                System.out.print("Nota fuera de rango. Ingrese de nuevo: ");
                nota = sc.nextDouble();
            }
            notas.add(nota);
        }

        // Cálculo de suma, máxima y mínima con for-each
        double suma = 0;
        double maxima = notas.get(0);
        double minima = notas.get(0);
        for (double n : notas) {
            suma += n;
            if (n > maxima) maxima = n;
            if (n < minima) minima = n;
        }
        double promedio = suma / notas.size();

        System.out.println("Notas: " + notas);
        System.out.println(String.format(Locale.US, "Promedio: %.2f", promedio));
        System.out.println("Máxima: " + maxima + " | Mínima: " + minima);

        sc.close();
    }
}
