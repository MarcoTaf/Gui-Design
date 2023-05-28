package imat;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.event.Event;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class ShoppingCartListController extends SubViewController{
    private ShoppingCart cart = IMatDataHandler.getInstance().getShoppingCart();
    @FXML
    public AnchorPane backgroundAnchorPane;
    @FXML
    public AnchorPane cartAnchorPane;
    @FXML
    public FlowPane itemFlowPane;
    @FXML
    public Text totalPrice;
    public Label cartIsEmptyError;

    public ShoppingCartListController(MainViewController owner)
    {

        super("varukorgdropdown.fxml", owner);
        updateList();
    }

    public void updateList()
    {
        cartIsEmptyError.setText("");
        itemFlowPane.getChildren().clear();

        double totalCost = 0;

        List<ShoppingItem> items = cart.getItems();
        for (int i = 0; i < items.size(); i++)
        {
            ShoppingCartListItemController cartItem = new ShoppingCartListItemController(owner, this, items.get(i));
            itemFlowPane.getChildren().add(cartItem);
            totalCost += items.get(i).getTotal();

        }
        totalPrice.setText("Pris: " + String.valueOf(totalCost) + " kr");

    }


    public void goToCheckout()
    {
        if (IMatDataHandler.getInstance().getShoppingCart().getItems().size() > 0) {
            owner.storeLastViewInCheckoutInfo();
            owner.switchView(MainViewController.view.checkoutCart);
        }
        else
        {
            cartIsEmptyError.setText("Varukorgen är tom");
        }
    }
}
