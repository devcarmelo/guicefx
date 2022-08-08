package io.github.devcarmelo.guicefx;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import javafx.scene.Node;
import java.lang.reflect.Field;

class FXMLViewTypeListener implements TypeListener {
    public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
        Class<?> clazz = typeLiteral.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() == Node.class && field.isAnnotationPresent(FXMLView.class)) {
                    typeEncounter.register(new FXMLViewMembersInjector<T>(field));
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}