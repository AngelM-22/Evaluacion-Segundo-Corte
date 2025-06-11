package maajpixup.dao;

import maajpixup.model.GeneroMusical;
import java.util.List;

public interface GeneroMusicalDao {
    List<GeneroMusical> findAll();
    boolean save(GeneroMusical generoMusical);
    boolean update(GeneroMusical generoMusical);
    boolean delete(GeneroMusical generoMusical);
    GeneroMusical findById(int id);
}