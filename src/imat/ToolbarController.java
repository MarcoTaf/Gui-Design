package imat;

import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;

public class ToolbarController extends SubViewController {
    private MainViewController parent;
    private Lighting lighting;
    @FXML
    public Button ToolbarHome;
    @FXML
    public Button ToolbarProfile;
    @FXML
    public Button ToolbarLogo;
    @FXML
    public TextField searchTextField;
    @FXML
    public Button ToolbarFavorite;
    @FXML
    public ImageView ToolbarFavoriteImage;
    @FXML
    public Text cartPriceText;

    public ToolbarController(MainViewController owner)
    {
        super("Toolbar.fxml", owner);

        searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
          @Override
          public void handle(KeyEvent event) {

              if(event.getCode().equals(
                      KeyCode.ENTER)
              ) {
                owner.switchView(MainViewController.view.shop, searchTextField.getText());
              }
          }
        });

        lighting = new Lighting(new Light.Distant(45, 90, Color.RED));
        ColorAdjust bright = new ColorAdjust(0, 1, 1, 1);
        lighting.setContentInput(bright);
        lighting.setSurfaceScale(0.0);

        updateFavoritesImage();

        ShoppingCart cart = IMatDataHandler.getInstance().getShoppingCart();

        cartPriceText.setText(String.valueOf(MathF.twoDecimans(cart.getTotal())) + " Kr");
        cart.addShoppingCartListener(new ShoppingCartListener() {
            @Override
            public void shoppingCartChanged(CartEvent cartEvent) {
                cartPriceText.setText(String.valueOf(MathF.twoDecimans(cart.getTotal())) + " Kr");
            }
        });
    }

    public void homePressed() {
        owner.switchView(MainViewController.view.start);
    }

    public void profilePressed() {
        owner.switchView(MainViewController.view.profile);
    }

    public void returnPressed()
    {
        owner.returnView();
    }
    public void cartPressed()
    {
        if (owner.getCurrentViewType() == MainViewController.view.cart)
        {
            owner.returnView();
        }
        else {
            owner.switchView(MainViewController.view.cart);
        }
    }

    public void flipFavoritesEnabled()
    {
        owner.flipFavoritesEnabled();
    }

    public void exitApp()
    {
        Platform.exit();
    }

    public void updateFavoritesImage()
    {
        if (owner.favoritesEnabled)
        {
            ToolbarFavoriteImage.setEffect(lighting);
        }
        else
        {
            ToolbarFavoriteImage.setEffect(null);
        }
    }
}
