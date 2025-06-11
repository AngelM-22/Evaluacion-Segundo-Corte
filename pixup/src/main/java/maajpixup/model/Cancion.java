package maajpixup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Cancion")
public class Cancion extends Catalogo {
    private String titulo;
    private int duracion;
    private Disco disco;

    public Cancion() {}

    public Cancion(String titulo, int duracion, Disco disco) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.disco = disco;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public Disco getDisco() { return disco; }
    public void setDisco(Disco disco) { this.disco = disco; }
}