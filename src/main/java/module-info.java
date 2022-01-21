module com.pjeskiem_i_mieczem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.pjeskiem_i_mieczem to javafx.fxml;
    exports com.pjeskiem_i_mieczem;
}