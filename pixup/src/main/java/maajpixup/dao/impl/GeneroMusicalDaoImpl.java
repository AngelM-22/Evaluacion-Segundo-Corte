package maajpixup.dao.impl;

import org.hibernate.Session;
import maajpixup.dao.GeneroMusicalDao;
import maajpixup.hibernate.HibernateUtil;
import maajpixup.model.GeneroMusical;

import java.util.List;

public class GeneroMusicalDaoImpl implements GeneroMusicalDao {
    private static GeneroMusicalDao generoMusicalDao;

    private GeneroMusicalDaoImpl() {}

    public static GeneroMusicalDao getInstance() {
        if (generoMusicalDao == null) {
            generoMusicalDao = new GeneroMusicalDaoImpl();
        }
        return generoMusicalDao;
    }

    @Override
    public List<GeneroMusical> findAll() {
        Session session = HibernateUtil.getSession();
        List<GeneroMusical> generos = session.createQuery("From GeneroMusical", GeneroMusical.class).getResultList();
        session.close();
        return generos;
    }

    @Override
    public boolean save(GeneroMusical generoMusical) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(generoMusical);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(GeneroMusical generoMusical) {
        return false;
    }

    @Override
    public boolean delete(GeneroMusical generoMusical) {
        return false;
    }

    @Override
    public GeneroMusical findById(int id) {
        return null;
    }
}
