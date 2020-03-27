import fr.feavy.window.FeaWindow;
import fr.feavy.window.StringData;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        FeaWindow window = new FeaWindow("Connexion") {
            private StringData button;
            private StringData status;
            {
                newColumnGroup();
                StringData pseudo = textfield("", "Pseudo : ");
                StringData pass = passfield("", "Passe : ");
                button = button("Connexion", () -> {
                    if (pseudo.value().length() == 0 || pass.value().length() == 0) {
                        status.set("Erreur : un champ est vide.");
                        return;
                    }
                    status.set("Connecté avec succès !");
                    button.component().setEnabled(false);
                });
                status = label("Veuillez vous connecter.");
            }
        };
        window.show();
    }
}
