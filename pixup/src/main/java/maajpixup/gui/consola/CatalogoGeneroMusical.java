package maajpixup.gui.consola;

import maajpixup.database.DatabaseConnection;
import maajpixup.negocio.Ejecutable;
import maajpixup.util.ReadUtil;

import java.sql.*;

public class CatalogoGeneroMusical implements Ejecutable {
    private static CatalogoGeneroMusical catalogoGeneroMusical;
    private boolean flag;

    private CatalogoGeneroMusical() {}

    public static CatalogoGeneroMusical getInstance() {
        if (catalogoGeneroMusical == null) {
            catalogoGeneroMusical = new CatalogoGeneroMusical();
        }
        return catalogoGeneroMusical;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        flag = true;
        while (flag) {
            System.out.println("Menu de Generos Musicales");
            System.out.println("1.- Agregar genero musical");
            System.out.println("2.- Editar genero musical");
            System.out.println("3.- Listar generos musicales");
            System.out.println("4.- Eliminar genero musical");
            System.out.println("5.- Salir");

            System.out.print("Ingrese su opcion: ");
            int opcion = ReadUtil.readInt();
            switch (opcion) {
                case 1 -> agregarGenero();
                case 2 -> editarGenero();
                case 3 -> listarGeneros();
                case 4 -> eliminarGenero();
                case 5 -> {
                    flag = false;
                    System.out.println("Saliendo del catalogo de generos musicales.");
                    return;
                }
                default -> System.out.println("Opcion invalida, intenta nuevamente.");
            }
        }
    }

    private void agregarGenero() {
        System.out.print("Ingrese el nombre del genero musical: ");
        String nombre = ReadUtil.readString();
        String query = "INSERT INTO generos (nombre) VALUES (?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Genero musical guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el genero musical: " + e.getMessage());
            try (Connection con = DatabaseConnection.getConnection()) {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }
        }
    }

    private void listarGeneros() {
        String query = "SELECT id, nombre FROM generos";
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Lista de generos musicales:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar generos musicales: " + e.getMessage());
        }
    }

    private void editarGenero() {
        listarGeneros();
        System.out.print("Ingrese el ID del genero musical a editar: ");
        int id = ReadUtil.readInt();
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = ReadUtil.readString();

        String query = "UPDATE generos SET nombre = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Genero musical editado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al editar genero musical: " + e.getMessage());
        }
    }

    private void eliminarGenero() {
        listarGeneros();
        System.out.print("Ingrese el ID del genero musical a eliminar: ");
        int id = ReadUtil.readInt();

        String query = "DELETE FROM generos WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Genero musical eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar genero musical: " + e.getMessage());
        }
    }
}
