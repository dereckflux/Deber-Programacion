import java.util.ArrayList;
import java.util.Comparator;

/**
 * Ejercicio 4. Inventario de reactivos de laboratorio
 * Conceptos: ordenamiento con sort y Comparator,
 * modificación de objetos dentro de la lista, filtrado.
 */
class Reactivo {
    private String nombre;
    private double cantidad;     // en gramos
    private double stockMinimo;  // en gramos

    public Reactivo(String nombre, double cantidad, double stockMinimo) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
    }

    public String getNombre() { return nombre; }
    public double getCantidad() { return cantidad; }
    public double getStockMinimo() { return stockMinimo; }

    /** Descuenta la cantidad solo si hay existencias suficientes. */
    public boolean consumir(double gramos) {
        if (gramos > 0 && gramos <= cantidad) {
            cantidad -= gramos;
            return true;
        }
        return false;
    }

    public boolean necesitaReposicion() {
        return cantidad < stockMinimo;
    }

    @Override
    public String toString() {
        return nombre + ": " + cantidad + " g (mín. " + stockMinimo + " g)";
    }
}

public class Ejercicio4 {

    public static void mostrar(ArrayList<Reactivo> lista) {
        for (Reactivo r : lista) {
            System.out.println(r);
        }
    }

    /** Busca un reactivo por nombre (búsqueda secuencial). */
    public static Reactivo buscar(ArrayList<Reactivo> lista, String nombre) {
        for (Reactivo r : lista) {
            if (r.getNombre().equalsIgnoreCase(nombre)) {
                return r;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Reactivo> inventario = new ArrayList<>();
        inventario.add(new Reactivo("NaCl", 250.0, 100.0));
        inventario.add(new Reactivo("Tris-HCl", 120.0, 50.0));
        inventario.add(new Reactivo("EDTA", 40.0, 30.0));
        inventario.add(new Reactivo("Agarosa", 15.0, 20.0));
        inventario.add(new Reactivo("SDS", 8.0, 10.0));

        // 1) Ordenar alfabéticamente por nombre
        inventario.sort(Comparator.comparing(Reactivo::getNombre, String.CASE_INSENSITIVE_ORDER));
        System.out.println("--- Inventario por nombre ---");
        mostrar(inventario);

        // 2) Registrar consumos
        System.out.println("Consumo de 15 g de EDTA: " + buscar(inventario, "EDTA").consumir(15));
        System.out.println("Consumo de 30 g de SDS: " + buscar(inventario, "SDS").consumir(30));

        // 3) Ordenar por cantidad de mayor a menor
        inventario.sort(Comparator.comparingDouble(Reactivo::getCantidad).reversed());
        System.out.println("--- Inventario por cantidad ---");
        mostrar(inventario);

        // 4) Filtrar reactivos por debajo del stock mínimo
        System.out.println("--- Reactivos por reponer ---");
        for (Reactivo r : inventario) {
            if (r.necesitaReposicion()) {
                System.out.println(r);
            }
        }
    }
}
