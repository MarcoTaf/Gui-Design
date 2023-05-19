package imat;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

public class DetailViewController extends SubViewController{
    private Product target = null;
    private IMatDataHandler database;
    @FXML
    public ImageView image;
    public DetailViewController(MainViewController owner)
    {
        super("detailView.fxml", owner);
        database = IMatDataHandler.getInstance();
    }

    public void setProduct(Product target)
    {
        this.target = target;
        updateVisuals();
    }

    public void updateVisuals()
    {
        if (target == null)
        {
           throw new RuntimeException("target item in detail view was null");
        }

        image.setImage(database.getFXImage(target));

    }
}
