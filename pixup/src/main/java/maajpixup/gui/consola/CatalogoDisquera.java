package maajpixup.gui.consola;

import maajpixup.database.DatabaseConnection;
import maajpixup.negocio.Ejecutable;
import maajpixup.util.ReadUtil;

import java.sql.*;

public class CatalogoDisquera implements Ejecutable {
    private static CatalogoDisquera catalogoDisquera;
    private boolean flag;

    private CatalogoDisquera() {}

    public static CatalogoDisquera getInstance() {
        if (catalogoDisquera == null) {
            catalogoDisquera = new CatalogoDisquera();
        }
        return catalogoDisquera;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        flag = true;
        while (flag) {
            System.out.println("Menu de Disqueras");
            System.out.println("1.- Agregar disquera");
            System.out.println("2.- Editar disquera");
            System.out.println("3.- Listar disqueras");
            System.out.println("4.- Eliminar disquera");
            System.out.println("5.- Salir");

            System.out.print("Ingrese su opcion: ");
            int opcion = ReadUtil.readInt();
            switch (opcion) {
                case 1 -> agregarDisquera();
                case 2 -> editarDisquera();
                case 3 -> listarDisqueras();
                case 4 -> eliminarDisquera();
                case 5 -> {
                    flag = false;
                    System.out.println("Saliendo del catalogo de disqueras.");
                    return;
                }
                default -> System.out.println("Opcion invalida, intenta nuevamente.");
            }
        }
    }

    private void agregarDisquera() {
        System.out.print("Ingrese el nombre de la disquera: ");
        String nombre = ReadUtil.readString();
        String query = "INSERT INTO disqueras (nombre) VALUES (?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Disquera guardada correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar la disquera: " + e.getMessage());
            try (Connection con = DatabaseConnection.getConnection()) {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }
        }
    }

    private void listarDisqueras() {
        String query = "SELECT id, nombre FROM disqueras";
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Lista de disqueras:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar disqueras: " + e.getMessage());
        }
    }

    private void editarDisquera() {
        listarDisqueras();
        System.out.print("Ingrese el ID de la disquera a editar: ");
        int id = ReadUtil.readInt();
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = ReadUtil.readString();

        String query = "UPDATE disqueras SET nombre = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Disquera editada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al editar disquera: " + e.getMessage());
        }
    }

    private void eliminarDisquera() {
        listarDisqueras();
        System.out.print("Ingrese el ID de la disquera a eliminar: ");
        int id = ReadUtil.readInt();

        String query = "DELETE FROM disqueras WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Disquera eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar disquera: " + e.getMessage());
        }
    }
}
