package com.example.RunClasses;

import com.example.clientside.Models.IModel;
import com.example.clientside.Models.MenuModel;
import com.example.clientside.StartHost;
import com.example.clientside.view.menuViewController;
import com.example.clientside.viewmodel.IViewModel;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartGuest extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        IModel mm = new MenuModel();
        IViewModel menuVM = new MenuViewModel(mm);
        ((MenuModel)mm).addObserver((MenuViewModel)menuVM);
        FXMLLoader fxmlLoader = new FXMLLoader(StartHost.class.getResource("Views/MenuViewGuest.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setScene(scene);
        stage.show();
        menuViewController mvc = fxmlLoader.getController();
        mvc.setMenuVM((MenuViewModel)menuVM);
        ((MenuViewModel)menuVM).addObserver(mvc);


    }

    public static void main(String[] args) {
        launch();
    }
}