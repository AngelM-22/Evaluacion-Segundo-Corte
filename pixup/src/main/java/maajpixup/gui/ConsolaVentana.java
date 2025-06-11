package maajpixup.gui;

import maajpixup.gui.consola.LecturaAccion;
import maajpixup.gui.consola.ListaCatalogos;
import maajpixup.negocio.Ejecutable;

public class ConsolaVentana extends LecturaAccion {
    private static ConsolaVentana consolaVentana;

    private ConsolaVentana() {}

    public static ConsolaVentana getInstance() {
        if (consolaVentana == null) {
            consolaVentana = new ConsolaVentana();
        }
        return consolaVentana;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Administrar Catálogos");
        System.out.println("2.- Salir");
    }

    @Override
    public int valorMinMenu() { return 1; }

    @Override
    public int valorMaxMenu() { return 2; }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;

        if (opcion == 1) {
            ejecutable = ListaCatalogos.getInstance();
        } else if (opcion == 2) {
            System.out.println("Saliendo del sistema...");
            flag = false;
            System.exit(0);
        } else {
            System.out.println("Opción inválida, intenta nuevamente.");
        }

        if (ejecutable != null) {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }

}
