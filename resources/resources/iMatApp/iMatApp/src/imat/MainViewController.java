
package imat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;



public class MainViewController implements Initializable {
    @FXML
    public StackPane startStackPane;
    @FXML
    public StackPane shopStackPane;
    @FXML
    public AnchorPane toolbarAnchorPane;
    @FXML
    Label pathLabel;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    enum view {
        start,
        shop
    }

    public void initialize(URL url, ResourceBundle rb) {


        startStackPane.getChildren().add(new StartViewController(this));
        shopStackPane.getChildren().add(new ShopController(this));
        toolbarAnchorPane.getChildren().add(new ToolbarController(this));


        String iMatDirectory = iMatDataHandler.imatDirectory();


    }

    public void switchView(view target)
    {
        switch (target)
        {
            case start:
                startStackPane.toFront();
                break;
            case shop:
                shopStackPane.toFront();
                break;
            default:
                break;
        }
    }


}