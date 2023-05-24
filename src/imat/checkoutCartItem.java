package imat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;



public class checkoutCartItem extends SubViewController{
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
    private CheckoutItemsController cart;
    public checkoutCartItem(MainViewController owner,CheckoutItemsController cart, ShoppingItem targetItem)
    {
        super("varukorgChckoutlistamedvaror.fxml", owner);
        this.targetItem = targetItem;
        this.cart = cart;
        updateSelf();
    }

    private void updateSelf()
    {
        Product product = targetItem.getProduct();
        Produktnamn.setText(product.getName());
        VarukorgDDImage.setImage(database.getFXImage(product));
        amountInCart.setText(String.valueOf((int) targetItem.getAmount()) + " st");
        priceText.setText(String.valueOf(targetItem.getTotal()) + " kr");

    }

    public void addToSelf()
    {
        targetItem.setAmount(targetItem.getAmount() + 1);
        cart.updateView();
    }

    public void removeFromSelf()
    {
        if (targetItem.getAmount() <= 1)
        {
            removeSelf();
        }
        else
        {
            targetItem.setAmount(targetItem.getAmount() - 1);
            cart.updateView();
        }
    }

    public void removeSelf()
    {
        database.getShoppingCart().removeItem(targetItem);
        cart.updateView();
    }

}
