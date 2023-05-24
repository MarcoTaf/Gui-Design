package imat;

import se.chalmers.cse.dat216.project.IMatDataHandler;

public class CheckoutConfirmController extends CheckoutViewsController{
    private IMatDataHandler database = IMatDataHandler.getInstance();

    public CheckoutConfirmController(MainViewController owner)
    {
        super("Bekräftelse.fxml", owner);
    }

    public void finishPurchase()
    {
        database.placeOrder();
        switchViewFinal();
    }
}
