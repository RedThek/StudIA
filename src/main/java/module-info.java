module cm.enspm.studia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.desktop;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.apache.pdfbox;
    requires org.apache.fontbox;

    opens cm.enspm.studia to javafx.fxml;
    exports cm.enspm.studia;
}