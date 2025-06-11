package maajpixup.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Catalogo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Catalogo() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
