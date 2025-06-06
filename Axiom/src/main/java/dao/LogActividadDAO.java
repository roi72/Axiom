package dao;

import model.Evento;
import model.LogActividad;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class LogActividadDAO {

    // Posibles interacciones que se puedan hacer a base de datos.
    // Econtrar registro por id
    // Listar todos los registros
    // Encontrar registro por nombre
    // Crear registro
    // Actualizar registro
    // Eliminar registro
    // Cada uno de estos metodos estara disponible para el usuario o no en funcion de sus permisos

    public LogActividad findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LogActividad.class, id);
        }
    }

    public List<LogActividad> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM LogActividad", LogActividad.class).list();
        }
    }

    public List<LogActividad> findByUsuario(Usuario usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LogActividad> query = session.createQuery(
                    "FROM LogActividad WHERE usuario = :usuario", LogActividad.class);
            query.setParameter("usuario", usuario);
            return query.list();
        }
    }

    public List<LogActividad> findByEvento(Evento evento) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<LogActividad> query = session.createQuery(
                    "FROM LogActividad WHERE evento = :evento", LogActividad.class);
            query.setParameter("evento", evento);
            return query.list();
        }
    }

    public void save(LogActividad log) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(log);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void delete(LogActividad log) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(log);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}

