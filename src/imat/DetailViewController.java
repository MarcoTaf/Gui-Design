package imat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class DetailViewController extends SubViewController{
    private Product targetProduct = null;
    private IMatDataHandler database;
    private int amountToAdd;
    private ShoppingCart shoppingCart;
    public Label productNamn;
    public Label priceLabel;
    @FXML
    public AnchorPane backgroundAnchor;
    @FXML
    public AnchorPane detailAnchor;
    @FXML
    public ImageView image;
    @FXML
    public Text productAddAmount;
    @FXML
    public Button productRemoveFromAdd;
    @FXML
    public Button productAddToAdd;
    @FXML
    public Button productAddToCart;
    public DetailViewController(MainViewController owner)
    {
        super("detailView.fxml", owner);
        database = IMatDataHandler.getInstance();
        shoppingCart = database.getShoppingCart();
    }

    public void setProduct(Product target)
    {
        this.targetProduct = target;
        amountToAdd = 1;
        updateVisuals();
    }

    public void updateVisuals()
    {
        if (targetProduct == null)
        {
           throw new RuntimeException("target item in detail view was null");
        }

        image.setImage(database.getFXImage(targetProduct));
        productNamn.setText(targetProduct.getName());
        priceLabel.setText("Pris: " + targetProduct.getPrice() + " " +  targetProduct.getUnit());
        updateAmountText();

    }

    public void exitDetailView()
    {
        owner.returnAddOldIntoList();
    }


    public void addToAdd()
    {
        amountToAdd++;
        updateAmountText();
    }

    public void removeFromAdd()
    {
        if (amountToAdd > 1)
        {
            amountToAdd--;
            updateAmountText();
        }
    }

    public void addToCart()
    {
        if (amountToAdd > 0) {
            ShoppingItem item = new ShoppingItem(targetProduct, amountToAdd);
            shoppingCart.addItem(item, true);

            amountToAdd = 1;
            updateAmountText();
        }
    }

    private void updateAmountText()
    {
        productAddAmount.setText(String.valueOf(amountToAdd) + " " + targetProduct.getUnitSuffix());
    }
}
