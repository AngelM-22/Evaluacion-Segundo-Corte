package maajpixup.gui.consola;

import maajpixup.database.DatabaseConnection;
import maajpixup.negocio.Ejecutable;
import maajpixup.util.ReadUtil;

import java.sql.*;

public class CatalogoDisco implements Ejecutable {
    private static CatalogoDisco catalogoDisco;
    private boolean flag;

    private CatalogoDisco() {}

    public static CatalogoDisco getInstance() {
        if (catalogoDisco == null) {
            catalogoDisco = new CatalogoDisco();
        }
        return catalogoDisco;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        flag = true;
        while (flag) {
            System.out.println("Menu de Discos");
            System.out.println("1.- Agregar disco");
            System.out.println("2.- Editar disco");
            System.out.println("3.- Listar discos");
            System.out.println("4.- Eliminar disco");
            System.out.println("5.- Salir");

            System.out.print("Ingrese su opción: ");
            int opcion = ReadUtil.readInt();
            switch (opcion) {
                case 1 -> agregarDisco();
                case 2 -> editarDisco();
                case 3 -> listarDiscos();
                case 4 -> eliminarDisco();
                case 5 -> {
                    flag = false;
                    System.out.println("Saliendo del catalogo de discos");
                }
                default -> System.out.println("Opcion invalida, intenta nuevamente.");
            }
        }
    }

    private void agregarDisco() {
        System.out.print("Ingrese el titulo del disco: ");
        String titulo = ReadUtil.readString();
        String query = "INSERT INTO discos (titulo) VALUES (?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
            con.commit(); // 🔹 Asegura que la transacción se guarde
            System.out.println("Disco guardado correctamente en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar el disco: " + e.getMessage());
        }
    }

    private void listarDiscos() {
        String query = "SELECT id, titulo FROM discos";
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Lista de discos:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("titulo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar discos: " + e.getMessage());
        }
    }

    private void editarDisco() {
        listarDiscos();
        System.out.print("Ingrese el ID del disco a editar: ");
        int id = ReadUtil.readInt();
        System.out.print("Ingrese el nuevo titulo: ");
        String nuevoTitulo = ReadUtil.readString();

        String query = "UPDATE discos SET titulo = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nuevoTitulo);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Disco editado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al editar disco: " + e.getMessage());
        }
    }

    private void eliminarDisco() {
        listarDiscos();
        System.out.print("Ingrese el ID del disco a eliminar: ");
        int id = ReadUtil.readInt();

        String query = "DELETE FROM discos WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
            System.out.println("Disco eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar disco: " + e.getMessage());
        }
    }
}


