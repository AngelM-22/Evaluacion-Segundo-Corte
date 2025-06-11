package maajpixup.gui.consola;

import maajpixup.database.DatabaseConnection;
import maajpixup.negocio.Ejecutable;
import maajpixup.util.ReadUtil;

import java.sql.*;

public class CatalogoArtista implements Ejecutable {
    private static CatalogoArtista catalogoArtista;
    private boolean flag;

    private CatalogoArtista() {}

    public static CatalogoArtista getInstance() {
        if (catalogoArtista == null) {
            catalogoArtista = new CatalogoArtista();
        }
        return catalogoArtista;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        flag = true;
        while (flag) {
            System.out.println("Menu de Artistas");
            System.out.println("1.- Agregar artista");
            System.out.println("2.- Editar artista");
            System.out.println("3.- Listar artistas");
            System.out.println("4.- Eliminar artista");
            System.out.println("5.- Salir");

            System.out.print("Ingrese su opcion: ");
            int opcion = ReadUtil.readInt();
            switch (opcion) {
                case 1 -> agregarArtista();
                case 2 -> editarArtista();
                case 3 -> listarArtistas();
                case 4 -> eliminarArtista();
                case 5 -> {
                    flag = false;
                    System.out.println("Saliendo del catalogo de artistas.");
                    return;
                }
                default -> System.out.println("Opcion invalida, intenta nuevamente.");
            }
        }
    }

    private void agregarArtista() {
        System.out.print("Ingrese el nombre del artista: ");
        String nombre = ReadUtil.readString();
        String query = "INSERT INTO artistas (nombre) VALUES (?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Artista guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el artista: " + e.getMessage());
            try (Connection con = DatabaseConnection.getConnection()) {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }
        }
    }

    private void listarArtistas() {
        String query = "SELECT id, nombre FROM artistas";
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Lista de artistas:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar artistas: " + e.getMessage());
        }
    }

    private void editarArtista() {
        listarArtistas();
        System.out.print("Ingrese el ID del artista a editar: ");
        int id = ReadUtil.readInt();
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = ReadUtil.readString();

        String query = "UPDATE artistas SET nombre = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Artista editado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al editar artista: " + e.getMessage());
        }
    }

    private void eliminarArtista() {
        listarArtistas();
        System.out.print("Ingrese el ID del artista a eliminar: ");
        int id = ReadUtil.readInt();

        String query = "DELETE FROM artistas WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Artista eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar artista: " + e.getMessage());
        }
    }
}



