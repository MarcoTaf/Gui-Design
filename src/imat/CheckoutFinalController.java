package imat;

public class CheckoutFinalController extends CheckoutViewsController{
    public CheckoutFinalController(MainViewController owner)
    {
        super("SistaVy.fxml", owner);
    }

    public void toStart()
    {
        owner.switchView(MainViewController.view.start);
    }
}
