package imat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class CheckoutDeliveryController extends CheckoutViewsController{

    private ToggleGroup deliveryPicker;
    public RadioButton radioStore;
    public RadioButton radioDelivery;
    public Text addedPrice;
    public Text dateText;
    public Text timeText;
    public Spinner<Integer> timeSpinner;
    public DatePicker datePicker;

    public CheckoutDeliveryController(MainViewController owner)
    {
        super("Leverans.fxml", owner);
        deliveryPicker = new ToggleGroup();

        radioDelivery.setToggleGroup(deliveryPicker);
        radioStore.setToggleGroup(deliveryPicker);

        timeText.setText("Tid: 9:00");
        dateText.setText("Datum: ");

        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                dateText.setText("Datum: " + String.valueOf(t1));
            }
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999999,900 , 30);
        timeSpinner.setValueFactory(valueFactory);

        timeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                int min = getLastTWoNum(t1);
                int hours = t1 - min;

                if (min == 60)//This is horrible -MT
                {
                    hours+=100;
                    min = 0;
                }

                if (min == 70)
                {
                    min = 30;
                }

                if (hours >= 2400) {
                    hours = 900;
                }
                if (hours <= 800)
                {
                    hours = 2300;
                }

                int newTime = hours + min;

                timeSpinner.getValueFactory().setValue(newTime);//There is a bug here for 2200 going down. I do not have the time to fix. Fuck it. NVM fixed it

                timeText.setText("Tid: " + String.valueOf(hours/100) + ":" + String.valueOf(min));
            }
        });
    }

    public Integer getLastTWoNum(Integer input)
    {
        if (input >= 100)
        {

            return (input - (int) ((Math.floor(input/100))*100));
        }
        else
        {
            throw new RuntimeException();
        }
    }
}
