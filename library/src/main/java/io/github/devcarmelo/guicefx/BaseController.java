package io.github.devcarmelo.guicefx;

import javafx.scene.Node;

public abstract class BaseController implements View {

    @FXMLView
    protected Node root;

    @Override
    public Node getRoot() {
        return root;
    }

}
