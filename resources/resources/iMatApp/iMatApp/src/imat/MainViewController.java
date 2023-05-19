
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

    private MyInfoController myInfoController;

    private ArrayList<view> previousView = new ArrayList<view>();
    private view currentView = null;
    @FXML
    public StackPane startStackPane;
    @FXML
    public StackPane shopStackPane;
    @FXML
    public StackPane myInfoStackPane;
    @FXML
    public StackPane shoppingCartStackPane;
    @FXML
    public AnchorPane toolbarAnchorPane;
    @FXML
    Label pathLabel;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    enum view {
        start,
        shop,
        profile,
        cart
    }

    public void initialize(URL url, ResourceBundle rb) {
        myInfoController = new MyInfoController(this);

        startStackPane.getChildren().add(new StartViewController(this));
        shopStackPane.getChildren().add(new ShopController(this));
        toolbarAnchorPane.getChildren().add(new ToolbarController(this));
        shoppingCartStackPane.getChildren().add(new ShoppingCartListController(this));
        myInfoStackPane.getChildren().add(myInfoController);


        switchView(view.start);

        String iMatDirectory = iMatDataHandler.imatDirectory();


    }
    private boolean canSwitchView()
    {
        if (currentView == null)//So why can this not just go under default? Be reasonable now.
        {
            return true;
        }

        switch(currentView)
        {
            case profile:
                if(myInfoController.checkFieldsChanged())
                {
                    return false;
                }
                break;
            default:
                break;
        }

        return true;
    }

    public void returnView()
    {
        if (canSwitchView())
        {
            if (previousView.size() > 0) {
                view target = previousView.get(previousView.size() - 1);
                previousView.remove(previousView.size() - 1);
                executeViewSwitch(target);
            }
        }
    }
    public void switchView(view target) {
        if (canSwitchView())
        {
            if (!(currentView == null)) {
                previousView.add(currentView);
            }
            executeViewSwitch(target);
        }

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
            case cart:
                shoppingCartStackPane.toFront();
                break;
            default:
                break;
        }
    }


}