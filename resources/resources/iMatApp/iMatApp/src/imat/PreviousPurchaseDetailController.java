package imat;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

public class PreviousPurchaseDetailController extends SubViewController{
    private Order targetOrder;
    public FlowPane cartFlowPane;
    public Label totalCostLabel;
    public PreviousPurchaseDetailController(MainViewController owner)
    {
        super("PreviousPurchaseDetail.fxml", owner);
    }

    public void setOrder(Order targetOrder)
    {
        cartFlowPane.getChildren().clear();
        this.targetOrder = targetOrder;
        List<ShoppingItem> items = targetOrder.getItems();
        float totalPrice = 0;

        for (int i = 0; i < items.size(); i++)
        {
            cartFlowPane.getChildren().add(new PreviousPurchaseDetailItem(owner, items.get(i)));
            totalPrice += items.get(i).getTotal();
        }

        totalCostLabel.setText("Total pris: " + String.valueOf(totalPrice) +  " Kr");
    }

    public void addToCart()
    {
        ShoppingCart cart = IMatDataHandler.getInstance().getShoppingCart();

        for (int i = 0; i < targetOrder.getItems().size(); i++)
        {
            cart.addItem(targetOrder.getItems().get(i));
        }
    }
}
