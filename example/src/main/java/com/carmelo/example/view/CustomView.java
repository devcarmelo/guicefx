package com.carmelo.example.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomView extends CustomController implements Initializable {

    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webView.getEngine().load("https://google.com");
    }
}
