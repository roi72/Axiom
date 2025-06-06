package dao;

import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class UsuarioDAO {

    // Posibles interacciones que se puedan hacer a base de datos.
    // Econtrar registro por id
    // Listar todos los registros
    // Encontrar registro por nombre
    // Crear registro
    // Actualizar registro
    // Eliminar registro
    // Forma de autenticar cada usuario
    // Cada uno de estos metodos estara disponible para el usuario o no en funcion de sus permisos

    public Usuario findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Usuario.class, id);
        }
    }

    public Usuario findByNombreUsuario(String nombreUsuario) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery("FROM Usuario WHERE nombre_usuario = :nombre", Usuario.class);
            query.setParameter("nombre", nombreUsuario);
            return query.uniqueResult();
        }
    }

    public List<Usuario> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Usuario", Usuario.class).list();
        }
    }

    public void save(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void update(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void delete(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public Usuario login(String nombreUsuario, String contraseñaHash) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> query = session.createQuery(
                    "FROM Usuario WHERE nombre_usuario = :nombre AND contraseña = :clave", Usuario.class);
            query.setParameter("nombre", nombreUsuario);
            query.setParameter("clave", contraseñaHash);
            return query.uniqueResult();
        }
    }
}
