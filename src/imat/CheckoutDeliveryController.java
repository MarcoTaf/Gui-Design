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
    public Text deliveryCost;
    public Spinner<Integer> timeSpinner;
    public DatePicker datePicker;

    public Label radioError;
    public Label dateError;

    public CheckoutDeliveryController(MainViewController owner)
    {
        super("Leverans.fxml", owner);
        radioError.setText("");
        dateError.setText("");
        deliveryCost.setText("Leverans Kostnad: ");
        deliveryPicker = new ToggleGroup();

        radioDelivery.setToggleGroup(deliveryPicker);
        radioStore.setToggleGroup(deliveryPicker);

        radioDelivery.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1)
                {
                    CheckoutInfo.getInstance().setDelivery(true);
                    deliveryCost.setText("Leverans Kostnad: " + String.valueOf(Math.round(CheckoutInfo.getInstance().fines)) + " kr");
                }
            }
        });

        radioStore.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                CheckoutInfo.getInstance().setDelivery(false);
                deliveryCost.setText("Leverans Kostnad: " + String.valueOf(Math.round(CheckoutInfo.getInstance().fines)) + " kr");
            }
        });

        timeText.setText("Tid: 9:00");
        dateText.setText("Datum: ");

        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate localDate, LocalDate t1) {
                dateText.setText("Datum: " + String.valueOf(t1));
                CheckoutInfo.getInstance().deliverDateString = String.valueOf(t1);
            }
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999999,900 , 30);
        timeSpinner.setValueFactory(valueFactory);

        timeSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                int min = getLastTWoNum(t1);
                int hours = t1 - min;

                if (min == 60)
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

                timeSpinner.getValueFactory().setValue(newTime);
                String minText = String.valueOf(min);

                if (minText.length() <= 1)
                {
                    minText += "0";
                }
                String timeString = String.valueOf(hours/100) + ":" + minText;
                CheckoutInfo.getInstance().deliverTimeString = timeString;
                timeText.setText("Tid: " + timeString);
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

    public void canNext()
    {
        boolean canGoNext = true;

        if (!(radioStore.isSelected()) && !(radioDelivery.isSelected()))
        {
            radioError.setText("Välj hur varorna ska mottas");
            canGoNext = false;
        }
        else
        {
            radioError.setText("");
        }

        if (datePicker.getEditor().getText() == "")
        {
            dateError.setText("Välj ett datum");
            canGoNext = false;
        }
        else
        {
            dateError.setText("");
        }

        if (canGoNext)
        {
            switchViewPayment();
        }
    }
}
