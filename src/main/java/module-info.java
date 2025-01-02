module br.com.felipe.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens br.com.felipe.demo to javafx.fxml;
    exports br.com.felipe.demo;
}