import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Ejercicio 5. Menú de gestión de muestras biológicas
 * Conceptos: operaciones CRUD (add, get, set, remove), isEmpty,
 * menú con do-while y switch.
 */
class Muestra {
    private String id;
    private String tipo;
    private double temperatura; // °C

    public Muestra(String id, String tipo, double temperatura) {
        this.id = id;
        this.tipo = tipo;
        this.temperatura = temperatura;
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
    public double getTemperatura() { return temperatura; }

    @Override
    public String toString() {
        return id + " | " + tipo + " | " + temperatura + " °C";
    }
}

public class Ejercicio5 {

    static ArrayList<Muestra> muestras = new ArrayList<>();
    static Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    /** Devuelve el índice de la muestra con ese id, o -1 si no existe. */
    static int buscar(String id) {
        for (int i = 0; i < muestras.size(); i++) {
            if (muestras.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("1. Registrar  2. Listar  3. Actualizar temperatura  4. Eliminar  5. Salir");
            System.out.println("6. Listar a -80 °C o menos  7. Contar por tipo");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar el salto de línea

            switch (opcion) {
                case 1: registrar(); break;
                case 2: listar(); break;
                case 3: actualizar(); break;
                case 4: eliminar(); break;
                case 5: System.out.println("Programa finalizado."); break;
                case 6: listarUltracongeladas(); break;
                case 7: contarPorTipo(); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
        sc.close();
    }

    // (1) CREATE: add, sin ID repetido
    static void registrar() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        if (buscar(id) != -1) {
            System.out.println("Ya existe una muestra con ese ID.");
            return;
        }
        System.out.print("Tipo: ");
        String tipo = sc.nextLine().trim();
        System.out.print("Temperatura (°C): ");
        double temp = sc.nextDouble();
        sc.nextLine();
        muestras.add(new Muestra(id, tipo, temp));
        System.out.println("Muestra registrada.");
    }

    // (2) READ: get, isEmpty
    static void listar() {
        if (muestras.isEmpty()) {
            System.out.println("No hay muestras registradas.");
            return;
        }
        for (int i = 0; i < muestras.size(); i++) {
            System.out.println("[" + i + "] " + muestras.get(i));
        }
    }

    // (3) UPDATE: reemplazo con set
    static void actualizar() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        int pos = buscar(id);
        if (pos == -1) {
            System.out.println("No existe una muestra con ese ID.");
            return;
        }
        System.out.print("Nueva temperatura (°C): ");
        double nueva = sc.nextDouble();
        sc.nextLine();
        Muestra anterior = muestras.get(pos);
        muestras.set(pos, new Muestra(anterior.getId(), anterior.getTipo(), nueva));
        System.out.println("Temperatura actualizada.");
    }

    // (4) DELETE: remove por índice
    static void eliminar() {
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        int pos = buscar(id);
        if (pos == -1) {
            System.out.println("No existe una muestra con ese ID.");
            return;
        }
        muestras.remove(pos);
        System.out.println("Muestra eliminada.");
    }

    // Reto (6): muestras a -80 °C o menos
    static void listarUltracongeladas() {
        boolean hay = false;
        for (Muestra m : muestras) {
            if (m.getTemperatura() <= -80) {
                System.out.println(m);
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay muestras a -80 °C o menos.");
    }

    // Reto (7): cuántas muestras hay de cada tipo (con dos ArrayList paralelos)
    static void contarPorTipo() {
        if (muestras.isEmpty()) {
            System.out.println("No hay muestras registradas.");
            return;
        }
        ArrayList<String> tipos = new ArrayList<>();
        ArrayList<Integer> conteos = new ArrayList<>();
        for (Muestra m : muestras) {
            int idx = tipos.indexOf(m.getTipo());
            if (idx == -1) {
                tipos.add(m.getTipo());
                conteos.add(1);
            } else {
                conteos.set(idx, conteos.get(idx) + 1);
            }
        }
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println(tipos.get(i) + ": " + conteos.get(i));
        }
    }
}
