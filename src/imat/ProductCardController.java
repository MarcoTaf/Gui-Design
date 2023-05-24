package imat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;


public class ProductCardController extends SubViewController
   {
       private IMatDataHandler database = IMatDataHandler.getInstance();
       private MainViewController owner;
       private Product targetProduct;
       private ShoppingCart shoppingCart = IMatDataHandler.getInstance().getShoppingCart();
       private int amountToAdd = 0;

       private Lighting lighting;
       @FXML
       public ImageView productImage;
       @FXML
       public Text productName;
       @FXML
       public Text productDesc;
       @FXML
       public Text productPrice;
       @FXML
       public Text productPriceWithoutDiscount;
       @FXML
       public Text productAddAmount;
       @FXML
       public Button productRemoveFromAdd;
       @FXML
       public Button productAddToAdd;
       @FXML
       public Button productAddToCart;
       @FXML
       public AnchorPane anchorPane;

       @FXML
       public Button favoriteButton;
       @FXML
       public ImageView favoriteImage;
       @FXML
       public ImageView isEcoImage;
       public ProductCardController(Product targetProduct, MainViewController owner) {
           super("ProductCard.fxml", owner);
           this.owner = owner;


           this.targetProduct = targetProduct;
           loadTargetProduct(this.targetProduct);

           lighting = new Lighting(new Light.Distant(45, 90, Color.RED));
           ColorAdjust bright = new ColorAdjust(0, 1, 1, 1);
           lighting.setContentInput(bright);
           lighting.setSurfaceScale(0.0);
           setFavoriteImageLighting();

           if (!(targetProduct.isEcological()))
           {
               isEcoImage.setVisible(false);
           }

       }

       private void loadTargetProduct(Product targetProduct)
       {
           productName.setText(targetProduct.getName());
           productDesc.setText(targetProduct.getUnit());
           productPrice.setText(String.valueOf(targetProduct.getPrice()) + " kr");
           productPriceWithoutDiscount.setText("");


           productImage.setImage(database.getFXImage(targetProduct));
           updateAmountText();
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

       public void showDetailView()
       {
            owner.switchView(MainViewController.view.detail, targetProduct);

       }

       public void switchFavorite()
       {
            if (database.isFavorite(targetProduct))
            {
                database.removeFavorite(targetProduct);
            }
            else
            {
                database.addFavorite(targetProduct);
            }

            setFavoriteImageLighting();
       }

       private void setFavoriteImageLighting()
       {
           if (database.isFavorite(targetProduct))
           {
               favoriteImage.setEffect(lighting);
           }
           else
           {
               favoriteImage.setEffect(null);
           }
       }

}
