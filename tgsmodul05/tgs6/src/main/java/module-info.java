module com.example.tgs6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tgs6 to javafx.fxml;
    exports com.example.tgs6;
}