package dao;

import model.Nave;
import model.Recurso;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class RecursoDAO {

    // Posibles interacciones que se puedan hacer a base de datos.
    // Econtrar registro por id
    // Listar todos los registros
    // Encontrar registro por nombre
    // Crear registro
    // Actualizar registro
    // Eliminar registro
    // Cada uno de estos metodos estara disponible para el usuario o no en funcion de sus permisos

    public Recurso findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Recurso.class, id);
        }
    }

    public List<Recurso> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Recurso", Recurso.class).list();
        }
    }

    public List<Recurso> findByTipo(String tipo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Recurso> query = session.createQuery(
                    "FROM Recurso WHERE tipo = :tipo", Recurso.class);
            query.setParameter("tipo", tipo);
            return query.list();
        }
    }

    public List<Recurso> findByNave(Nave nave) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Recurso> query = session.createQuery(
                    "FROM Recurso WHERE nave = :nave", Recurso.class);
            query.setParameter("nave", nave);
            return query.list();
        }
    }

    public void save(Recurso recurso) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(recurso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void update(Recurso recurso) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(recurso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void delete(Recurso recurso) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(recurso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}

