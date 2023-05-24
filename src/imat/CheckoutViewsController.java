package imat;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

public abstract class CheckoutViewsController extends  SubViewController{
    @FXML
    protected Button cartButton;
    @FXML
    protected Button infoButton;
    @FXML
    protected Button deliveryButton;
    @FXML
    protected Button paymentButton;
    @FXML
    protected Button confirmButton;
    public CheckoutViewsController(String path, MainViewController owner)
    {
        super(path, owner);
    }

    public void switchViewCart()
    {
        owner.switchView(MainViewController.view.checkoutCart);
    }

    public void switchViewInfo()
    {
        owner.switchView(MainViewController.view.checkoutInfo);
    }

    public void switchViewDelivery()
    {
        owner.switchView(MainViewController.view.checkoutDelivery);
    }

    public void switchViewPayment()
    {
        owner.switchView(MainViewController.view.checkoutPayment);
    }
    public void switchViewConfirm()
    {
        owner.switchView(MainViewController.view.checkoutConfirm);
    }
    public void switchViewFinal()
    {
        owner.switchView(MainViewController.view.checkoutFinal);
    }
}
