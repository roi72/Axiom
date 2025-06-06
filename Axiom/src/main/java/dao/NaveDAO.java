package dao;

import model.Nave;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class NaveDAO {

    // Posibles interacciones que se puedan hacer a base de datos.
    // Econtrar registro por id
    // Listar todos los registros
    // Encontrar registro por nombre
    // Crear registro
    // Actualizar registro
    // Eliminar registro
    // Cada uno de estos metodos estara disponible para el usuario o no en funcion de sus permisos

    public Nave findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Nave.class, id);
        }
    }

    public List<Nave> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Nave", Nave.class).list();
        }
    }

    public void save(Nave nave) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(nave);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void update(Nave nave) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(nave);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(Nave nave) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(nave);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}

