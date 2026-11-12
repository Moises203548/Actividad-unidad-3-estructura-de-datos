package co.edu.udec;

public class Pila {

    private Nodo tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    public void apilar(Object dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        Nodo nuevo = new Nodo(dato);
        nuevo.setDerecha(tope);
        tope = nuevo;
        tamanio++;
    }

    public Object desapilar() {
        if (esVacia()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        Object dato = tope.getDato();
        tope = tope.getDerecha();
        tamanio--;
        return dato;
    }

    public Object peek() {
        if (esVacia()) {
            throw new IllegalStateException("La pila esta vacia");
        }
        return tope.getDato();
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public boolean contiene(Object dato) {
        return buscar(dato) != -1;
    }

    public void limpiar() {
        tope = null;
        tamanio = 0;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("(pila vacia)");
            return;
        }
        Nodo actual = tope;
        while (actual != null) {
            System.out.println("- " + actual.getDato());
            actual = actual.getDerecha();
        }
    }

    public int buscar(Object dato) {
        Nodo actual = tope;
        int posicion = 0;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getDerecha();
            posicion++;
        }
        return -1;
    }
}
