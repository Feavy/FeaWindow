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

    public void newColumnGroup() {
        JPanel newGroup = new JPanel();
        newGroup.setLayout(new BoxLayout(newGroup, BoxLayout.Y_AXIS));
        //newGroup.setLayout(new GridLayout(0, 1));
        currentGroup.add(newGroup);
        currentGroup = newGroup;
    }

    public void newLineGroup() {
        JPanel newGroup = new JPanel();
        newGroup.setLayout(new BoxLayout(newGroup, BoxLayout.X_AXIS));
        //newGroup.setLayout(new GridLayout(1, 0));

        currentGroup.add(newGroup);
        currentGroup = newGroup;
    }

    public void endGroup() {

    }

    public StringData label(String text) {
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
    public StringData textfield(String value, String caption) {
        return textfield(value, caption, true, false, null);
    }

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData textfield(String value, String caption, boolean editable) {
        return textfield(value, caption, editable, false, null);
    }

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     * @param editable Is textfield editable ? Default : True
     */
    public StringData textfield(String value, String caption, boolean editable, Consumer<String> onEdit) {
        return textfield(value, caption, editable, false, onEdit);
    }

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     * @param editable Is textfield editable ? Default : True
     * @param onEdit   Called when textfield's value changes.
     */
    private StringData textfield(String value, String caption, boolean editable, boolean isPassword, Consumer<String> onEdit) {
        JLabel label = new JLabel(caption);
        label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        currentGroup.add(label);

        final JTextField textField = isPassword ? new JPasswordField(value, 16) : new JTextField(value, 16);
        textField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
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

    /**
     * Add a new textfield in this window
     *
     * @param value    Value of this textfield
     * @param caption  Label of this textfield
     */
    public StringData passfield(String value, String caption) {
        return textfield(value, caption, true, true, null);
    }

    public StringData passfield(String value, String caption, boolean editable) {
        return textfield(value, caption, editable, true, null);
    }

    public StringData passfield(String value, String caption, boolean editable, Consumer<String> onEdit) {
        return textfield(value, caption, editable, true, onEdit);
    }

    public StringData button(String buttonLabel, Runnable onClick) {
        JButton button = new JButton(buttonLabel);
        button.setAlignmentX(JButton.LEFT_ALIGNMENT);
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
