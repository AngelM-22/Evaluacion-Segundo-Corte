package maajpixup.gui.consola;

import java.util.ArrayList;
import java.util.List;

public abstract class Catalogos<T> {
    protected List<T> lista;

    public Catalogos() {
        lista = new ArrayList<>();
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public void add(T t) {
        lista.add(t);
    }

    public void remove(T t) {
        lista.remove(t);
    }

    public abstract T newT();
    public abstract boolean processNewT(T t);
    public abstract void processEditT(T t);

    public void printAll() {
        for (T t : lista) {
            System.out.println(t);
        }
    }
}
