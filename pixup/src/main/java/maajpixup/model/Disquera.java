package maajpixup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Disquera")
public class Disquera extends Catalogo {
    private String nombre;

    public Disquera() {}

    public Disquera(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}