package com.carmelo.example.view;

import io.github.devcarmelo.guicefx.BaseController;
import io.github.devcarmelo.guicefx.FXMLLocation;

public abstract class CustomController extends BaseController {

    @FXMLLocation
    private String resolveViewName() {
        String name = getClass().getName();
        name = name.substring(name.lastIndexOf('.') + 1);
        name = name.replace("View", "").toLowerCase();
        return getClass().getResource(name + "View.fxml").toExternalForm();
    }
}
