module com.cuentabancaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.testng;
    requires  java.sql;
    requires  mysql.connector.java;
    requires org.apache.commons.codec;

    opens com.cuentabancaria.vista.cuentaBancaria to javafx.fxml ;
    opens com.cuentabancaria.vista.login.registrarse to javafx.fxml;
    opens com.cuentabancaria.controlador.test to javafx.fxml;
    opens com.cuentabancaria.controlador.cuentaBancaria to javafx.fxml;
    opens com.cuentabancaria.controlador to javafx.fxml;
    opens com.cuentabancaria.vista to javafx.fxml;
    opens com.cuentabancaria.controlador.login to javafx.fxml;
    opens com.cuentabancaria.vista.titular to javafx.fxml;
    opens  com.cuentabancaria.vista.buscar to javafx.fxml;
    opens com.cuentabancaria.controlador.buscar to javafx.fxml;
    opens com.cuentabancaria.modelo.titular to javafx.base;
    opens com.cuentabancaria.controlador.titular to javafx.fxml;
    opens com.cuentabancaria.modelo.cuentas to javafx.base;
    opens test to javafx.base ;
    exports com.cuentabancaria.vista to javafx.graphics;
    exports com.cuentabancaria.controlador.test to javafx.fxml;
    exports com.cuentabancaria.controlador.buscar to javafx.graphics;
    exports com.cuentabancaria.controlador to javafx.fxml;
    exports com.cuentabancaria.controlador.titular to javafx.fxml;
    exports com.cuentabancaria.controlador.cuentaBancaria;
    exports test;
}