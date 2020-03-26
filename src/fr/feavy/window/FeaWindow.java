package fr.feavy.window;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FeaWindow {
    private final JFrame frame;
    private final JPanel contentPane;

    private JPanel currentGroup;

    private List<FeaData> dataList = new ArrayList<>();

    public FeaWindow(String title) {
        this.frame = new JFrame(title);
        this.contentPane = new JPanel();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setContentPane(this.contentPane);
    }

    public void startGroupColumn() {
        currentGroup = new JPanel(new GridLayout(0, 1));
        contentPane.add(currentGroup);
    }

    public void startGroupLine() {
        currentGroup = new JPanel(new GridLayout(1, 0));
        contentPane.add(currentGroup);
    }

    public void endGroup() {

    }

    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     */
    public StringData add(String value, String caption) {
        return add(value, caption, true, false, null);
    }

    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData add(String value, String caption, boolean editable) {
        return add(value, caption, editable, false, null);
    }

    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData add(String value, String caption, boolean editable, Consumer<StringData> onEdit) {
        return add(value, caption, editable, false, onEdit);
    }

    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData add(String value, String caption, boolean editable, boolean isPassword) {
        return add(value, caption, editable, isPassword, null);
    }

    /**
     * Add a new textfield in this window
     * @param value Value of this textfield
     * @param caption Label of this textfield
     * @param editable Is textfield editable ? Default : True
     * @param onEdit Called when textfield's value changes.
     */
    public StringData add(String value, String caption, boolean editable, boolean isPassword, Consumer<StringData> onEdit) {
        JLabel label = new JLabel(caption);
        currentGroup.add(label);

        final JTextField textField = isPassword ? new JPasswordField(value, 16) : new JTextField(value, 16);
        textField.setEditable(editable);

        StringData data = new StringData(dataList.size(), textField, value, textField::setText);

        textField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            data.value = textField.getText();
            if(onEdit != null)
                onEdit.accept(data);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            data.value = textField.getText();
            if(onEdit != null)
                onEdit.accept(data);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            data.value = textField.getText();
            if(onEdit != null)
                onEdit.accept(data);
        }
    });

        currentGroup.add(textField);
        dataList.add(data);
        return data;
    }

    public StringData add(String buttonLabel, Consumer<StringData> onClick) {
        JButton button = new JButton(buttonLabel);
        StringData data = new StringData(dataList.size(), button, buttonLabel, button::setText);
        button.addActionListener(e -> onClick.accept(data));
        currentGroup.add(button);
        dataList.add(data);
        return data;
    }

    public <T extends FeaData> FeaData get(int id) {
        return (T)dataList.get(id);
    }

    public void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
