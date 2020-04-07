import fr.feavy.window.*;

import java.io.File;
import java.util.Optional;

public class Main {
    private static SButton button;
    private static SLabel status;

    public static void main(String[] args) {
        SimpleFrame w = new SimpleFrame("Connexion");
        w.newColumnGroup();
        STextField pseudo = w.textfield("Pseudo : ");
        SPassField pass = w.passfield("Passe : ");
        button = w.button("Connexion").onClick(() -> {
            if (pseudo.value().length() == 0 || pass.value().length() == 0) {
                status.value("Erreur : un champ est vide.");
                return;
            }
            status.value("Connecté avec succès !");
            button.component().setEnabled(false);
        });
        status = w.label("Veuillez vous connecter.");
        w.button("...").onClick(() -> {
            Optional<File> textFile = w.fileChooser("Choisissez un fichier texte").extensions("Fichiers texte", true,"txt").value();
            textFile.ifPresent(file -> {
                System.out.println("Le fichier que vous avez selectionné est : "+file.getName());
            });
        });
        w.show();
    }
}
