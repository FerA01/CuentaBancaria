package com.cuentabancaria.controlador.login;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.controlador.ControladorCuentaBancaria;
import com.cuentabancaria.encoder.Encoder;
import com.cuentabancaria.modelo.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class ControladorLogin {
    @FXML private Text botonRegistrarse;
    @FXML private TextField nombreUsuario;
    @FXML private PasswordField contrasena;
    @FXML private Button iniciarSesion;
    private ControladorBaseDato baseDato;
    private ValidadorLoginRegistro validadorLoginRegistro;

    public TextField getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(TextField nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public PasswordField getContrasena() { return contrasena; }
    public void setContrasena(PasswordField contrasena) { this.contrasena = contrasena; }
    public Button getIniciarSesion() { return iniciarSesion; }
    public void setIniciarSesion(Button iniciarSesion) { this.iniciarSesion = iniciarSesion; }
    public ControladorBaseDato getBaseDato() { return baseDato; }
    public void setBaseDato(ControladorBaseDato baseDato) { this.baseDato = baseDato; }
    public Text getBotonRegistrarse() { return botonRegistrarse; }
    public void setBotonRegistrarse(Text botonRegistrarse) { this.botonRegistrarse = botonRegistrarse; }
    public ValidadorLoginRegistro getValidadorLoginRegistro() { return validadorLoginRegistro; }
    public void setValidadorLoginRegistro(ValidadorLoginRegistro validadorLoginRegistro) { this.validadorLoginRegistro = validadorLoginRegistro; }

    @FXML
    public void accionIniciarSesion() throws SQLException {
        setBaseDato(new ControladorBaseDato());
        setValidadorLoginRegistro(new ValidadorLoginRegistro());
        Usuario usuario = null;
        if ( getValidadorLoginRegistro().nombreUsuarioVacio(getNombreUsuario()) || getValidadorLoginRegistro().contrasenaVacio(getContrasena())){
            getValidadorLoginRegistro().alertaNombreUsuarioContrasenaVacio();
        }else {
            Usuario nuevo = null;
            usuario = new Usuario(getNombreUsuario().getText(), getContrasena().getText());
            if(getValidadorLoginRegistro().existeNombreUsuario(usuario.getNombreUsuario())){
                if(getValidadorLoginRegistro().validarContrasena(getContrasena().getText(), usuario.getNombreUsuario())){
                    nuevo = getBaseDato().obtenerUsuario(usuario.getNombreUsuario());
                }else{
                    getValidadorLoginRegistro().alertaContrasenaIncorrecta();
                }
            }else{
                getValidadorLoginRegistro().alertaUsuarioInexistente();
            }
            if (nuevo != null){
                System.out.println(nuevo);
            }
        }
        
    }
    @FXML
    private void accionRegistrarse() throws IOException {
       cargarVentanaRegistrarse();
    }

    private void cargarVentanaRegistrarse(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/login/registrarse/Registrarse.fxml"));
            Parent parent = fxmlLoader.load();
            ControladorRegistrarse controladorRegistrarse = fxmlLoader.getController();
            controladorRegistrarse.setControladorLogin(this);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            controladorRegistrarse.setVentana(stage);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrarse");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add( new Image(
                    getClass().getResourceAsStream( "/imagenes/icono.png" )));
            stage.showAndWait();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
