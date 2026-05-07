module cm.enspm.studia {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;
    requires org.controlsfx.controls;
    requires org.apache.pdfbox;
    requires org.apache.fontbox;

    requires java.sql;

    opens cm.enspm.studia to javafx.fxml;
    opens cm.enspm.studia.ui.controller to javafx.fxml;
    opens cm.enspm.studia.repository to java.sql;
    exports cm.enspm.studia;
}