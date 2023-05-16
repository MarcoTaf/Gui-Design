package imat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartViewController extends AnchorPane {
    public StartViewController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Startvy.fxml"));



        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
