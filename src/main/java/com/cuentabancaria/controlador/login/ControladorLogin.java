package com.cuentabancaria.controlador.login;
import com.cuentabancaria.modelo.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class ControladorLogin {
    @FXML private TextField nombreUsuario;
    @FXML private TextField contrasena;
    @FXML private Button iniciarSesion;

    public TextField getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(TextField nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public TextField getContrasena() { return contrasena; }
    public void setContrasena(TextField contrasena) { this.contrasena = contrasena; }
    public Button getIniciarSesion() { return iniciarSesion; }
    public void setIniciarSesion(Button iniciarSesion) { this.iniciarSesion = iniciarSesion; }

    @FXML
    public void accionIniciarSesion(){
        String nombreUsuario = getNombreUsuario().getText();
        String contrasena = getContrasena().getText();
        Usuario usuario = null;
        if ( nombreUsuario.isEmpty() || contrasena.isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Por favor, rellene los campos");
            alert.showAndWait();
        }else {
            usuario = new Usuario(nombreUsuario, contrasena);
        }
        System.out.println(usuario);
    }
}
