
package imat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;



public class MainViewController implements Initializable {

    private ArrayList<view> previousView = new ArrayList<view>();
    private view currentView = null;
    @FXML
    public StackPane startStackPane;
    @FXML
    public StackPane shopStackPane;
    @FXML
    public StackPane myInfoStackPane;
    @FXML
    public AnchorPane toolbarAnchorPane;
    @FXML
    Label pathLabel;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    enum view {
        start,
        shop,
        profile
    }

    public void initialize(URL url, ResourceBundle rb) {


        startStackPane.getChildren().add(new StartViewController(this));
        shopStackPane.getChildren().add(new ShopController(this));
        toolbarAnchorPane.getChildren().add(new ToolbarController(this));
        myInfoStackPane.getChildren().add(new MyInfoController(this));


        switchView(view.start);

        String iMatDirectory = iMatDataHandler.imatDirectory();


    }

    public void returnView()
    {
        if (previousView.size() > 0)
        {
            view target = previousView.get(previousView.size() - 1);
            previousView.remove(previousView.size() - 1);
            executeViewSwitch(target);
        }
    }
    public void switchView(view target)
    {
        if (!(currentView == null))
        {
            previousView.add(currentView);
        }
        executeViewSwitch(target);
    }
    private void executeViewSwitch(view target)
    {
        currentView = target;
        switch (target)
        {
            case start:
                startStackPane.toFront();
                break;
            case shop:
                shopStackPane.toFront();
                break;
            case profile:
                myInfoStackPane.toFront();
                break;
            default:
                break;
        }
    }


}