module br.com.felipe.AppPonto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    opens br.com.felipe.AppPonto;
    exports br.com.felipe.AppPonto;


}