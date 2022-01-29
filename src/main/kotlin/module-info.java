module com.strwatcher.noder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.swing;
    requires opencv;
    requires com.google.gson;

    opens com.gadstvo69.photo_editor to javafx.fxml, com.google.gson;
    exports com.gadstvo69.photo_editor;
    opens com.gadstvo69.photo_editor.base to javafx.fxml, com.google.gson;
    exports com.gadstvo69.photo_editor.base;
    opens com.gadstvo69.photo_editor.nodes to javafx.fxml, com.google.gson;
    exports com.gadstvo69.photo_editor.nodes;
    opens com.gadstvo69.photo_editor.nodes.edit_nodes to javafx.fxml, com.google.gson;
    opens com.gadstvo69.photo_editor.nodes.filter_nodes to javafx.fxml, com.google.gson;
    opens com.gadstvo69.photo_editor.serialization to com.google.gson;
}