package imat;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public abstract class SubViewController extends AnchorPane {
    protected MainViewController owner;
    public SubViewController(String path, MainViewController owner)
    {
        this.owner = owner;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/" + path));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void mouseTrap(Event event)
    {
        event.consume();
    }

    public void exitView()
    {
        owner.returnView();
    }
}
