
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

import javax.tools.Tool;


public class MainViewController implements Initializable {

    private ShopController shopController;
    private MyInfoController myInfoController;
    private ToolbarController toolbarController;
    private ShoppingCartListController cartListController;
    private DetailViewController detailViewController;
    private CheckoutItemsController checkoutItemsController;
    public boolean favoritesEnabled = false;

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
    public StackPane myInfoBackoutErrorStackPane;
    @FXML
    public  StackPane checkoutCartStackpane;
    @FXML
    public  StackPane checkoutInfoStackpane;
    @FXML StackPane checkoutDeliveryStackPane;
    @FXML StackPane checkoutPaymentStackPane;
    @FXML
    StackPane checkoutSistaStackPane;
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
        detail,
        myInfoBackoutError,
        checkoutCart,
        checkoutInfo,
        checkoutDelivery,
        checkoutPayment,
        checkoutConfirm,
        checkoutFinal
    }

    enum  test {a}

    public void initialize(URL url, ResourceBundle rb) {
        shopController = new ShopController(this);
        myInfoController = new MyInfoController(this);
        toolbarController = new ToolbarController(this);
        cartListController = new ShoppingCartListController(this);
        detailViewController = new DetailViewController(this);
        checkoutItemsController = new CheckoutItemsController(this);

        startStackPane.getChildren().add(new StartViewController(this));
        shopStackPane.getChildren().add(shopController);
        toolbarAnchorPane.getChildren().add(toolbarController);
        shoppingCartStackPane.getChildren().add(cartListController);
        myInfoStackPane.getChildren().add(myInfoController);
        detailStackPane.getChildren().add(detailViewController);
        myInfoBackoutErrorStackPane.getChildren().add(new MyInfoLeaveErrorController(this));
        checkoutCartStackpane.getChildren().add(checkoutItemsController);
        checkoutInfoStackpane.getChildren().add(new CheckoutInfoController(this));
        checkoutDeliveryStackPane.getChildren().add(new CheckoutDeliveryController(this));
        checkoutPaymentStackPane.getChildren().add(new CheckoutPaymentController(this));


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
                    previousView.add(currentView);
                    executeViewSwitch(new CurrentViewInfo(view.myInfoBackoutError));
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

            CurrentViewInfo targetView = new CurrentViewInfo(target, product, string, shopController.getCategory(), shopController.getEco(), shopController.getSortMode());//BRB gonna need to get an ultra wide laptop to handle the amount of god damn arguments here. If I ask for wheels to be put onto it, do you think I can skate on it?
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
                shopController.updateShopContents(target.targetString, target.targetCategory, target.ecoEnabled, target.sortMode);
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
            case myInfoBackoutError:
                myInfoBackoutErrorStackPane.toFront();
                break;
            case checkoutCart:
                checkoutItemsController.updateView();
                checkoutCartStackpane.toFront();
                break;
            case checkoutInfo:
                checkoutInfoStackpane.toFront();
                break;
            case checkoutDelivery:
                checkoutDeliveryStackPane.toFront();
                break;
            case checkoutPayment:
                checkoutPaymentStackPane.toFront();
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

    public void flipFavoritesEnabled()
    {
        favoritesEnabled = (favoritesEnabled == false);
        toolbarController.updateFavoritesImage();
        shopController.updateShopContents(currentView.targetString, currentView.targetCategory, currentView.ecoEnabled, currentView.sortMode);
    }

    public void setStoreEco(boolean ecoEnabled)
    {
        shopController.setEco(ecoEnabled);
    }

    private class CurrentViewInfo{
        public Product targetProduct = null;
        public view targetView;
        public String targetString= null;
        public ProductCategory targetCategory= null;
        public boolean ecoEnabled = false;
        public ShopController.sortMode sortMode = ShopController.sortMode.none;


        public CurrentViewInfo(view targetView)
        {
            this.targetView = targetView;
        }

        public CurrentViewInfo(view targetView, Product targetProduct, String targetString, ProductCategory targetCategory, boolean ecoEnabled, ShopController.sortMode sortMode)
        {
            this.targetView = targetView;
            this.targetProduct = targetProduct;
            this.targetString = targetString;
            this.targetCategory = targetCategory;
            this.ecoEnabled = ecoEnabled;
            this.sortMode = sortMode;
        }

    }

}