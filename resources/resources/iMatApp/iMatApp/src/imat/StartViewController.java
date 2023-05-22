package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

import java.io.IOException;

public class StartViewController extends SubViewController {

    @FXML
    public Button startShoppingButton;
    @FXML
    public Button rrofileShoppingButton;
    public StartViewController(MainViewController owner)
    {
        super("Startvy.fxml", owner);
    }

    public void shopPressed()
    {
        owner.switchView(MainViewController.view.shop);
    }

    public void profilePressed()
    {
        owner.switchView(MainViewController.view.profile);
    }

    public void ecoPressed()
    {
        owner.setStoreEco(true);
        owner.switchView(MainViewController.view.shop);
    }
}
