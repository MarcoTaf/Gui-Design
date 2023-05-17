package imat;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.beans.value.ObservableValue;
import  javafx.beans.value.ChangeListener;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class MyInfoController extends SubViewController {
    private Customer customer;
    private boolean haveFieldsChanged = false;
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public TextField mailField;
    @FXML
    public TextField telField;
    @FXML
    public TextField addressField;
    @FXML
    public TextField postNumField;
    @FXML
    public TextField postOrtField;
    @FXML
    public Button confirmButton;

    public MyInfoController(MainViewController owner)
    {
        super("Min_Profil.fxml", owner);
        createTextfieldListener(firstNameField);
        createTextfieldListener(lastNameField);
        createTextfieldListener(mailField);
        createTextfieldListener(telField);
        createTextfieldListener(addressField);
        createTextfieldListener(postNumField);
        createTextfieldListener(postOrtField);

        customer = IMatDataHandler.getInstance().getCustomer();

        firstNameField.setText(customer.getFirstName());
    }

    public void fieldChanged()
    {
        haveFieldsChanged = true;
        System.out.println("Changed");
    }

    public boolean checkFieldsChanged()
    {
        return haveFieldsChanged;
    }

    public void updateInfoBackend()
    {
        if (haveFieldsChanged)
        {
            haveFieldsChanged = false;

            customer.setFirstName(firstNameField.getText());
            customer.setLastName(lastNameField.getText());
            customer.setEmail(mailField.getText());
            customer.setPhoneNumber(telField.getText());
            customer.setAddress(addressField.getText());
            customer.setPostCode(postNumField.getText());
            customer.setPostAddress(postOrtField.getText());
        }
    }

    private class TextFieldListener implements ChangeListener<Boolean>{

        private TextField textField;
        private MyInfoController owner;

        public TextFieldListener(TextField textField, MyInfoController owner){
            this.textField = textField;
            this.owner = owner;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            owner.fieldChanged();
        }
    }

    private void createTextfieldListener(TextField target)
    {
        target.focusedProperty().addListener(new TextFieldListener(target, this));
    }
}
