package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ToolbarController extends AnchorPane {
    @FXML
    public Button HomeButton;

    public ToolbarController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Toolbar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
