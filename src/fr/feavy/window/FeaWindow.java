package fr.feavy.window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Structure : FeaWindow#add(youData, caption, arguments, callback)
 */
public class FeaWindow {
    private final JFrame frame;
    private final JPanel contentPane;

    private JPanel currentGroup;

    private List<FeaData> dataList = new ArrayList<>();

    public FeaWindow(String title) {
        this.frame = new JFrame(title);
        this.contentPane = new JPanel();
        //contentPane.setLayout(new GridLayout(0, 1));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        currentGroup = contentPane;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setContentPane(this.contentPane);
    }

    public void startGroupColumn() {
        JPanel newGroup = new JPanel();
        newGroup.setLayout(new BoxLayout(newGroup, BoxLayout.Y_AXIS));
        currentGroup.add(newGroup);
        currentGroup = newGroup;
    }

    public void startGroupLine() {
        JPanel newGroup = new JPanel();
        newGroup.setLayout(new BoxLayout(newGroup, BoxLayout.X_AXIS));
        currentGroup.add(newGroup);
        currentGroup = newGroup;
    }

    public void endGroup() {

    }

    public StringData add(String text) {
        JLabel label = new JLabel(text);
        currentGroup.add(label);
        StringData data = new StringData(dataList.size(), label, text, label::setText);
        dataList.add(data);
        return data;
    }

    /**
     * Add a new textfield in this window
     *
     * @param value   Value of this textfield
     * @param caption Label of this textfield
     */
    public StringData add(String value, String caption) {
        return add(value, caption, true, false, null);
    }

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData add(String value, String caption, boolean editable) {
        return add(value, caption, editable, false, null);
    }

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData add(String value, String caption, boolean editable, Consumer<String> onEdit) {
        return add(value, caption, editable, false, onEdit);
    }

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData add(String value, String caption, boolean editable, boolean isPassword) {
        return add(value, caption, editable, isPassword, null);
    }

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     * @param editable Is textfield editable ? Default : True
     * @param onEdit   Called when textfield's value changes.
     */
    public StringData add(String value, String caption, boolean editable, boolean isPassword, Consumer<String> onEdit) {
        JLabel label = new JLabel(caption);
        currentGroup.add(label);

        final JTextField textField = isPassword ? new JPasswordField(value, 16) : new JTextField(value, 16);
        textField.setEditable(editable);

        StringData data = new StringData(dataList.size(), textField, value, textField::setText);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                data.value = textField.getText();
                if (onEdit != null)
                    onEdit.accept(textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                data.value = textField.getText();
                if (onEdit != null)
                    onEdit.accept(textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                data.value = textField.getText();
                if (onEdit != null)
                    onEdit.accept(textField.getText());
            }
        });

        currentGroup.add(textField);
        dataList.add(data);
        return data;
    }

    public StringData add(String buttonLabel, Runnable onClick) {
        JButton button = new JButton(buttonLabel);
        StringData data = new StringData(dataList.size(), button, buttonLabel, button::setText);
        button.addActionListener(e -> onClick.run());
        currentGroup.add(button);
        dataList.add(data);
        return data;
    }

    public <T extends FeaData> FeaData get(int id) {
        return (T) dataList.get(id);
    }

    public void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
