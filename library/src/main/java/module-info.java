module io.github.devcarmelo.guicefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;

    exports io.github.devcarmelo.guicefx;
    opens io.github.devcarmelo.guicefx to com.google.guice, javafx.fxml;
}