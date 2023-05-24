package imat;

import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

public class PreviousPurchaseDetailController extends SubViewController{
    private Order targetOrder;
    public FlowPane cartFlowPane;
    public PreviousPurchaseDetailController(MainViewController owner)
    {
        super("PreviousPurchaseDetail.fxml", owner);
    }

    public void setOrder(Order targetOrder)
    {
        cartFlowPane.getChildren().clear();
        this.targetOrder = targetOrder;
        List<ShoppingItem> items = targetOrder.getItems();

        for (int i = 0; i < items.size(); i++)
        {
            cartFlowPane.getChildren().add(new PreviousPurchaseDetailItem(owner, items.get(i)));
        }
    }
}
