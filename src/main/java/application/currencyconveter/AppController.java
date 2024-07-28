package application.currencyconveter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class AppController {

    @FXML
    private TextField amountTextField;
    @FXML
    private Label originalLabel;
    @FXML
    private Label resultLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private ComboBox<String> toComboBox;
    @FXML
    private ComboBox<String> fromComboBox;
    private String convertFrom, convertTo,result,original;
    private BigDecimal quantity;
    CurrencyConverter currencyConverter;

    @FXML
    void initialize() throws IOException, InterruptedException {
        currencyConverter =new CurrencyConverter();
        fromComboBox.getItems().addAll(currencyConverter.getCurrencyNames());
        toComboBox.getItems().addAll(currencyConverter.getCurrencyNames());
        // Add listener to ensure only numeric input
        amountTextField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (!isValidNumericInput(event.getCharacter())) {
                event.consume();
            }
        });


    }
    @FXML
    void onConvertButtonClick(ActionEvent event) throws IOException, InterruptedException {
        if (isValidInputs()) {
            convertFrom=fromComboBox.getValue();
            convertTo=toComboBox.getValue();
            quantity=new BigDecimal(amountTextField.getText());
            currencyConverter.setOperation(convertFrom,convertTo,quantity);
            result=formatAsCurrency(currencyConverter.doOperation())+" "+convertTo;
            resultLabel.setText(result);
            original=formatAsCurrency(quantity)+" "+convertFrom;
            originalLabel.setText(original);
            statusLabel.setText("");
        } else {
            // Show error message in status label
            statusLabel.setText("Please enter valid inputs.");
        }
    }
    boolean isValidInputs(){
        boolean fromValid = isValidChoice(fromComboBox);
        boolean toValid = isValidChoice(toComboBox);
        boolean amountValid = isValidQuantity(amountTextField);

        return fromValid && toValid && amountValid;
    }
    <T> boolean isValidChoice(ComboBox<T> comboBox) {
        if (comboBox.getValue() == null) {
            addErrorBorder(comboBox);
            return false;
        } else {
            removeErrorBorder(comboBox);
            return true;
        }
    }

    boolean isValidQuantity(TextField textField) {
        String text = textField.getText();
        if (text == null || text.trim().isEmpty()) {
            addErrorBorder(textField);
            return false;
        }
        try {
            BigDecimal value = new BigDecimal(text);
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                addErrorBorder(textField);
                return false;
            }
        } catch (NumberFormatException e) {
            addErrorBorder(textField);
            return false;
        }
        removeErrorBorder(textField);
        return true;
    }

    private void addErrorBorder(javafx.scene.Node node) {
        if (!node.getStyleClass().contains("error-border")) {
            node.getStyleClass().add("error-border");
        }
    }

    private void removeErrorBorder(javafx.scene.Node node) {
        node.getStyleClass().remove("error-border");
    }
    public static String formatAsCurrency(BigDecimal amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(amount).substring(1);
    }
    private boolean isValidNumericInput(String input) {
        return input.matches("[0-9.]");
    }

}
