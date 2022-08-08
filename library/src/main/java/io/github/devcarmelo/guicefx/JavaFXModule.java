package io.github.devcarmelo.guicefx;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class JavaFXModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new FXMLViewTypeListener());
    }
}
