module com.cuentabancaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.testng;
    requires  java.sql;
    requires  mysql.connector.java;

    opens test;
    opens com.cuentabancaria.controlador.test to javafx.fxml;
    opens com.cuentabancaria.controlador to javafx.fxml;
    opens com.cuentabancaria.vista to javafx.fxml;
    opens com.cuentabancaria.controlador.login to javafx.fxml;
    opens com.cuentabancaria.vista.titular to javafx.fxml;
    opens  com.cuentabancaria.vista.buscar to javafx.fxml;
    opens com.cuentabancaria.controlador.buscar to javafx.fxml;
    opens com.cuentabancaria.modelo.titular to javafx.base;
    exports com.cuentabancaria.vista to javafx.graphics;
    exports com.cuentabancaria.controlador.test to javafx.fxml;

}