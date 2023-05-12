package imat;

import javafx.fxml.FXMLLoader;

public abstract class SubViewController {
    public SubViewController(String fileName)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }
}
