module com.cuentabancaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.testng;
    requires  java.sql;
    requires  mysql.connector.java;
    opens com.cuentabancaria.controlador to javafx.fxml;
    opens com.cuentabancaria.vista to javafx.fxml;
    opens com.cuentabancaria.controlador.login to javafx.fxml;
    opens com.cuentabancaria.vista.titular to javafx.fxml;
    exports com.cuentabancaria.vista to javafx.graphics;
}