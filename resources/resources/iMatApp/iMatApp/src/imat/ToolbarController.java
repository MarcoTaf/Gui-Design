package imat;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ToolbarController extends SubViewController {
    private MainViewController parent;
    @FXML
    public Button HomeButton;

    public ToolbarController(MainViewController owner)
    {
        super("Toolbar.fxml", owner);
    }
}
