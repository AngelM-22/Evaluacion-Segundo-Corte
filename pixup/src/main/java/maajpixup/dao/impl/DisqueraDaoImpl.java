package maajpixup.dao.impl;

import org.hibernate.Session;
import maajpixup.dao.DisqueraDao;
import maajpixup.hibernate.HibernateUtil;
import maajpixup.model.Disquera;

import java.util.List;

public class DisqueraDaoImpl implements DisqueraDao {
    private static DisqueraDao disqueraDao;

    private DisqueraDaoImpl() {}

    public static DisqueraDao getInstance() {
        if (disqueraDao == null) {
            disqueraDao = new DisqueraDaoImpl();
        }
        return disqueraDao;
    }

    @Override
    public List<Disquera> findAll() {
        Session session = HibernateUtil.getSession();
        List<Disquera> disqueras = session.createQuery("From Disquera", Disquera.class).getResultList();
        session.close();
        return disqueras;
    }

    @Override
    public boolean save(Disquera disquera) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(disquera);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Disquera disquera) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(disquera);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Disquera disquera) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(disquera);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Disquera findById(int id) {
        Session session = HibernateUtil.getSession();
        Disquera disquera = session.get(Disquera.class, id);
        session.close();
        return disquera;
    }
}