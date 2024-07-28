module application.currencyconveter {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;


    opens application.currencyconveter to javafx.fxml;
    exports application.currencyconveter;
}