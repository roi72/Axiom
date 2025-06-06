package util;

import dao.NaveDAO;
import dao.RolDAO;
import dao.TrabajoDAO;
import model.Nave;
import model.Rol;
import model.Trabajo;

import java.util.List;

public class DatosIniciales {

    // Esta clase sirve para cargar la informacion necesaria para que se pueda empezar a usar el programa

    public static void cargar() {
        NaveDAO naveDAO = new NaveDAO();
        RolDAO rolDAO = new RolDAO();
        TrabajoDAO trabajoDAO = new TrabajoDAO();

        // Insertar nave por defecto si no hay ninguna
        // Esto llama al metodo encargado de hacer una consulta de todos los registros.
        // Si devuelve la lista vacia es que no hay ningun registro, por tanto, los inserta
        List<Nave> naves = naveDAO.findAll();
        if (naves.isEmpty()) {
            Nave nave = new Nave();
            nave.setNombre("Nave Principal");
            nave.setCapacidad(200);
            nave.setVelocidad(30000);
            nave.setTamaño(250.0);
            naveDAO.save(nave);
            System.out.println("Nave por defecto creada.");
        }

        // Insertar roles por defecto si no hay ninguno
        List<Rol> roles = rolDAO.findAll();
        if (roles.isEmpty()) {
            rolDAO.save(new Rol("Capitán", true));
            rolDAO.save(new Rol("Tripulante", true));
            rolDAO.save(new Rol("Trabajador", false));
            rolDAO.save(new Rol("Pasajero", false));
            System.out.println("Roles por defecto creados.");
        }

        // Insertar trabajos por defecto si no hay ninguno
        List<Trabajo> trabajos = trabajoDAO.findAll();
        if (trabajos.isEmpty()) {
            trabajoDAO.save(new Trabajo("Mecánico", "Repara sistemas mecánicos."));
            trabajoDAO.save(new Trabajo("Agricultor", "Cultiva alimentos."));
            trabajoDAO.save(new Trabajo("Médico", "Atiende a los pasajeros."));
            trabajoDAO.save(new Trabajo("Ingeniero", "Mantiene los sistemas de la nave."));
            System.out.println("Trabajos por defecto creados.");
        }
    }
}

