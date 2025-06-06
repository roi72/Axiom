package controller;

import dao.TrabajoDAO;
import dao.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Rol;
import model.Trabajo;
import model.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;
import java.util.Objects;

public class RegistroController {

    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField edadField;
    @FXML private TextField usuarioField;
    @FXML private PasswordField contraseñaField;
    @FXML private ComboBox<Trabajo> trabajoComboBox;
    @FXML private Label mensajeLabel;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private TrabajoDAO trabajoDAO = new TrabajoDAO();

    @FXML
    public void initialize() {
        // Cargar trabajos disponibles en el ComboBox
        List<Trabajo> trabajos = trabajoDAO.findAll();
        ObservableList<Trabajo> opciones = FXCollections.observableArrayList(trabajos);
        trabajoComboBox.setItems(opciones);
    }

    @FXML
    private void handleRegistro(ActionEvent event) {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String edadStr = edadField.getText();
        String nombreUsuario = usuarioField.getText();
        String contraseña = contraseñaField.getText();
        Trabajo trabajo = trabajoComboBox.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || edadStr.isEmpty() || nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setText("Por favor, completa todos los campos obligatorios.");
            return;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Rol rolNoPrivilegiado = session.createQuery("FROM Rol WHERE privilegiado = false", Rol.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (rolNoPrivilegiado == null) {
                mensajeLabel.setText("Error: no existe un rol sin privilegios.");
                return;
            }

            Usuario nuevo = new Usuario();
            nuevo.setNombre(nombre);
            nuevo.setApellido(apellido);
            nuevo.setEdad(Integer.parseInt(edadStr));
            nuevo.setNombre_usuario(nombreUsuario);
            nuevo.setContraseña(contraseña); // Aplica hash si lo implementas
            nuevo.setRol(rolNoPrivilegiado);
            nuevo.setTrabajo(trabajo);
            nuevo.setNave(rolNoPrivilegiado.getUsuarios().get(0).getNave()); // Asignar nave por defecto

            usuarioDAO.save(nuevo);

            mensajeLabel.setText("Registro exitoso.");
            limpiarCampos();

        } catch (Exception e) {
            mensajeLabel.setText("Error al registrar el usuario.");
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        nombreField.clear();
        apellidoField.clear();
        edadField.clear();
        usuarioField.clear();
        contraseñaField.clear();
        trabajoComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void irALogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login.fxml")));
            Stage stage = (Stage) nombreField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            mensajeLabel.setText("Error al cambiar a login.");
            e.printStackTrace();
        }
    }
}

