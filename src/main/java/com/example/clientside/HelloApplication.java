package com.example.clientside;

import com.example.clientside.Models.MenuModel;
import com.example.clientside.view.menuViewController;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.application.Application;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observer;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        MenuModel mm = new MenuModel();
//        MenuViewModel menuVM = new MenuViewModel(mm);
//        mm.addObserver(menuVM);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Views/BoardView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
//        menuViewController mvc = fxmlLoader.getController();
//        mvc.setMenuVM(menuVM);
//        menuVM.addObserver(mvc);
    }

    public static void main(String[] args) {
        launch();
    }
}