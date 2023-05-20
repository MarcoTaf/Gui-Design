package imat;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.concurrent.Flow;

public class ShopController extends SubViewController {
    private MainViewController parent;
    private IMatDataHandler database = IMatDataHandler.getInstance();
    private HashMap<Product, ProductCardController> productCardDatabase = new HashMap<Product, ProductCardController>();
    private ProductCategory currentCategory = null;

    @FXML
    public FlowPane productFlowPane;
    @FXML
    public FlowPane categoryFlowPane;
    public ShopController(MainViewController owner)
    {
        super("Kategorilista.fxml", owner);
        setupProductCardDB();
        updateShopContents(null, currentCategory);

        ProductCategory[] categories = ProductCategory.values();

        for (int i = 0; i < categories.length; i++)
        {
            ProductCategory category = categories[i];
            Button categoryButton = new Button();
            categoryButton.setText(String.valueOf(category));
            categoryButton.setPrefWidth(categoryFlowPane.getPrefWidth());
            categoryButton.setPrefHeight(100);
            categoryButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setCategory(category);
                }
            });
            categoryFlowPane.getChildren().add(categoryButton);
        }

    }

    public void updateShopContents(String searchTemrs, ProductCategory category)
    {
        productFlowPane.getChildren().clear();

        List<Product> products;
        if (searchTemrs == null)
        {
            products = database.getProducts();
        }
        else
        {
            products = database.findProducts(searchTemrs);
        }

        for (var i = 0; i < products.size(); i++)
        {
            Product product = products.get(i);
            if (category == null || category == product.getCategory())
            {
                if (owner.favoritesEnabled && !(database.isFavorite(product)))
                {
                    continue;
                }
                addProduct(product);
            }
        }

    }

    private void addProduct(Product target)
    {
        productFlowPane.getChildren().add(productCardDatabase.get(target));
    }

    private void setupProductCardDB()
    {
        List<Product> products = database.getProducts();

        for(int i = 0; i < products.size(); i++)
        {
            productCardDatabase.put(products.get(i), new ProductCardController(products.get(i), owner));
        }
    }

    public void setCategory(ProductCategory category)
    {
        if (currentCategory == category)
        {
            currentCategory = null;
        }
        else
        {
            currentCategory = category;
        }

        owner.switchView(MainViewController.view.shop);
    }

    public ProductCategory getCategory()
    {
        return currentCategory;
    }
}
