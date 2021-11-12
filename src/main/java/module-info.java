module com.cuentabancaria.cuentabancaria {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cuentabancaria to javafx.fxml;
    exports com.cuentabancaria;
    exports com.cuentabancaria.controlador;
    opens com.cuentabancaria.controlador to javafx.fxml;
}