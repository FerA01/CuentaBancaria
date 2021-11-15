module com.cuentabancaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.testng;

    exports com.cuentabancaria.controlador;
    exports com.cuentabancaria.vista;
    opens com.cuentabancaria.controlador to javafx.fxml;
    opens com.cuentabancaria.vista to javafx.fxml;
}