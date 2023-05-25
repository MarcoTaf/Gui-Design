package imat;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class OverrideButtonController extends Button {
    public OverrideButtonController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/" + "OverrideButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
