import com.example.clientside.Models.MenuModel;
import com.example.clientside.view.menuViewController;
import com.example.clientside.viewmodel.MenuViewModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import java.util.concurrent.TimeoutException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuViewDataBindingTest extends ApplicationTest {

    private menuViewController viewController;
    private MenuViewModel viewModel;

    @BeforeEach
    public void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(HelloApplication.class);
    }

    @AfterEach
    public void tearDown() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/MenuView.fxml"));
        Parent root = fxmlLoader.load();
        viewController = fxmlLoader.getController();
        viewModel = new MenuViewModel(new MenuModel());
        viewController.setMenuVM(viewModel);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testDataBinding() {
        // Simulate user input in the name TextField
        Platform.runLater(() -> {
            TextField nameTextField = lookup("#name").query();
            nameTextField.setText("John Doe");
        });

        // Assert that the ViewModel's name property is updated correctly
        assertEquals("John Doe", viewModel.getName());
    }
}
