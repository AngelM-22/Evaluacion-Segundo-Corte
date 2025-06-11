package maajpixup.dao.impl;

import org.hibernate.Session;
import maajpixup.dao.CancionDao;
import maajpixup.hibernate.HibernateUtil;
import maajpixup.model.Cancion;

import java.util.List;

public class CancionDaoImpl implements CancionDao {
    private static CancionDao cancionDao;

    private CancionDaoImpl() {}

    public static CancionDao getInstance() {
        if (cancionDao == null) {
            cancionDao = new CancionDaoImpl();
        }
        return cancionDao;
    }

    @Override
    public List<Cancion> findAll() {
        Session session = HibernateUtil.getSession();
        List<Cancion> canciones = session.createQuery("From Cancion", Cancion.class).getResultList();
        session.close();
        return canciones;
    }

    @Override
    public boolean save(Cancion cancion) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(cancion);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Cancion cancion) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(cancion);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Cancion cancion) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(cancion);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Cancion findById(int id) {
        Session session = HibernateUtil.getSession();
        Cancion cancion = session.get(Cancion.class, id);
        session.close();
        return cancion;
    }
}