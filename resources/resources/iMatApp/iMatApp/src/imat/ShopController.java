package imat;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ShopController extends SubViewController {
    private MainViewController parent;
    public ShopController(MainViewController owner)
    {
        super("Kategorilista.fxml", owner);
    }
}
