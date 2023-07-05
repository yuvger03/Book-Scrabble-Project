module com.example.clientside {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires java.naming;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires com.fasterxml.jackson.databind;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;
    requires org.junit.jupiter.migrationsupport;
    requires org.junit.platform.commons;
    requires org.junit.platform.engine;
    requires org.junit.platform.launcher;

    opens com.example.clientside to javafx.fxml;
    opens com.example.clientside.view to javafx.fxml;
    exports com.example.clientside;
    exports com.example.clientside.viewmodel;
    exports com.example.clientside.view;
    exports com.example.clientside.Models;
    opens com.example.clientside.Models to javafx.fxml;
    exports com.example.Tests;
    opens com.example.Tests to javafx.fxml;

}