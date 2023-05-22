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
import java.util.*;
import java.util.concurrent.Flow;

public class ShopController extends SubViewController {
    private MainViewController parent;
    private IMatDataHandler database = IMatDataHandler.getInstance();
    private HashMap<Product, ProductCardController> productCardDatabase = new HashMap<Product, ProductCardController>();
    private ProductCategory currentCategory = null;
    private boolean ecoEnabled = false;
    private sortMode sortingMode = sortMode.none; //I think I am dying bad names oh god

    @FXML
    public FlowPane productFlowPane;
    @FXML
    public FlowPane categoryFlowPane;
    public ShopController(MainViewController owner)
    {
        super("Kategorilista.fxml", owner);
        setupProductCardDB();
        updateShopContents(null, currentCategory, ecoEnabled, sortingMode);

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

    public void updateShopContents(String searchTemrs, ProductCategory category, boolean ecoEnabled, sortMode sortingMode)
    {
        productFlowPane.getChildren().clear();
        this.ecoEnabled = ecoEnabled;
        this.sortingMode = sortingMode;

        List<Product> products;
        List<Product> productsUnsorted;
        if (searchTemrs == null)
        {
            /*WTF? The list this spits out is not a copy. It's the actual original list of all the products
            that the code has access to. This means that if you manipulate or edit this list in any way
            then that means that all products will be wiped from the code since this is also the list it saves.
            Why would you ever have a setup like this???
             */
            productsUnsorted = new ArrayList<Product>(database.getProducts());
        }
        else
        {
            productsUnsorted = new ArrayList<>(database.findProducts(searchTemrs));
        }

        switch (sortingMode)
        {
            case top:
                products = sortList(productsUnsorted);
                break;
            case bottom:
                products = new ArrayList<Product>();//This code was brouht to you by Java, who cannot even make a reverseing method for their god damn lists.
                List<Product> sortedList = sortList(productsUnsorted);
                while(sortedList.size() > 0)
                {
                    products.add(sortedList.get(sortedList.size() - 1));
                    sortedList.remove(sortedList.size() - 1);
                }
                break;
            default:
                products = productsUnsorted; //Dear IDE, I feel like I am losing my sanity with you.
                break;
        }
        System.out.println(String.valueOf(sortingMode));

        for (int i = 0; i < products.size(); i++)
        {
            Product product = products.get(i);
            if (category == null || category == product.getCategory())
            {
                if (owner.favoritesEnabled && !(database.isFavorite(product)))
                {
                    continue;
                }

                if (ecoEnabled && !(product.isEcological()))
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

    public void flipEco()
    {
        ecoEnabled = (ecoEnabled == false);
        owner.switchView(MainViewController.view.shop);
    }

    public void setEco(boolean ecoEnabled)
    {
        this.ecoEnabled = ecoEnabled;
    }

    public boolean getEco()
    {
        return ecoEnabled;
    }

    public void setSortMode(sortMode sortingMode)
    {
        this.sortingMode = sortingMode;
        owner.switchView(MainViewController.view.shop);
    }

    public sortMode getSortMode()
    {
        return this.sortingMode;
    }

    public void setSortingModeTop()
    {
        if (sortingMode == sortMode.top)
        {
            setSortMode(sortMode.none);
        }
        else
        {
            setSortMode(sortMode.top);
        }
    }

    public void setSortingModeBottom()
    {
        if (sortingMode == sortMode.bottom)
        {
            setSortMode(sortMode.none);
        }
        else
        {
            setSortMode(sortMode.bottom);
        }
    }

    public List<Product> sortList(List<Product> products)
    {
        ArrayList<Product> productsSorted = new ArrayList<Product>();

        if (products.size() > 0)
        {
            productsSorted.add(products.get(0));
            products.remove(0);

            while(products.size() > 0)
            {
                Product currentProduct = products.get(0);
                products.remove(0);

                boolean foundFlag = false;
                for (int k = 0; k < productsSorted.size(); k++)
                {
                    if (currentProduct.getPrice() < productsSorted.get(k).getPrice())
                    {
                        foundFlag = true;
                        productsSorted.add(k, currentProduct);
                        break;
                    }
                }

                if (!(foundFlag))
                {
                    productsSorted.add(currentProduct);
                }
            }

        }
        return productsSorted;
    }

    enum sortMode{
        none,
        top,
        bottom
    }
}
