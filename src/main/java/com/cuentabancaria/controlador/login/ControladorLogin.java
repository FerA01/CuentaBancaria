package com.cuentabancaria.controlador.login;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.controlador.ControladorCuentaBancaria;
import com.cuentabancaria.controlador.cuentaBancaria.ControladorCuentaBancaria2;
import com.cuentabancaria.encoder.Encoder;
import com.cuentabancaria.modelo.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControladorLogin implements Initializable {
    @FXML private Text botonRegistrarse;
    @FXML private TextField nombreUsuario;
    @FXML private PasswordField contrasena;
    @FXML private Button iniciarSesion;
    private ControladorBaseDato baseDato;
    private ValidadorLoginRegistro validadorLoginRegistro;
    private Stage ventanaLogin;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

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
    public Stage getVentanaLogin() { return ventanaLogin; }
    public void setVentanaLogin(Stage ventanaLogin) { this.ventanaLogin = ventanaLogin; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @FXML
    public void accionIniciarSesion() throws SQLException, IOException {
        setBaseDato(new ControladorBaseDato());
        setValidadorLoginRegistro(new ValidadorLoginRegistro());
        Usuario usuario = null;
        if ( getValidadorLoginRegistro().nombreUsuarioVacio(getNombreUsuario()) || getValidadorLoginRegistro().contrasenaVacio(getContrasena())){
            ValidadorLoginRegistro.alertaNombreUsuarioContrasenaVacio();
        }else {
            Usuario nuevo = null;
            setUsuario(new Usuario(getNombreUsuario().getText(), getContrasena().getText()));
            if(getValidadorLoginRegistro().existeNombreUsuario(getUsuario().getNombreUsuario())){
                if(getValidadorLoginRegistro().validarContrasena(getContrasena().getText(), getUsuario().getNombreUsuario())){
                    nuevo = getBaseDato().obtenerUsuario(getUsuario().getNombreUsuario());
                    ValidadorLoginRegistro.alertaUsuarioLogeadoExitosamente();
                    //cargar ventana principal
                    abrirVentanaPrincipal();
                    getVentanaLogin().hide();
                }else{ ValidadorLoginRegistro.alertaContrasenaIncorrecta(); }
            }else{ ValidadorLoginRegistro.alertaUsuarioInexistente(); }
            if (nuevo != null){ System.out.println(nuevo); }
        }
    }
    @FXML
    private void accionRegistrarse() throws IOException { cargarVentanaRegistrarse(); }
    private void cargarVentanaRegistrarse() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/login/registrarse/Registrarse.fxml"));
            Parent parent = fxmlLoader.load();
            ControladorRegistrarse controladorRegistrarse = fxmlLoader.getController();
            controladorRegistrarse.setControladorLogin(this);
            controladorRegistrarse.setVentanaLogin(getVentanaLogin());
            controladorRegistrarse.ocultarVentanaLogin();
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
    }
    public void abrirVentanaPrincipal() throws IOException, SQLException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/cuentaBancaria/CuentaBancaria.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage ventana = new Stage();
            ControladorCuentaBancaria2 controlador = loader.getController();
            setUsuario(getBaseDato().obtenerUsuario(getUsuario().getNombreUsuario()));
            controlador.setUsuario(getUsuario());
            controlador.cambiarTextoEtiqueta(controlador.getEtiquetaNombreUsuario(), controlador.obtenerNombreUsuario());
            controlador.cambiarTextoEtiqueta(controlador.getEtiquetaNumeroCbu(), controlador.obtenerNumeroCuitUsuario());
            controlador.ocultarBotonCrearTitular();
            controlador.setControladorLogin(this);
            ventana.setTitle("Principal");
            ventana.setResizable(false);
            ventana.setScene(scene);
            ventana.showAndWait();
    }
}

/*  -Si me logueo, abro la ventana principal con los datos del usuario cargados en la base
    -Al hacer una operación, el usuario debe tener obligatoriamente un número de cuit, caso contrario debo
    generar una excepción para que el usuario llene datos como titular persona u organización.
    -Una vez creado el titular debo actualizar el numero de cuit del usuario con el número de cuit creado.



 */
