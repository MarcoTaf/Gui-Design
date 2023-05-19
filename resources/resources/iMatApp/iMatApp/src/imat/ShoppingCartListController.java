package imat;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.event.Event;

public class ShoppingCartListController extends SubViewController{
    @FXML
    public AnchorPane backgroundAnchorPane;
    @FXML
    public AnchorPane cartAnchorPane;
    public ShoppingCartListController(MainViewController owner)
    {
        super("varukorgdropdown.fxml", owner);
    }

    public void outsideClicked()
    {
        owner.returnView();
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }
}
