module br.com.felipe.AppPonto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens br.com.felipe.AppPonto to javafx.fxml;
    exports br.com.felipe.AppPonto;
}