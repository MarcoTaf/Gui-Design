package imat;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class ShopController extends SubViewController {
    private MainViewController parent;

    @FXML
    public FlowPane productFlowPane;
    public ShopController(MainViewController owner)
    {
        super("Kategorilista.fxml", owner);
        productFlowPane.getChildren().add(new ProductCardController(IMatDataHandler.getInstance().getProduct(1), owner));
    }
}
