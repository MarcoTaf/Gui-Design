package imat;

import imat.SubViewController;
import javafx.event.Event;


public class MyInfoLeaveErrorController extends SubViewController {
    public MyInfoLeaveErrorController(MainViewController owner)
    {
        super("MyInfoBackoutWarning.fxml", owner);
    }


    public void returnView()
    {
        owner.returnView();
    }
}
