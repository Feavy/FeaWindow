package fr.feavy.window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Optional;

public class SFileChooser {
    protected JFileChooser fileChooser;
    protected JPanel panel;

    public SFileChooser(JPanel panel, String title) {
        this.panel = panel;
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }

    public SFileChooser extensions(String description, boolean allFilesAvailable, String ...extensions) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extensions);
        if(!allFilesAvailable)
            fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        return this;
    }

    public SFileChooser extensions(String description, String ...extensions) {
        return this.extensions(description, false, extensions);
    }

    public Optional<File> value() {
        fileChooser.showOpenDialog(panel);
        return Optional.ofNullable(fileChooser.getSelectedFile());
    }
}
