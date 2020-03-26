package fr.feavy.window;

import javax.swing.*;

public class FeaData {
    private final JComponent component;
    private final int id;

    public FeaData(int id, JComponent component) {
        this.component = component;
        this.id = id;
    }

    public JComponent component() {
        return component;
    }

    public int id() {
        return id;
    }
}
