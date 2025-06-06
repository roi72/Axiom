import dao.UsuarioDAO;
import model.Nave;
import model.Rol;
import model.Trabajo;
import model.Usuario;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {
    public static void main(String[] args) {

        //Abrir sesion para insertar a base de datos
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Inserts de prueba para probar las consultas a base de datos
            // y comprobar la correcta creacion de la base de datos

            // Crear NAVE
            Nave nave = new Nave();
            nave.setNombre("Nave Alpha");
            nave.setCapacidad(100);
            nave.setVelocidad(25000);
            nave.setTamaño(150.0);
            session.persist(nave);

            // Crear ROL
            Rol rol = new Rol();
            rol.setNombre("Capitán");
            rol.setPrivilegiado(true);
            session.persist(rol);

            // Crear TRABAJO
            Trabajo trabajo = new Trabajo();
            trabajo.setNombre("Ingeniero");
            trabajo.setDescripcion("Responsable del mantenimiento de sistemas.");
            session.persist(trabajo);

            // Crear USUARIO
            Usuario usuario = new Usuario();
            usuario.setNombre("Juan");
            usuario.setApellido("Pérez");
            usuario.setEdad(35);
            usuario.setNombre_usuario("admin");
            usuario.setContraseña("1234"); // En producción: usa hash seguro
            usuario.setNave(nave);
            usuario.setRol(rol);
            usuario.setTrabajo(trabajo);
            session.persist(usuario);

            // Guardar en la base de datos
            transaction.commit();

        } catch (Exception e) {
            System.err.println("Error al insertar:");
            e.printStackTrace();
        }

        // -------------------------------------------

        // Probar consulta con UsuarioDAO
        try {
            // Llamar al metodo de UsuarioDAO encargado de buscar usuario por nombre
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.findByNombreUsuario("admin");

            if (usuario != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + usuario.getId_usuario());
                System.out.println("Nombre: " + usuario.getNombre() + " " + usuario.getApellido());
                System.out.println("Rol: " + usuario.getRol().getNombre());
                System.out.println("Trabajo: " + usuario.getTrabajo().getNombre());
                System.out.println("Nave: " + usuario.getNave().getNombre());
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Error durante la búsqueda:");
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
