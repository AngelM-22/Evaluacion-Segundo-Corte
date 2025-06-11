package maajpixup.gui.consola;

import maajpixup.negocio.Ejecutable;

public class Consola extends LecturaAccion {
    private static Consola consola;

    private Consola() {}

    public static Consola getInstance() {
        if (consola == null) {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Bienvenido a la Consola");
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
        } else if (opcion == 2) { // 🔹 Se agrega manejo de la opción "Salir"
            System.out.println("Saliendo del sistema...");
            System.exit(0); // 🔹 Cierra el programa correctamente
        } else {
            System.out.println("Opción inválida, intenta nuevamente.");
        }

        if (ejecutable != null) {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }

}
