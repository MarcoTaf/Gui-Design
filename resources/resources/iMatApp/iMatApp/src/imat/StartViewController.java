package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartViewController extends SubViewController {

    @FXML
    public Button startShoppingButton;
    public StartViewController(MainViewController owner)
    {
        super("Startvy.fxml", owner);
    }

    public void shopPressed()
    {
        owner.switchView(MainViewController.view.shop);
    }
}
