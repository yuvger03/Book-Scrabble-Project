package com.example.RunClasses;

import com.example.clientside.Models.MenuModel;
import com.example.clientside.view.menuViewController;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGuest extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MenuModel mm = new MenuModel();
        MenuViewModel menuVM = new MenuViewModel(mm);
        mm.addObserver(menuVM);
        FXMLLoader fxmlLoader = new FXMLLoader(StartHost.class.getResource("Views/MenuViewGuest.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        menuViewController mvc = fxmlLoader.getController();
        mvc.setMenuVM(menuVM);
        menuVM.addObserver(mvc);
    }

    public static void main(String[] args) {
        launch();
    }
}