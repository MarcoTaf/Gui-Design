package imat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.*;

public class ShopController extends SubViewController {
    private MainViewController parent;
    private IMatDataHandler database = IMatDataHandler.getInstance();
    private HashMap<Product, ProductCardController> productCardDatabase = new HashMap<Product, ProductCardController>();
    private ProductCategory currentCategory = null;
    private boolean ecoEnabled = false;
    private sortMode sortingMode = sortMode.none;

    private HashMap<String, String> categoryNames = new HashMap<String, String>();
    private ProductFeature feature;

    @FXML
    public FlowPane productFlowPane;
    @FXML
    public FlowPane categoryFlowPane;
    public ShopController(MainViewController owner)
    {
        super("Kategorilista.fxml", owner);
        setupProductCardDB();
        List<Product> products = IMatDataHandler.getInstance().getProducts();

        if (products.size() == 0)
        {
            throw new RuntimeException("Product list is empty. Check files");
        }

        Product featureProduct = products.get(new Random().nextInt(products.size()));
        feature = new ProductFeature(owner, featureProduct);

        updateShopContents(null, currentCategory, ecoEnabled, sortingMode);

        ProductCategory[] categories = ProductCategory.values();
        String[] buttonNames = {"Ärtor", "Bröd", "Bär", "Citrusfrukt", "Varma drycker", "Kalla drycker", "Exotiska frukter",
        "Fisk", "Grönsaker och Frukt", "Sallad","Kött", "Mejeri", "Meloner", "Mjöl, Socker och salt", "Nötter och Frön", "Pasta", "Potatis och ris",
        "Rotfrukterr", "Frukt", "Sött", "Örter"};

        for (int i = 0; i < categories.length; i++)
        {
            ProductCategory category = categories[i];
            Button categoryButton = new OverrideButtonController();

            categoryButton.setText(buttonNames[i]);
            categoryButton.setPrefWidth(categoryFlowPane.getPrefWidth());
            categoryButton.setPrefHeight(100);


            categoryButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    setCategory(category);

                    boolean hasSelectedClass = categoryButton.getStyleClass().contains("selected-category");


                    for (Node node : categoryFlowPane.getChildren()) {
                        if (node instanceof Button) {

                            node.getStyleClass().remove("selected-category");
                        }
                    }


                    if (!hasSelectedClass) {
                        categoryButton.getStyleClass().add("selected-category");
                    }
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

        if ((searchTemrs == null) && sortingMode == sortMode.none && !(ecoEnabled) && !owner.favoritesEnabled)
        {
            feature.setPrefWidth(productFlowPane.getPrefWidth());
            feature.productImage.setFitWidth(productFlowPane.getPrefWidth());
            productFlowPane.getChildren().add(feature);

        }
        if (searchTemrs == null)
        {
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
                products = new ArrayList<Product>();
                List<Product> sortedList = sortList(productsUnsorted);
                while(sortedList.size() > 0)
                {
                    products.add(sortedList.get(sortedList.size() - 1));
                    sortedList.remove(sortedList.size() - 1);
                }
                break;
            default:
                products = productsUnsorted;
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
