package imat;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.text.LabelView;
import java.util.List;

public class CheckoutConfirmController extends CheckoutViewsController{
    private IMatDataHandler database = IMatDataHandler.getInstance();
    public FlowPane productFlowpane;
    public Label PrisSummaVaror;
    public Label PrisLeveranssätt;
    public Label TotalPris;
    public Label Prismoms;
    public Label DittKortnummer;
    public Label ValdAffärFörUpphämtning;
    public Label dateInfo;
    public Label tidInfo;

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
        PrisSummaVaror.setText(String.valueOf(MathF.twoDecimans(cart.getTotal())) + " kr");
        PrisLeveranssätt.setText(String.valueOf(MathF.twoDecimans(CheckoutInfo.getInstance().fines)) + " kr");
        TotalPris.setText(String.valueOf(MathF.twoDecimans(CheckoutInfo.getInstance().getTotal())) + " kr");
        Prismoms.setText(String.valueOf(MathF.twoDecimans(cart.getTotal()/4)) + " kr");
        String cardNum = IMatDataHandler.getInstance().getCreditCard().getCardNumber();
        cardNum = cardNum.substring(cardNum.length()-4);
        DittKortnummer.setText("**** **** **** " + cardNum);

        if (CheckoutInfo.getInstance().delivery)
        {

            ValdAffärFörUpphämtning.setText("Leverans: " + IMatDataHandler.getInstance().getCustomer().getAddress());
        }
        else
        {
            ValdAffärFörUpphämtning.setText("Hämtas i butik");
        }

        dateInfo.setText("Datum: " + CheckoutInfo.getInstance().deliverDateString);
        tidInfo.setText("Tid: " + CheckoutInfo.getInstance().deliverTimeString);
    }
}
