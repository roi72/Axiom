package dao;

import model.Evento;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class EventoDAO {

    // Posibles interacciones que se puedan hacer a base de datos.
    // Econtrar registro por id
    // Listar todos los registros
    // Encontrar registro por nombre
    // Crear registro
    // Actualizar registro
    // Eliminar registro
    // Cada uno de estos metodos estara disponible para el usuario o no en funcion de sus permisos

    public Evento findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Evento.class, id);
        }
    }

    public List<Evento> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Evento", Evento.class).list();
        }
    }

    public List<Evento> findByUsuario(Usuario usuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Evento> query = session.createQuery(
                    "FROM Evento WHERE usuario = :usuario", Evento.class);
            query.setParameter("usuario", usuario);
            return query.list();
        }
    }

    public List<Evento> findByEstado(String estado) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Evento> query = session.createQuery(
                    "FROM Evento WHERE estado = :estado", Evento.class);
            query.setParameter("estado", estado);
            return query.list();
        }
    }

    public void save(Evento evento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void update(Evento evento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void delete(Evento evento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(evento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}

