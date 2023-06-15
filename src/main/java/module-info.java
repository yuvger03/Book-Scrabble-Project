module com.example.clientside {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.clientside to javafx.fxml;
    opens com.example.clientside.view to javafx.fxml;
    exports com.example.clientside;
    exports com.example.clientside.viewmodel;
    exports com.example.clientside.view;

}