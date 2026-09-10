module org.example.fast_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    opens org.example.fast_app.application to javafx.graphics, javafx.fxml;
    opens org.example.fast_app.controller to javafx.fxml;
    opens org.example.fast_app.model to javafx.base;

    exports org.example.fast_app.application;
    exports org.example.fast_app.controller;
    exports org.example.fast_app.model;
    exports org.example.fast_app.util;
}