package imat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class ShoppingCartListItemController extends SubViewController{
    private IMatDataHandler database = IMatDataHandler.getInstance();
    private ShoppingItem targetItem;
    @FXML
    public Button VarukorgDDTaBort;
    @FXML
    public ImageView VarukorgDDImage;
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
        updateListItem();
    }

    private void updateListItem()
    {
        Product product = targetItem.getProduct();
        Produktnamn.setText(product.getName());
        VarukorgDDImage.setImage(database.getFXImage(product));
    }
}
