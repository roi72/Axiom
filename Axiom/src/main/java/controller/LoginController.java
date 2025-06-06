package controller;

import dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Usuario;

import java.util.Objects;

public class LoginController {

    @FXML private TextField usuarioField;
    @FXML private PasswordField contraseñaField;
    @FXML private Label mensajeLabel;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void handleLogin(ActionEvent event) {
        String nombreUsuario = usuarioField.getText();
        String contraseña = contraseñaField.getText();

        if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            mensajeLabel.setText("Todos los campos son obligatorios.");
            return;
        }

        Usuario usuario = usuarioDAO.login(nombreUsuario, contraseña); // Asegúrate de usar hash si aplicas seguridad

        if (usuario != null) {
            mensajeLabel.setText("Login exitoso.");

            // Redirigir al dashboard según el rol (por ahora: placeholder)
            System.out.println("Usuario: " + usuario.getNombre_usuario() + " | Rol: " + usuario.getRol().getNombre());

            // Aquí cargarías una nueva escena con FXMLLoader si deseas

        } else {
            mensajeLabel.setText("Credenciales inválidas.");
        }
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/registro.fxml")));
            Stage stage = (Stage) usuarioField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            mensajeLabel.setText("Error al cambiar de escena.");
            e.printStackTrace();
        }
    }
}

