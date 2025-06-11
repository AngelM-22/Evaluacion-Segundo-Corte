package maajpixup.gui.consola;

import maajpixup.negocio.Ejecutable;
import maajpixup.util.ReadUtil;

public class ListaCatalogos extends LecturaAccion {
    private static ListaCatalogos listaCatalogos;

    private ListaCatalogos() {}

    public static ListaCatalogos getInstance() {
        if (listaCatalogos == null) {
            listaCatalogos = new ListaCatalogos();
        }
        return listaCatalogos;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1.- Canción");
        System.out.println("2.- Disco");
        System.out.println("3.- Artista");
        System.out.println("4.- Disquera");
        System.out.println("5.- Género Musical");
        System.out.println("6.- Salir");
    }

    @Override
    public int valorMinMenu() { return 1; }

    @Override
    public int valorMaxMenu() { return 6; }

    @Override
    public void procesaOpcion() {
        System.out.print("Ingrese su opción: ");
        opcion = ReadUtil.readInt();

        Ejecutable ejecutable = null;

        switch (opcion) {
            case 1:
                ejecutable = CatalogoCancion.getInstance();
                break;
            case 2:
                ejecutable = CatalogoDisco.getInstance();
                break;
            case 3:
                ejecutable = CatalogoArtista.getInstance();
                break;
            case 4:
                ejecutable = CatalogoDisquera.getInstance();
                break;
            case 5:
                ejecutable = CatalogoGeneroMusical.getInstance();
                break;
            case 6:
                System.out.println("Saliendo...");
                flag = false;
                return;
        }

        if (ejecutable != null) {
            ejecutable.setFlag(true);
            ejecutable.run();
            flag = false;
        } else {
            System.out.println("Opción inválida, intenta nuevamente.");
        }
    }
}
