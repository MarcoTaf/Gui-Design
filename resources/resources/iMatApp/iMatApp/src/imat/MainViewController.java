
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
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;


public class MainViewController implements Initializable {

    private ShopController shopController;
    private MyInfoController myInfoController;
    private ShoppingCartListController cartListController;
    private DetailViewController detailViewController;

    private ArrayList<CurrentViewInfo> previousView = new ArrayList<CurrentViewInfo>();
    private CurrentViewInfo currentView = null;
    @FXML
    public StackPane startStackPane;
    @FXML
    public StackPane shopStackPane;
    @FXML
    public StackPane myInfoStackPane;
    @FXML
    public StackPane shoppingCartStackPane;
    @FXML
    public StackPane detailStackPane;
    @FXML
    public AnchorPane toolbarAnchorPane;
    @FXML
    Label pathLabel;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    enum view {
        start,
        shop,
        profile,
        cart,
        detail
    }

    public void initialize(URL url, ResourceBundle rb) {
        shopController = new ShopController(this);
        myInfoController = new MyInfoController(this);
        cartListController = new ShoppingCartListController(this);
        detailViewController = new DetailViewController(this);

        startStackPane.getChildren().add(new StartViewController(this));
        shopStackPane.getChildren().add(shopController);
        toolbarAnchorPane.getChildren().add(new ToolbarController(this));
        shoppingCartStackPane.getChildren().add(cartListController);
        myInfoStackPane.getChildren().add(myInfoController);
        detailStackPane.getChildren().add(detailViewController);


        switchView(view.start);

        String iMatDirectory = iMatDataHandler.imatDirectory();


    }
    private boolean canSwitchView()
    {
        if (currentView == null)//So why can this not just go under default? Be reasonable now.
        {
            return true;
        }

        switch(currentView.targetView)
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

    public void returnAddOldIntoList()
    {
        CurrentViewInfo target = previousView.get(previousView.size()-1);
        switchView(target.targetView, target.targetProduct);
    }

    public void returnView()
    {
        if (canSwitchView())
        {
            if (previousView.size() > 0) {
                CurrentViewInfo target = previousView.get(previousView.size() - 1);
                previousView.remove(previousView.size() - 1);
                executeViewSwitch(target);
            }
        }
    }

    public void switchView(view target)
    {
        switchView(target, null, null);
    }
    public void switchView(view target, Product product) {
        switchView(target, product, null);
    }

    public void switchView(view target, String string) {
        if (string == "")
        {
            string = null;
        }

        switchView(target, null, string);
    }
    public void switchView(view target, Product product, String string) {
        if (canSwitchView())
        {

            if (!(currentView == null)) {
                previousView.add(currentView);
            }

            CurrentViewInfo targetView = new CurrentViewInfo(target, product, string, shopController.getCategory());
            executeViewSwitch(targetView);
        }

    }
    private void executeViewSwitch(CurrentViewInfo target)
    {
        currentView = target;
        switch (target.targetView)
        {
            case start:
                startStackPane.toFront();
                break;
            case shop:
                shopController.updateShopContents(target.targetString, target.targetCategory);
                shopStackPane.toFront();
                break;
            case profile:
                myInfoStackPane.toFront();
                break;
            case cart:
                cartListController.updateList();
                shoppingCartStackPane.toFront();
                break;
            case detail:
                detailViewController.setProduct(target.targetProduct);
                detailStackPane.toFront();
                break;
            default:
                break;
        }
    }

    public view getCurrentViewType()
    {
        if (!(currentView == null))
        {
            return currentView.targetView;
        }
        throw new RuntimeException("Tried to read CurrentViewType when it was blank.");
    }

    private class CurrentViewInfo{
        public Product targetProduct;
        public view targetView;
        public String targetString;
        public ProductCategory targetCategory;

        public CurrentViewInfo(view targetView)
        {
            this.targetView = targetView;
            this.targetProduct = null;
        }

        public CurrentViewInfo(view targetView, Product targetProduct, String targetString, ProductCategory targetCategory)
        {
            this.targetView = targetView;
            this.targetProduct = targetProduct;
            this.targetString = targetString;
            this.targetCategory = targetCategory;
        }

    }

}