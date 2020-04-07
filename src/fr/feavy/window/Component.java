package fr.feavy.window;

public interface Component<T, C> {
    void value(T newValue);
    T value();

    C component();
}
