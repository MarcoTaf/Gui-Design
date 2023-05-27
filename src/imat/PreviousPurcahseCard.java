package imat;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.util.List;
import java.util.Random;

public class PreviousPurcahseCard extends SubViewController{
    private Order targetOrder;
    @FXML
    public ImageView image1;
    @FXML
    public ImageView image2;
    @FXML
    public ImageView image3;
    @FXML
    public ImageView image4;
    @FXML
    public Text dateText;
    @FXML
    public Text priceText;
    public PreviousPurcahseCard(MainViewController owner, Order targetOrder) {
        super("tidigare_köp.fxml", owner);
        this.targetOrder = targetOrder;
        updateCard();
    }

    public void updateCard()
    {
        List<ShoppingItem> products = targetOrder.getItems();

        float totalPrice = 0;
        dateText.setText(String.valueOf(targetOrder.getDate()));

        for(int i = 0; i < products.size(); i++)
        {

            totalPrice += products.get(i).getTotal();
        }
        priceText.setText(String.valueOf(totalPrice) + " Kr.");
        addRandImg(image1);
        addRandImg(image2);
        addRandImg(image3);
        addRandImg(image4);
    }

    private void addRandImg(ImageView target)
    {
        target.setImage(IMatDataHandler.getInstance().getFXImage(targetOrder.getItems().get(new Random().nextInt(targetOrder.getItems().size())).getProduct()));
    }

    public void openDetail()
    {
        owner.switchView(MainViewController.view.previousPurchaseDetail, targetOrder);
    }
}
