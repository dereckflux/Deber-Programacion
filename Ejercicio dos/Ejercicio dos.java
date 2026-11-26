import java.util.ArrayList;
import java.util.Arrays;

/**
 * Ejercicio 2. Eliminación de duplicados
 * Conceptos: contains, construcción de una lista nueva,
 * métodos estáticos con parámetros de tipo ArrayList.
 */
public class Ejercicio2 {

    /**
     * Devuelve una NUEVA lista sin repeticiones, conservando el orden
     * de la primera aparición. La lista original no se modifica.
     */
    public static ArrayList<Integer> sinDuplicados(ArrayList<Integer> lista) {
        ArrayList<Integer> resultado = new ArrayList<>();
        for (Integer valor : lista) {
            if (!resultado.contains(valor)) {
                resultado.add(valor);
            }
        }
        return resultado;
    }

    /**
     * Reto adicional: elimina los duplicados MODIFICANDO la lista original.
     * Se recorre de atrás hacia adelante con un for con índice para evitar
     * ConcurrentModificationException (que aparecería al usar remove dentro
     * de un for-each). Si la primera aparición del elemento (indexOf) está
     * antes de la posición actual, el elemento actual es un duplicado.
     * Devuelve cuántos elementos se eliminaron.
     */
    public static int eliminarDuplicadosEnLugar(ArrayList<Integer> lista) {
        int eliminados = 0;
        for (int i = lista.size() - 1; i >= 0; i--) {
            if (lista.indexOf(lista.get(i)) < i) {
                lista.remove(i);          // remove(int índice)
                eliminados++;
            }
        }
        return eliminados;
    }

    public static void main(String[] args) {
        ArrayList<Integer> original = new ArrayList<>(Arrays.asList(4, 7, 4, 2, 9, 7, 7, 1));

        ArrayList<Integer> sinRep = sinDuplicados(original);
        System.out.println("Original: " + original);
        System.out.println("Sin duplicados: " + sinRep);
        System.out.println("Elementos eliminados: " + (original.size() - sinRep.size()));

        // ----- Reto adicional -----
        System.out.println("\n--- Reto: modificando la lista original ---");
        ArrayList<Integer> copia = new ArrayList<>(original);
        System.out.println("Antes: " + copia);
        int eliminados = eliminarDuplicadosEnLugar(copia);
        System.out.println("Después: " + copia);
        System.out.println("Elementos eliminados: " + eliminados);
    }
}
