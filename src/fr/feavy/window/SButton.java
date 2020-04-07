package fr.feavy.window;

import javax.swing.*;

public class SButton implements Component<String, JButton> {
    private JButton button;

    public SButton(JPanel panel, String value) {
        this.button = new JButton(value);
        panel.add(this.button);
    }

    @Override
    public void value(String newValue) {
        this.button.setText(newValue);
    }

    @Override
    public String value() {
        return this.button.getText();
    }

    @Override
    public JButton component() {
        return this.button;
    }

    public SButton onClick(Runnable runnable) {
        this.button.addActionListener(e -> runnable.run());
        return this;
    }
}
