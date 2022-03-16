package com.cuentabancaria.controlador.login;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.encoder.Encoder;
import com.cuentabancaria.modelo.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class ControladorRegistrarse implements Initializable {
    @FXML private TextField numeroCuit;
    @FXML private Button botonRegistrarse;
    @FXML private TextField nombreUsuario;
    @FXML private PasswordField contrasena;
    private ControladorBaseDato baseDato;
    private ValidadorLoginRegistro validadorLoginRegistro;
    private ControladorLogin controladorLogin;
    private Stage ventana;
    private Stage ventanaLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public Button getBotonRegistrarse() { return botonRegistrarse; }
    public void setBotonRegistrarse(Button botonRegistrarse) { this.botonRegistrarse = botonRegistrarse; }
    public TextField getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(TextField nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public PasswordField getContrasena() { return contrasena; }
    public void setContrasena(PasswordField contrasena) { this.contrasena = contrasena; }
    public TextField getNumeroCuit() { return numeroCuit; }
    public void setNumeroCuit(TextField numeroCuit) { this.numeroCuit = numeroCuit; }
    public ControladorBaseDato getBaseDato() { return baseDato; }
    public void setBaseDato(ControladorBaseDato baseDato) { this.baseDato = baseDato; }
    public ValidadorLoginRegistro getValidadorLoginRegistro() { return validadorLoginRegistro; }
    public void setValidadorLoginRegistro(ValidadorLoginRegistro validadorLoginRegistro) { this.validadorLoginRegistro = validadorLoginRegistro; }
    public ControladorLogin getControladorLogin() { return controladorLogin; }
    public void setControladorLogin(ControladorLogin controladorLogin) { this.controladorLogin = controladorLogin; }
    public Stage getVentana() { return ventana; }
    public void setVentana(Stage ventana) { this.ventana = ventana; }
    public Stage getVentanaLogin() { return ventanaLogin; }
    public void setVentanaLogin(Stage ventanaLogin) { this.ventanaLogin = ventanaLogin; }

    @FXML
    private void accionRegistrarse() throws SQLException {
        setValidadorLoginRegistro(new ValidadorLoginRegistro());
        setBaseDato(new ControladorBaseDato());
        Encoder encoder = new Encoder();
        Usuario usuario = null;
        if (getValidadorLoginRegistro().nombreUsuarioVacio(getNombreUsuario()) || getValidadorLoginRegistro().contrasenaVacio(getContrasena())){
            ValidadorLoginRegistro.alertaNombreUsuarioContrasenaVacio();
        }else {
            usuario = new Usuario(getNombreUsuario().getText(), getContrasena().getText());
            if (getValidadorLoginRegistro().existeNombreUsuario(usuario.getNombreUsuario())){
                ValidadorLoginRegistro.alertaUsuarioExistente();
            }else {
                byte[] encriptar = encoder.encode(usuario.getContrasena());
                String contrasenaEncriptada = new String(encriptar, StandardCharsets.UTF_8);
                usuario.setContrasena(contrasenaEncriptada);
                getBaseDato().insertarUsuario(usuario);
                ValidadorLoginRegistro.alertaUsuarioCreadoExitosamente();
                getVentanaLogin().show();
                getVentana().close();
            }
        }
    }
    public void ocultarVentanaLogin(){ getVentanaLogin().hide(); }
    public void mostrarVentanaLogin(){ getVentanaLogin().show(); }
}
