import java.util.ArrayList;

/**
 * Ejercicio 3. Lista de objetos Estudiante
 * Conceptos: ArrayList de objetos, encapsulamiento,
 * búsqueda secuencial, remove por índice.
 */
class Estudiante {
    // Atributos privados (encapsulamiento)
    private String codigo;
    private String nombre;
    private double promedio;

    public Estudiante(String codigo, String nombre, double promedio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.promedio = promedio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPromedio() { return promedio; }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " - " + promedio;
    }
}

public class Ejercicio3 {

    /** Búsqueda secuencial: devuelve el índice del estudiante o -1 si no existe. */
    public static int buscarPorCodigo(ArrayList<Estudiante> lista, String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo().equals(codigo)) {
                return i;
            }
        }
        return -1;
    }

    public static void listar(ArrayList<Estudiante> lista) {
        for (Estudiante e : lista) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("E001", "Ana Torres", 8.75));
        estudiantes.add(new Estudiante("E002", "Luis Mena", 6.40));
        estudiantes.add(new Estudiante("E003", "Carla Ruiz", 9.20));
        estudiantes.add(new Estudiante("E004", "Jorge Vaca", 7.10));

        System.out.println("--- Lista de estudiantes ---");
        listar(estudiantes);

        // Búsqueda de un código existente
        String buscado = "E003";
        int pos = buscarPorCodigo(estudiantes, buscado);
        if (pos != -1) {
            System.out.println("Encontrado " + buscado + " en la posición " + pos + ": "
                    + estudiantes.get(pos).getNombre());
        } else {
            System.out.println("No existe el estudiante " + buscado);
        }

        // Búsqueda de un código inexistente
        String inexistente = "E009";
        if (buscarPorCodigo(estudiantes, inexistente) == -1) {
            System.out.println("No existe el estudiante " + inexistente);
        }

        // Eliminación por índice (remove(int))
        String aEliminar = "E002";
        int idx = buscarPorCodigo(estudiantes, aEliminar);
        if (idx != -1) {
            Estudiante eliminado = estudiantes.remove(idx);
            System.out.println("Eliminado: " + eliminado);
        }

        // Estudiante con el mejor promedio
        Estudiante mejor = estudiantes.get(0);
        for (Estudiante e : estudiantes) {
            if (e.getPromedio() > mejor.getPromedio()) {
                mejor = e;
            }
        }

        System.out.println("--- Lista actualizada ---");
        listar(estudiantes);
        System.out.println("Total de estudiantes: " + estudiantes.size());
        System.out.println("Mejor promedio: " + mejor.getNombre() + " (" + mejor.getPromedio() + ")");
    }
}
