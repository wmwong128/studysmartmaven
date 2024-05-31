module com.studysmartjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.studysmartjavafx to javafx.fxml;
    exports com.studysmartjavafx;
}
