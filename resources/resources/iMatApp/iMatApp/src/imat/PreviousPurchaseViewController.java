package imat;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;

import java.util.List;

public class PreviousPurchaseViewController extends SubViewController{
    @FXML
    public FlowPane orderFlowPane;
    public  PreviousPurchaseViewController(MainViewController owner)
    {
        super("PreviousPurchaseView.fxml", owner);
        updateView();
    }

    public void updateView()
    {
        orderFlowPane.getChildren().clear();

        List<Order> orders =  IMatDataHandler.getInstance().getOrders();


        for (int i = 0; i < orders.size(); i++)
        {
            orderFlowPane.getChildren().add(new PreviousPurcahseCard(owner, orders.get(i)));
        }
    }
}
