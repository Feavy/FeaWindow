import fr.feavy.window.FeaWindow;
import fr.feavy.window.StringData;

public class Main {
    private static StringData status = null;
    private static StringData button = null;

    public static void main(String[] args) {
        FeaWindow window = new FeaWindow("Connexion");

        window.startGroupColumn();
        StringData pseudo = window.add("", "Pseudo : ");
        StringData pass = window.add("", "Passe : ", true, true);

        button = window.add("Connexion", () -> {
            if(pseudo.value().length() == 0 || pass.value().length() == 0) {
                status.set("Erreur : un champ est vide.");
                return;
            }
            status.set("Connecté avec succès !");
            button.component().setEnabled(false);
        });
        status = window.add("Veuillez vous connecter.");
        window.endGroup();

        window.show();
    }
}
