package imat;

import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;

public class CheckoutConfirmController extends CheckoutViewsController{
    private IMatDataHandler database = IMatDataHandler.getInstance();
    public FlowPane productFlowpane;

    public CheckoutConfirmController(MainViewController owner)
    {
        super("Bekräftelse.fxml", owner);
    }

    public void finishPurchase()
    {
        database.placeOrder();
        switchViewFinal();
    }

    public void updateList()
    {
        productFlowpane.getChildren().clear();

        ShoppingCart cart = IMatDataHandler.getInstance().getShoppingCart();

        List<ShoppingItem> items = cart.getItems();

        for (int i = 0; i < items.size(); i++)
        {
            PreviousPurchaseDetailItem item = new PreviousPurchaseDetailItem(owner, items.get(i));
            item.setPrefWidth(productFlowpane.getPrefWidth());
            productFlowpane.getChildren().add(item);
        }
    }
}
