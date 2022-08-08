module com.carmelo.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.google.guice;
    requires io.github.devcarmelo.guicefx;
    requires org.jfxtras.styles.jmetro;

    opens com.carmelo.example to com.google.guice, javafx.fxml;
    opens com.carmelo.example.view to io.github.devcarmelo.guicefx, com.google.guice, javafx.fxml;

    exports com.carmelo.example;
}