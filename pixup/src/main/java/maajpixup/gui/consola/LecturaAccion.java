package maajpixup.gui.consola;

import maajpixup.negocio.Ejecutable;
import maajpixup.util.ReadUtil;

public abstract class LecturaAccion implements Ejecutable {
    protected boolean flag = true;
    protected int opcion;

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            despliegaMenu();
            opcion = ReadUtil.readInt();
            if (opcion >= valorMinMenu() && opcion <= valorMaxMenu()) {
                procesaOpcion();
            } else {
                System.out.println("Opción inválida, intenta nuevamente.");
            }
        }
    }

    public abstract void despliegaMenu();
    public abstract int valorMinMenu();
    public abstract int valorMaxMenu();
    public abstract void procesaOpcion();
}
