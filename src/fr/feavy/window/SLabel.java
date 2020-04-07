package fr.feavy.window;

import javax.swing.*;

public class SLabel implements Component<String, JLabel>{
    private JLabel label;

    public SLabel(JPanel panel, String label) {
        this.label = new JLabel(label);
        panel.add(this.label);
    }

    public String value() {
        return label.getText();
    }

    public void value(String value) {
        label.setText(value);
    }

    @Override
    public JLabel component() {
        return label;
    }
}
