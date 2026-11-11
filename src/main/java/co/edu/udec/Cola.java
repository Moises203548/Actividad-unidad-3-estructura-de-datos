package co.edu.udec;

public class Cola {

    private Nodo frente;
    private Nodo fin;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamanio = 0;
    }

    public void encolar(Object dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            frente = nuevo;
        } else {
            fin.setDerecha(nuevo);
        }
        fin = nuevo;
        tamanio++;
    }

    public Object desencolar() {
        if (esVacia()) {
            throw new IllegalStateException("La cola esta vacia");
        }
        Object dato = frente.getDato();
        frente = frente.getDerecha();
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    public Object peek() {
        if (esVacia()) {
            throw new IllegalStateException("La cola esta vacia");
        }
        return frente.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public boolean contiene(Object dato) {
        Nodo actual = frente;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getDerecha();
        }
        return false;
    }

    public void limpiar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("(cola vacia)");
            return;
        }
        Nodo actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.getDato());actual = actual.getDerecha();posicion++;
        }
    }
}
