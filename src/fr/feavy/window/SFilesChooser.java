package fr.feavy.window;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Optional;

public class SFilesChooser {
    protected JFileChooser fileChooser;
    protected JPanel panel;

    public SFilesChooser(JPanel panel, String title) {
        this.panel = panel;
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(false);
    }

    public SFilesChooser extensions(String description, boolean allFilesAvailable, String ...extensions) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extensions);
        if(!allFilesAvailable)
            fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        return this;
    }

    public SFilesChooser extensions(String description, String ...extensions) {
        return this.extensions(description, false, extensions);
    }

    public Optional<File[]> value() {
        fileChooser.showOpenDialog(panel);
        return Optional.ofNullable(fileChooser.getSelectedFiles());
    }
}
