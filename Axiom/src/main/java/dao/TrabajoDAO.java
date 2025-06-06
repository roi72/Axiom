package dao;

import model.Trabajo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class TrabajoDAO {

    // Posibles interacciones que se puedan hacer a base de datos.
    // Econtrar registro por id
    // Listar todos los registros
    // Encontrar registro por nombre
    // Crear registro
    // Actualizar registro
    // Eliminar registro
    // Cada uno de estos metodos estara disponible para el usuario o no en funcion de sus permisos

    public Trabajo findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Trabajo.class, id);
        }
    }

    public List<Trabajo> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Trabajo", Trabajo.class).list();
        }
    }

    public void save(Trabajo trabajo) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(trabajo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void update(Trabajo trabajo) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(trabajo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(Trabajo trabajo) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(trabajo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}

