package fr.feavy.window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Structure : FeaWindow#add(youData, caption, arguments, callback)
 */
public class SimpleFrame {
    private final JFrame frame;
    private final JPanel contentPane;

    private JPanel currentGroup;

    public SimpleFrame(String title) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public STextField textfield(String caption) {
        return this.textfield(caption, "");
    }

    public STextField textfield(String caption, String value) {
        return new STextField(currentGroup, caption, value);
    }

    public SPassField passfield(String caption) {
        return this.passfield(caption, "");
    }

    public SPassField passfield(String caption, String value) {
        return new SPassField(currentGroup, caption, value);
    }

    public SButton button(String value) {
        return new SButton(currentGroup, value);
    }

    public SLabel label(String value) {
        return new SLabel(currentGroup, value);
    }

    public SFileChooser fileChooser(String title) {
        return new SFileChooser(contentPane, title);
    }

    public SFilesChooser filesChooser(String title) {
        return new SFilesChooser(contentPane, title);
    }

    public void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
