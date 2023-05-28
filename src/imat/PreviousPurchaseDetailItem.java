package imat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;



public class PreviousPurchaseDetailItem extends SubViewController{
    private IMatDataHandler database = IMatDataHandler.getInstance();
    private ShoppingItem targetItem;
    @FXML
    public Button VarukorgDDTaBort;
    @FXML
    public ImageView VarukorgDDImage;
    @FXML
    public Label Produktnamn;
    @FXML
    public Label priceText;
    @FXML
    public Label prisperstyck;
    @FXML
    public Label prisperstyckrabatt;
    @FXML
    public Label amountInCart;
    @FXML
    public Button productAdd;
    @FXML
    public Button productRemove;
    private Order targetOrder;
    public PreviousPurchaseDetailItem(MainViewController owner,  ShoppingItem targetItem)
    {
        super("PreviousPurchaseDetailItem.fxml", owner);
        this.targetItem = targetItem;

        updateSelf();
    }

    private void updateSelf()
    {
        Product product = targetItem.getProduct();
        Produktnamn.setText(product.getName());
        VarukorgDDImage.setImage(database.getFXImage(product));
        priceText.setText(String.valueOf(MathF.twoDecimans(targetItem.getTotal())) + " kr");
        amountInCart.setText(String.valueOf(targetItem.getAmount()) + " st");

    }



}
