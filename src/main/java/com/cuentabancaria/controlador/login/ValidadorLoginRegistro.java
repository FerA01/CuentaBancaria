package com.cuentabancaria.controlador.login;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.encoder.Encoder;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
public class ValidadorLoginRegistro {
    private ControladorBaseDato baseDato;

    public ValidadorLoginRegistro(){ setBaseDato(null); }

    public ControladorBaseDato getBaseDato() { return baseDato; }
    public void setBaseDato(ControladorBaseDato baseDato) { this.baseDato = baseDato; }

    public boolean existeNombreUsuario(String nombreUsuario) throws SQLException {
        setBaseDato(new ControladorBaseDato());
        return getBaseDato().existeNombreUsuario(nombreUsuario);
    }
    public boolean existeNumeroCuit(String numeroCuit) throws SQLException{ return getBaseDato().existeNumeroCuit(numeroCuit); }

    public boolean nombreUsuarioVacio(TextField nombreUsuario){ return nombreUsuario.getText().isEmpty(); }
    public boolean contrasenaVacio(PasswordField contrasena){ return contrasena.getText().isEmpty(); }
    public boolean numeroCuitVacio(TextField numeroCuit){ return numeroCuit.getText().isEmpty(); }

    public boolean validarContrasena(String contrasena, String nombreUsuario) throws SQLException {
        Encoder encoder = new Encoder();
        String contrasenaEncriptada = getBaseDato().obtenerContrasena(nombreUsuario);
        String contrasenaDesencriptada = new String(encoder.decode(contrasenaEncriptada), StandardCharsets.UTF_8);
        return contrasenasIguales(contrasena, contrasenaDesencriptada);
    }

    private boolean contrasenasIguales(String contrasena1, String contrasena2){ return contrasena1.equals(contrasena2); }

    //Alertas.
    public static void alertaNombreUsuarioContrasenaVacio(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, rellene los campos!");
        alert.showAndWait();
    }
    public static void alertaUsuarioInexistente(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("No existe el nombre de usuario");
        alert.showAndWait();
    }
    public static void alertaUsuarioExistente(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("El nombre de usuario ya existe \n!Por Favor¡ elija otro nombre");
        alert.showAndWait();
    }
    public static void alertaUsuarioCreadoExitosamente(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Usuario creado exitosamente");
        alert.showAndWait();
    }
    public static void alertaUsuarioLogeadoExitosamente(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Usuario logeado exitosamente");
        alert.showAndWait();
    }
    public static void alertaContrasenaIncorrecta(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Contraseña incorrecta");
        alert.showAndWait();
    }
    public static void alertaNumeroCuitExistenteInvalido(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡ERROR! \nÉl número de cuit ya existe o es un número de cuit invalido.");
        alert.showAndWait();
    }
}
