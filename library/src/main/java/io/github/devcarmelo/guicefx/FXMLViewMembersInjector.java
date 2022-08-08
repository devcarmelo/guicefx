package io.github.devcarmelo.guicefx;

import com.google.inject.MembersInjector;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

class FXMLViewMembersInjector<T> implements MembersInjector<T> {

    private final Field field;
    private FXMLLoader loader;

    FXMLViewMembersInjector(Field field) {
        this.field = field;
        field.setAccessible(true);
    }

    public void injectMembers(T t) {
        try {

            this.loader = new FXMLLoader();

            Optional<Method> resolveNameMethod = getMethodAnnotatedWith(t.getClass(), FXMLLocation.class);
            resolveNameMethod.ifPresentOrElse(
                    method -> initCustomLocation(method, t),
                    () -> initDefaultLocation(t.getClass()));

            loader.setControllerFactory(cf -> t);
            loader.load();

            field.set(t, loader.getRoot());

        } catch (IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initDefaultLocation(Class<?> controllerClass) {

        String pack = controllerClass.getPackageName();
        String name = controllerClass.getName();

        String fileName = name.replace(pack, "")
                .replace(".", "")
                .replace("Controller", "")
                .toLowerCase() + "-view.fxml";

        URL url = controllerClass.getResource(fileName);
        loader.setLocation(url);
    }

    private void initCustomLocation(Method method, Object obj) {
        try {
            method.setAccessible(true);
            String viewName = (String) method.invoke(obj);
            loader.setLocation(new URL(viewName));
        } catch (MalformedURLException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Method> getMethodAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {

        Method method = null;
        Class<?> klass = type;

        while (klass != Object.class) {
            // need to traverse a type hierarchy in order to process methods from super types
            // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
            for (final Method m : klass.getDeclaredMethods()) {
                if (m.isAnnotationPresent(annotation)) {
                    method = m;
                    break;
                }
            }

            if(method != null) break;

            // move to the upper class in the hierarchy in search for more methods
            klass = klass.getSuperclass();
        }

        return Optional.ofNullable(method);
    }
}
