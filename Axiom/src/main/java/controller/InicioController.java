package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.SceneManager;

public class InicioController {

    @FXML
    private void irALogin(ActionEvent event) {
        SceneManager.cambiarEscena("/view/login.fxml");
    }

    @FXML
    private void irARegistro(ActionEvent event) {
        SceneManager.cambiarEscena("/view/registro.fxml");
    }
}

