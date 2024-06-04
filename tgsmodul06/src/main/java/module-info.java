module org.example.TugasModul6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.TgsModul6 to javafx.fxml;
    exports org.example.TgsModul6;
}
