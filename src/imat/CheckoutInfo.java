package imat;

import javafx.scene.control.DatePicker;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class CheckoutInfo {
    private static CheckoutInfo inst = null;

    public double fines = 0;
    public boolean delivery = false;

    public MainViewController.CurrentViewInfo lastViewBeofreCheckout = null;
    public String deliverDateString;
    public String deliverTimeString = "9:00";



    public static CheckoutInfo getInstance()
    {
        if (inst == null)
        {
            inst = new CheckoutInfo();
        }

        return inst;
    }

    private CheckoutInfo()
    {

    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;

        if (delivery)
        {
            fines = 99d;
        }
        else
        {
            fines = 0d;
        }
    }

    public double getTotal()
    {
        return IMatDataHandler.getInstance().getShoppingCart().getTotal() + fines;
    }
}
