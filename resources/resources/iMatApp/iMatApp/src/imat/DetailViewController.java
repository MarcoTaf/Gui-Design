package imat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
        amountToAdd = 0;
        updateVisuals();
    }

    public void updateVisuals()
    {
        if (targetProduct == null)
        {
           throw new RuntimeException("target item in detail view was null");
        }

        image.setImage(database.getFXImage(targetProduct));
        updateAmountText();

    }

    public void exitDetailView()
    {
        owner.returnAddOldIntoList();
    }

    public void mouseTrap(Event event)
    {
        event.consume();
    }

    public void addToAdd()
    {
        amountToAdd++;
        updateAmountText();
    }

    public void removeFromAdd()
    {
        if (amountToAdd > 0)
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

            amountToAdd = 0;
            updateAmountText();
        }
    }

    private void updateAmountText()
    {
        productAddAmount.setText(String.valueOf(amountToAdd) + " st");
    }
}
