package co.edu.udec;
import java.util.Scanner;
public class SistemaGimnasio {

    private final Lista listaGeneral = new Lista();
    private final Cola colaPendientes = new Cola();
    private final Pila historialProcesados = new Pila();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new SistemaGimnasio().ejecutar();
    }

    private void ejecutar() {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                switch (opcion) {
                    case 1: registrarInscripcion(); break;
                    case 2: verTodas(); break;
                    case 3: verPendientes(); break;
                    case 4: procesarSiguiente(); break;
                    case 5: verHistorial(); break;
                    case 6: buscarPorCodigo(); break;
                    case 7: cancelarPendiente(); break;
                    case 8: deshacerUltimo(); break;
                    case 9: verCantidades(); break;
                    case 10: System.out.println("Hasta pronto."); break;
                    default: System.out.println("Opcion no valida. Elija entre 1 y 10.");
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    private void mostrarMenu() {
        System.out.println("\n===== GIMNASIO - INSCRIPCIONES A CLASES =====");
        System.out.println("1. Registrar inscripcion");
        System.out.println("2. Ver todas las inscripciones registradas");
        System.out.println("3. Ver inscripciones pendientes");
        System.out.println("4. Procesar siguiente inscripcion");
        System.out.println("5. Ver historial de inscripciones procesadas");
        System.out.println("6. Buscar inscripcion por codigo");
        System.out.println("7. Cancelar inscripcion pendiente");
        System.out.println("8. Deshacer ultimo procesamiento");
        System.out.println("9. Ver cantidad de inscripciones");
        System.out.println("10. Salir");
    }

    private void registrarInscripcion() {
        String codigo = leerTextoObligatorio("Codigo de inscripcion: ");
        if (listaGeneral.contiene(new InscripcionClase(codigo, "", "", ""))) {
            throw new IllegalArgumentException("Ya existe una inscripcion con el codigo " + codigo);
        }
        String socio = leerTextoObligatorio("Nombre del socio: ");
        String clase = leerTextoObligatorio("Nombre de la clase: ");
        String horario = leerTextoObligatorio("Horario (ej. Lunes 6:00 pm): ");

        InscripcionClase inscripcion = new InscripcionClase(codigo, socio, clase, horario);
        listaGeneral.agregar(inscripcion);
        colaPendientes.encolar(inscripcion);
        System.out.println("Inscripcion registrada y enviada a pendientes.");
    }

    private void verTodas() {
        System.out.println("--- Todas las inscripciones (adelante) ---");
        listaGeneral.mostrarAdelante();
    }

    private void verPendientes() {
        System.out.println("--- Pendientes (orden de llegada) ---");
        colaPendientes.mostrar();
    }

    private void procesarSiguiente() {
        InscripcionClase procesada = (InscripcionClase) colaPendientes.desencolar();
        procesada.setEstado(InscripcionClase.PROCESADA);
        historialProcesados.apilar(procesada);
        System.out.println("Procesada: " + procesada);
    }

    private void verHistorial() {
        System.out.println("--- Historial (la mas reciente primero) ---");
        historialProcesados.mostrar();
    }

    private void buscarPorCodigo() {
        String codigo = leerTextoObligatorio("Codigo a buscar: ");
        Object encontrada = listaGeneral.buscarDato(new InscripcionClase(codigo, "", "", ""));
        if (encontrada == null) {
            System.out.println("No existe una inscripcion con el codigo " + codigo);
        } else {
            System.out.println("Encontrada: " + encontrada);
        }
    }

    private void cancelarPendiente() {
        if (colaPendientes.esVacia()) {
            throw new IllegalStateException("No hay inscripciones pendientes para cancelar");
        }
        String codigo = leerTextoObligatorio("Codigo a cancelar: ");
        InscripcionClase buscada = new InscripcionClase(codigo, "", "", "");
        if (!colaPendientes.contiene(buscada)) {
            throw new IllegalArgumentException("El codigo " + codigo + " no esta pendiente");
        }

        Cola auxiliar = new Cola();
        InscripcionClase cancelada = null;
        while (!colaPendientes.esVacia()) {
            InscripcionClase actual = (InscripcionClase) colaPendientes.desencolar();
            if (actual.equals(buscada)) {
                cancelada = actual;
            } else {
                auxiliar.encolar(actual);
            }
        }
        while (!auxiliar.esVacia()) {
            colaPendientes.encolar(auxiliar.desencolar());
        }
        cancelada.setEstado(InscripcionClase.CANCELADA);
        System.out.println("Cancelada: " + cancelada);
    }

    private void deshacerUltimo() {
        InscripcionClase ultima = (InscripcionClase) historialProcesados.desapilar();
        ultima.setEstado(InscripcionClase.PENDIENTE);
        colaPendientes.encolar(ultima);
        System.out.println("Deshecho. Vuelve a pendientes: " + ultima);
    }

    private void verCantidades() {
        System.out.println("Registradas: " + listaGeneral.cuentaElementos());
        System.out.println("Pendientes:  " + colaPendientes.tamanio());
        System.out.println("Procesadas:  " + historialProcesados.tamanio());
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String linea = scanner.hasNextLine() ? scanner.nextLine().trim() : "10";
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String leerTextoObligatorio(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
            if (texto.isEmpty()) {
                System.out.println("Este campo no puede estar vacio.");
                if (!scanner.hasNextLine()) {
                    throw new IllegalStateException("Entrada terminada");
                }
            }
        } while (texto.isEmpty());
        return texto;
    }
}
