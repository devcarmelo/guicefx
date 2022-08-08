package com.carmelo.example;

import com.google.inject.*;
import io.github.devcarmelo.guicefx.JavaFXModule;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class AppEntryPoint extends Application {

    @Override
    public void start(Stage stage) {

        Injector injector = Guice.createInjector(new JavaFXModule());
        HelloController controller = injector.getInstance(HelloController.class);

        JMetro jMetro = new JMetro(Style.LIGHT);

        Scene scene = new Scene((Parent) controller.getRoot(), 640, 420);
        jMetro.setScene(scene);
        stage.setTitle("Hello GuiceFX!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}