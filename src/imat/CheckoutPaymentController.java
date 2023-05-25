package imat;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class CheckoutPaymentController extends CheckoutViewsController{
    private CreditCard card = IMatDataHandler.getInstance().getCreditCard();
    public TextField KortnummerBetalning;
    public TextField MM;
    public TextField ÅÅ;
    public TextField CVCBetalning;
    public Label SummaVarorPris;
    public Label AvgiftPris;
    public Label TotalsummaPris;
    public CheckoutPaymentController(MainViewController owner)
    {
        super("Betalning.fxml", owner);
    }

    public void updateLabels()
    {
        SummaVarorPris.setText(String.valueOf(String.valueOf(IMatDataHandler.getInstance().getShoppingCart().getTotal())) + " kr");
        AvgiftPris.setText(String.valueOf(MathF.twoDecimans(owner.fines)) + " kr");
        TotalsummaPris.setText(String.valueOf(owner.getTotalCost()) + " kr");
    }

    public void numChanged()
    {
        card.setCardNumber(KortnummerBetalning.getText());
    }
    public void monthChanged()
    {
        card.setValidMonth(Integer.valueOf(MM.getText()));
    }

    public void yearChanged()
    {
        card.setValidYear(Integer.valueOf(ÅÅ.getText()));
    }
    public void cvvChanged()
    {
        card.setVerificationCode(Integer.valueOf(CVCBetalning.getText()));
    }
}
