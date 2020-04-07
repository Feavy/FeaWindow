package fr.feavy.window;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.function.Consumer;

public class STextField {
    private JLabel captionLabel;
    private JTextField valueField;

    public STextField(JPanel panel, String caption, String value) {
        captionLabel = new JLabel(caption);
        panel.add(captionLabel);
        valueField = new JTextField(value);
        panel.add(valueField);
    }

    protected STextField(JPanel panel, JLabel captionLabel, JTextField valueField) {
        this.captionLabel = captionLabel;
        panel.add(captionLabel);
        this.valueField = valueField;
        panel.add(valueField);
    }

    public String caption() {
        return captionLabel.getText();
    }

    public void caption(String caption) {
        captionLabel.setText(caption);
    }

    public String value() {
        return valueField.getText();
    }

    public void value(String value) {
        valueField.setText(value);
    }

    public STextField onChange(Consumer<String> callback) {
        valueField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                callback.accept(value());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                callback.accept(value());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                callback.accept(value());
            }
        });
        return this;
    }
}
