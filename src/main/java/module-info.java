module cm.enspm.studia {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;
    requires org.controlsfx.controls;
    requires org.apache.pdfbox;
    requires org.apache.fontbox;

    opens cm.enspm.studia to javafx.fxml;
    exports cm.enspm.studia;
}