package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
            return new MetadataSources(sr).buildMetadata().buildSessionFactory();

        } catch (Exception e) {
            System.err.println("Error al crear la SessionFactory: " + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Cerar conexiones
        getSessionFactory().close();
    }
}


