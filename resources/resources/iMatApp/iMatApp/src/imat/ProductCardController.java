package imat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import se.chalmers.cse.dat216.project.Product;



public class ProductCardController extends AnchorPane
   {
       private Product targetProduct;
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
       public ProductCardController(Product targetProduct) {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductCard.fxml"));
           fxmlLoader.setRoot(this);
           fxmlLoader.setController(this);

           try {
               fxmlLoader.load();
           } catch (IOException exception) {
               throw new RuntimeException(exception);
           }

           this.targetProduct = targetProduct;
           loadTargetProduct(this.targetProduct);
       }

       private void loadTargetProduct(Product targetProduct)
       {
           productName.setText(targetProduct.getName());
       }

}
