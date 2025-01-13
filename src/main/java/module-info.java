module br.com.felipe.AppPonto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires mysql.connector.j;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens br.com.felipe.AppPonto;
    exports br.com.felipe.AppPonto;


}