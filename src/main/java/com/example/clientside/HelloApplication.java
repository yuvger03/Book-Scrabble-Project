package com.example.clientside;

import com.example.clientside.Models.MenuModel;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        MenuModel mm = new MenuModel();
//        MenuViewModel menuVM = new MenuViewModel();
        //mm.addListener((InvalidationListener) menuVM);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/MenuView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}