module com.example.clientside {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires java.naming;

    opens com.example.clientside to javafx.fxml;
    opens com.example.clientside.view to javafx.fxml;
    exports com.example.clientside;
    exports com.example.clientside.viewmodel;
    exports com.example.clientside.view;
    exports com.example.clientside.Models;
    opens com.example.clientside.Models to javafx.fxml;

}