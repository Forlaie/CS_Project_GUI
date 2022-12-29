module com.example.cs_project_gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs_project_gui to javafx.fxml;
    exports com.example.cs_project_gui;
}