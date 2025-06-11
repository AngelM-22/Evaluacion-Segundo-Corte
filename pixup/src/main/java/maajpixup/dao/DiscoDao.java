package maajpixup.dao;

import maajpixup.model.Disco;
import java.util.List;

public interface DiscoDao {
    List<Disco> findAll();
    boolean save(Disco disco);
    boolean update(Disco disco);
    boolean delete(Disco disco);
    Disco findById(int id);
}
