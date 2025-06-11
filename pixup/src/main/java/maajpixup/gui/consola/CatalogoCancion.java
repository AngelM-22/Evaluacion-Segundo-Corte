package maajpixup.gui.consola;

import maajpixup.database.DatabaseConnection;
import maajpixup.negocio.Ejecutable;
import maajpixup.util.ReadUtil;

import java.sql.*;

public class CatalogoCancion implements Ejecutable {
    private static CatalogoCancion catalogoCancion;
    private boolean flag;

    private CatalogoCancion() {}

    public static CatalogoCancion getInstance() {
        if (catalogoCancion == null) {
            catalogoCancion = new CatalogoCancion();
        }
        return catalogoCancion;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        flag = true;
        while (flag) {
            System.out.println("Menu de Canciones");
            System.out.println("1.- Agregar cancion");
            System.out.println("2.- Editar cancion");
            System.out.println("3.- Listar canciones");
            System.out.println("4.- Eliminar cancion");
            System.out.println("5.- Salir");

            System.out.print("Ingrese su opcion: ");
            int opcion = ReadUtil.readInt();
            switch (opcion) {
                case 1 -> agregarCancion();
                case 2 -> editarCancion();
                case 3 -> listarCanciones();
                case 4 -> eliminarCancion();
                case 5 -> {
                    flag = false;
                    System.out.println("Saliendo del catalogo de canciones.");
                    return;
                }
                default -> System.out.println("Opcion invalida, intenta nuevamente.");
            }
        }
    }

    private void agregarCancion() {
        System.out.print("Ingrese el titulo de la cancion: ");
        String titulo = ReadUtil.readString();
        String query = "INSERT INTO canciones (titulo) VALUES (?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Cancion guardada correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la cancion: " + e.getMessage());
            try (Connection con = DatabaseConnection.getConnection()) {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }
        }
    }

    private void listarCanciones() {
        String query = "SELECT id, titulo FROM canciones";
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Lista de canciones:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("titulo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar canciones: " + e.getMessage());
        }
    }

    private void editarCancion() {
        listarCanciones();
        System.out.print("Ingrese el ID de la cancion a editar: ");
        int id = ReadUtil.readInt();
        System.out.print("Ingrese el nuevo titulo: ");
        String nuevoTitulo = ReadUtil.readString();

        String query = "UPDATE canciones SET titulo = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nuevoTitulo);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Cancion editada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al editar cancion: " + e.getMessage());
        }
    }

    private void eliminarCancion() {
        listarCanciones();
        System.out.print("Ingrese el ID de la cancion a eliminar: ");
        int id = ReadUtil.readInt();

        String query = "DELETE FROM canciones WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Cancion eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cancion: " + e.getMessage());
        }
    }
}

