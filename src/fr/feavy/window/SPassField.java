package fr.feavy.window;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.function.Consumer;

public class SPassField extends STextField{
    public SPassField(JPanel panel, String caption, String value) {
        super(panel, new JLabel(caption), new JPasswordField(value));
    }

    public SPassField onChange(Consumer<String> callback) {
        super.onChange(callback);
        return this;
    }
}
