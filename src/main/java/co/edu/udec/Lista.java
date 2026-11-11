package co.edu.udec;

public class Lista {

    private Nodo cabeza;
    private Nodo ultimo;
    private int tamanio;

    public Lista() {
        this.cabeza = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public void agregar(Object dato) {
        validarDato(dato);
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setDerecha(nuevo);
            nuevo.setIzquierda(ultimo);
            ultimo = nuevo;
        }
        tamanio++;
    }

    public void agregarAlInicio(Object dato) {
        validarDato(dato);
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        } else {
            nuevo.setDerecha(cabeza);
            cabeza.setIzquierda(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    public void agregarEnPosicion(int indice, Object dato) {
        validarDato(dato);
        if (indice < 0 || indice > tamanio) {
            throw new IndexOutOfBoundsException("Posicion invalida: " + indice);
        }
        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        if (indice == tamanio) {
            agregar(dato);
            return;
        }
        Nodo actual = obtenerNodo(indice);
        Nodo anterior = actual.getIzquierda();
        Nodo nuevo = new Nodo(dato);
        nuevo.setIzquierda(anterior);
        nuevo.setDerecha(actual);
        anterior.setDerecha(nuevo);
        actual.setIzquierda(nuevo);
        tamanio++;
    }

    public Object eliminarPrimero() {
        validarNoVacia();
        Object dato = cabeza.getDato();
        if (cabeza == ultimo) {
            cabeza = null;
            ultimo = null;
        } else {
            cabeza = cabeza.getDerecha();
            cabeza.setIzquierda(null);
        }
        tamanio--;
        return dato;
    }

    public Object eliminarUltimo() {
        validarNoVacia();
        Object dato = ultimo.getDato();
        if (cabeza == ultimo) {
            cabeza = null;
            ultimo = null;
        } else {
            ultimo = ultimo.getIzquierda();
            ultimo.setDerecha(null);
        }
        tamanio--;
        return dato;
    }

    public Object eliminarEnPosicion(int indice) {
        validarNoVacia();
        validarIndice(indice);
        if (indice == 0) {
            return eliminarPrimero();
        }
        if (indice == tamanio - 1) {
            return eliminarUltimo();
        }
        Nodo actual = obtenerNodo(indice);
        actual.getIzquierda().setDerecha(actual.getDerecha());
        actual.getDerecha().setIzquierda(actual.getIzquierda());
        tamanio--;
        return actual.getDato();
    }

    public Object buscarDato(int indice) {
        validarIndice(indice);
        return obtenerNodo(indice).getDato();
    }

    public Object buscarDato(Object dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getDerecha();
        }
        return null;
    }

    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    public int cuentaElementos() {
        return tamanio;
    }

    public boolean esVacia() {
        return tamanio == 0;
    }

    public void limpiar() {
        cabeza = null;
        ultimo = null;
        tamanio = 0;
    }

    public void mostrarAdelante() {
        if (esVacia()) {
            System.out.println("(lista vacia)");
            return;
        }
        Nodo actual = cabeza;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getDerecha();
            i++;
        }
    }

    public void mostrarAtras() {
        if (esVacia()) {
            System.out.println("(lista vacia)");
            return;
        }
        Nodo actual = ultimo;
        int i = tamanio;
        while (actual != null) {
            System.out.println(i + ". " + actual.getDato());
            actual = actual.getIzquierda();
            i--;
        }
    }


    private Nodo obtenerNodo(int indice) {
        Nodo actual;
        if (indice < tamanio / 2) {
            actual = cabeza;
            for (int i = 0; i < indice; i++) {
                actual = actual.getDerecha();
            }
        } else {
            actual = ultimo;
            for (int i = tamanio - 1; i > indice; i--) {
                actual = actual.getIzquierda();
            }
        }
        return actual;
    }

    private void validarDato(Object dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
    }

    private void validarNoVacia() {
        if (esVacia()) {
            throw new IllegalStateException("La lista esta vacia");
        }
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("Posicion invalida: " + indice);
        }
    }
}
