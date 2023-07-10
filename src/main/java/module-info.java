module com.example.clientside {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires com.fasterxml.jackson.databind;

    opens com.example.clientside.view to javafx.fxml;
    exports com.example.clientside.viewmodel;
    exports com.example.clientside.view;
    exports com.example.clientside.Models;
    opens com.example.clientside.Models to javafx.fxml;
    exports com.example.RunClasses;
    opens com.example.RunClasses to javafx.fxml;
    exports com.example.serverSide;
    opens com.example.serverSide to javafx.fxml;
    exports com.example.clientside;
    opens com.example.clientside to javafx.fxml;
}