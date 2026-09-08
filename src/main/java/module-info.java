module org.example.fast_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fast_app.controller to javafx.fxml;
    exports org.example.fast_app;
}