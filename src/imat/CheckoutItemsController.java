package imat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

public class CheckoutItemsController extends CheckoutViewsController{
    @FXML
    public Label totalCostLabel;
    @FXML
    public FlowPane cartFlowPane;
    public CheckoutItemsController(MainViewController owner)
    {
        super("VarukorgCheckout.fxml", owner);

    }

    public void updateView()
    {
        cartFlowPane.getChildren().clear();

        List<ShoppingItem> items = IMatDataHandler.getInstance().getShoppingCart().getItems();

        for (int i = 0; i < items.size(); i++)
        {
            cartFlowPane.getChildren().add(new checkoutCartItem(owner, this, items.get(i)));
        }
        totalCostLabel.setText("Totalt Pris: " + String.valueOf(MathF.twoDecimans(IMatDataHandler.getInstance().getShoppingCart().getTotal())) + " kr");
    }

    public void exitCheckout()
    {
        owner.switchView(CheckoutInfo.getInstance().lastViewBeofreCheckout);
    }
}
