
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

    public void initialize(URL url, ResourceBundle rb) {


        startStackPane.getChildren().add(new StartViewController());
        shopStackPane.getChildren().add(new ShopController());
        toolbarAnchorPane.getChildren().add(new ToolbarController());


        String iMatDirectory = iMatDataHandler.imatDirectory();


    }

    private void loadViews()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartVy.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }


}