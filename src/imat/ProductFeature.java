package imat;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class ProductFeature extends SubViewController{
    private Product targetProduct;
    public ImageView productImage;
    public Text productName;
    public Text productPrice;
    public Text productAddAmount;
    public ShoppingCart shoppingCart;
    private int amountToAdd = 1;
    public ProductFeature(MainViewController owner, Product targetProduct)
    {
        super("storeFeature.fxml", owner);
        this.targetProduct = targetProduct;
        productImage.setImage(IMatDataHandler.getInstance().getFXImage(targetProduct));
        productName.setText(targetProduct.getName());
        productPrice.setText(String.valueOf(targetProduct.getPrice()) + " " + targetProduct.getUnit());
        shoppingCart = IMatDataHandler.getInstance().getShoppingCart();
        updateAmountText();
    }

    public void goToView()
    {
        owner.switchView(MainViewController.view.detail, targetProduct);
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
