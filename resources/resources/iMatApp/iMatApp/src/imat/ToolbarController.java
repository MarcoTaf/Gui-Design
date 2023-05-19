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
    public Button ToolbarHome;
    @FXML
    public Button ToolbarProfile;
    @FXML
    public Button ToolbarLogo;

    public ToolbarController(MainViewController owner)
    {
        super("Toolbar.fxml", owner);
    }

    public void homePressed() {
        owner.switchView(MainViewController.view.start);
    }

    public void profilePressed() {
        owner.switchView(MainViewController.view.profile);
    }

    public void returnPressed()
    {
        owner.returnView();
    }
    public void cartPressed(){owner.switchView(MainViewController.view.cart);}
}
