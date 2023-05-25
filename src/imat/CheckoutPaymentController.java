package imat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class CheckoutPaymentController extends CheckoutViewsController{
    private CreditCard card = IMatDataHandler.getInstance().getCreditCard();
    public TextField KortnummerBetalning;
    public TextField MM;
    public TextField ÅÅ;
    public TextField CVCBetalning;
    public Label SummaVarorPris;
    public Label AvgiftPris;
    public Label TotalsummaPris;
    public Label cardNumError;
    public Label MMÅÅError;
    public Label CVCError;
    public CheckoutPaymentController(MainViewController owner)
    {
        super("Betalning.fxml", owner);
    }

    public void updateLabels()
    {
        KortnummerBetalning.setText(card.getCardNumber());
        MM.setText(String.valueOf(card.getValidMonth()));
        ÅÅ.setText(String.valueOf(card.getValidYear()));
        CVCBetalning.setText(String.valueOf(card.getVerificationCode()));
        SummaVarorPris.setText(String.valueOf(String.valueOf(IMatDataHandler.getInstance().getShoppingCart().getTotal())) + " kr");
        AvgiftPris.setText(String.valueOf(MathF.twoDecimans(owner.fines)) + " kr");
        TotalsummaPris.setText(String.valueOf(owner.getTotalCost()) + " kr");

        cardNumError.setText("");
        MMÅÅError.setText("");
        CVCError.setText("");

        KortnummerBetalning.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                if (!(t1.matches("-?\\d+(.\\d+)?"))) {
                    cardNumError.setText("The card number cannot contain letters");
                }
                else {
                    cardNumError.setText("");
                    card.setCardNumber(t1);
                }
            }
        });



        MM.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                if (!(t1.matches("-?\\d+(.\\d+)?"))) {
                    MMÅÅError.setText("The YY/MM fields cannot contain letters");
                }
                else {
                    MMÅÅError.setText("");
                    card.setValidMonth(Integer.valueOf(t1));
                }
            }

        });

        ÅÅ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!(t1.matches("-?\\d+(.\\d+)?"))) {
                    MMÅÅError.setText("The YY/MM fields cannot contain letters");
                }
                else {
                    MMÅÅError.setText("");
                    card.setValidYear(Integer.valueOf(t1));
                }

            }
        });

        CVCBetalning.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!(t1.matches("-?\\d+(.\\d+)?"))) {
                    CVCError.setText("The CVC number cannot contain letters");
                }
                else {
                    CVCError.setText("");
                    card.setVerificationCode(Integer.valueOf(t1));
                }

            }
        });
    }

    public void numChanged()
    {
        card.setCardNumber(KortnummerBetalning.getText());
    }
    public void monthChanged()
    {
        card.setValidMonth(Integer.valueOf(MM.getText()));
    }

    public void yearChanged()
    {
        card.setValidYear(Integer.valueOf(ÅÅ.getText()));
    }
    public void cvvChanged()
    {
        card.setVerificationCode(Integer.valueOf(CVCBetalning.getText()));
    }

    public void checkNext()
    {
        boolean canSwitch = true;

        if (card.getCardNumber().equals("")) {
            cardNumError.setText("The card number cannot be empty");
            canSwitch = false;
        }else if (!(card.getCardNumber().length() == 16))
        {cardNumError.setText("The card number must contain 16 digits");
            canSwitch = false;}
        else{
            cardNumError.setText("");
        }

        if (String.valueOf(card.getValidMonth()).equals("") || String.valueOf(card.getValidYear()).equals("")) {
            MMÅÅError.setText("The YY/MM fields cannot be empty");
            canSwitch = false;
        }else if (!(String.valueOf(card.getValidMonth()).length() == 2) || !(String.valueOf(card.getValidYear()).length() == 2))
             {
                 MMÅÅError.setText("The YY/MM fields must contain 2 digits");
        canSwitch = false;
             }else{
            MMÅÅError.setText("");
        }

        if (String.valueOf(card.getVerificationCode()).equals("")) {
            CVCError.setText("The CVC number cannot be empty");
            canSwitch = false;
        }else if (!(String.valueOf(card.getVerificationCode()).length() == 3))
        {
            CVCError.setText("The CVC number must contain 3 digits");
            canSwitch = false;
        }
        else{
            CVCError.setText("");
        }



        if (canSwitch)
        {
            switchViewConfirm();
        }
    }
}
