package imat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class ShoppingCartListItemController extends SubViewController{
    private ShoppingItem targetItem;
    @FXML
    public Button VarukorgDDTaBort;
    @FXML
    public TextField Produktnamn;
    @FXML
    public TextField priceText;
    @FXML
    public TextField prisperstyck;
    @FXML
    public TextField prisperstyckrabatt;
    @FXML
    public TextField amountInCart;
    @FXML
    public Button productAdd;
    @FXML
    public Button productRemove;
    public ShoppingCartListItemController(MainViewController owner, ShoppingItem targetItem)
    {
        super("varukorglistitem.fxml", owner);
        this.targetItem = targetItem;
    }
}
