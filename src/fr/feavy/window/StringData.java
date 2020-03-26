package fr.feavy.window;

import javax.swing.*;
import java.util.function.Consumer;

public class StringData extends FeaData{
    String value;
    private Consumer<String> onDataChange;

    public StringData(int id, JComponent component, String value, Consumer<String> onDataChange) {
        super(id, component);
        this.value = value;
        this.onDataChange = onDataChange;
    }

    public void set(String value) {
        this.value = value;
        onDataChange.accept(value);
    }

    public String value() {
        return value;
    }
}
