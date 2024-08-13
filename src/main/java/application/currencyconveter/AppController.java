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
import java.util.Objects;

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
            String convertFrom = fromComboBox.getValue();
            String convertTo = toComboBox.getValue();
            BigDecimal quantity = new BigDecimal(amountTextField.getText());
            currencyConverter.setOperation(convertFrom, convertTo, quantity);
            String result = formatAsCurrency(currencyConverter.doOperation()) + " " + convertTo;
            resultLabel.setText(result);
            String original = formatAsCurrency(quantity) + " " + convertFrom;
            originalLabel.setText(original);
            statusLabel.setText("");
        } else {
            // Show error message in status label
            statusLabel.setText("Please enter valid inputs.");
        }
    }

    private boolean isValidInputs(){
        boolean fromValid = isValidChoice(fromComboBox);
        boolean toValid = isValidChoice(toComboBox);
        boolean amountValid = isValidQuantity(amountTextField);

        return fromValid && toValid && amountValid;
    }

    private <T> boolean isValidChoice(ComboBox<T> comboBox) {
        boolean valid = comboBox.getValue() != null;
        setErrorBorder(comboBox, !valid);
        return valid;
    }

    private boolean isValidQuantity(TextField textField) {
        String text = textField.getText();
        boolean valid = text != null && !text.trim().isEmpty();
        try {
            Objects.requireNonNull(text, "Text field value cannot be null");
            BigDecimal value = new BigDecimal(text);
            valid = valid && value.compareTo(BigDecimal.ZERO) > 0;
        } catch (NumberFormatException | NullPointerException e) {
            valid = false;
        }
        setErrorBorder(textField, !valid);
        return valid;
    }

    private void setErrorBorder(javafx.scene.Node node, boolean error) {
        if (error) {
            if (!node.getStyleClass().contains("error-border")) {
                node.getStyleClass().add("error-border");
            }
        } else {
            node.getStyleClass().remove("error-border");
        }
    }

    private boolean isValidNumericInput(String input) {
        return input.matches("[0-9.]");
    }

    private String formatAsCurrency(BigDecimal amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(amount).substring(1);
    }
}
