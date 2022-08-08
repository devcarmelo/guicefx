package com.carmelo.example;

import com.carmelo.example.view.CustomView;
import com.carmelo.example.view.FirstController;
import com.carmelo.example.view.SecondController;
import com.google.inject.Inject;
import io.github.devcarmelo.guicefx.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class HelloController extends BaseController {

    @Inject
    private FirstController firstController;

    @Inject
    private SecondController secondController;

    @Inject
    private CustomView customView;

    @FXML
    private StackPane viewPane;


    public void onBtnSecondClick(ActionEvent actionEvent) {
        changeView(secondController.getRoot());
    }

    public void onBtnFirstClick(ActionEvent actionEvent) {
        changeView(firstController.getRoot());
    }

    public void onBtnCustomClick(ActionEvent actionEvent) {
        changeView(customView.getRoot());
    }

    private void changeView(Node view) {
        viewPane.getChildren().clear();
        viewPane.getChildren().add(view);
    }


}