package maajpixup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Artista")
public class Artista extends Catalogo {
    private String nombre;

    public Artista() {}

    public Artista(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}

