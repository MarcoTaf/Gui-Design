package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartViewController extends AnchorPane {

    @FXML
    public Button startShoppingButton;
    public StartViewController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Startvy.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
