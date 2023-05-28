package imat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.beans.value.ObservableValue;
import  javafx.beans.value.ChangeListener;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class CheckoutInfoController extends CheckoutViewsController {
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

    public Label nameError;

    public CheckoutInfoController(MainViewController owner)
    {
        super("UppgifterUtcheckning.fxml", owner);
        createTextfieldListener(firstNameField);
        createTextfieldListener(lastNameField);
        createTextfieldListener(mailField);
        createTextfieldListener(telField);
        createTextfieldListener(addressField);
        createTextfieldListener(postNumField);
        createTextfieldListener(postOrtField);

        customer = IMatDataHandler.getInstance().getCustomer();


        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        mailField.setText(customer.getEmail());
        telField.setText(customer.getPhoneNumber());
        addressField.setText(customer.getAddress());
        postNumField.setText(customer.getPostCode());
        postOrtField.setText(customer.getPostAddress());
        nameError.setText("");

        firstNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setFirstName(t1);
            }
        });

        lastNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setLastName(t1);
            }
        });
        mailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setEmail(t1);
            }
        });
        telField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setPhoneNumber(t1);
            }
        });
        addressField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setAddress(t1);
            }
        });
        postNumField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setPostCode(t1);
            }
        });
        postOrtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                customer.setPostAddress(t1);
            }
        });
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
        private CheckoutInfoController owner;

        public TextFieldListener(TextField textField, CheckoutInfoController owner){
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

    public void canNext()
    {
        if (customer.getFirstName() == "")
        {
            nameError.setText("Alla fält måste fyllas");
            return;
        }
        if (customer.getLastName() == "")
        {
            nameError.setText("Alla fält måste fyllas");
            return;
        }
        if (customer.getAddress() == "")
        {
            nameError.setText("Alla fält måste fyllas");
            return;
        }
        if (customer.getPhoneNumber() == "")
        {
            nameError.setText("Alla fält måste fyllas");
            return;
        }
        if (customer.getEmail() == "")
        {
            nameError.setText("Alla fält måste fyllas");
            return;
        }
        if (customer.getPostCode() == "")
        {
            nameError.setText("Alla fält måste fyllas");
            return;
        }
        if (customer.getPostAddress() == "")
        {
            nameError.setText("Alla fält måste fyllas");
            return;
        }

        nameError.setText("");


        switchViewDelivery();

    }
}
