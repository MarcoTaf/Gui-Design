package imat;

import com.sun.tools.javac.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    @FXML
    public TextField searchTextField;

    public ToolbarController(MainViewController owner)
    {
        super("Toolbar.fxml", owner);

        searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
          @Override
          public void handle(KeyEvent event) {

              if(event.getCode().equals(
                      KeyCode.ENTER)
              ) {
                owner.switchView(MainViewController.view.shop, searchTextField.getText());
              }
          }
        });
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
    public void cartPressed()
    {
        if (owner.getCurrentViewType() == MainViewController.view.cart)
        {
            owner.returnView();
        }
        else {
            owner.switchView(MainViewController.view.cart);
        }
    }
}
