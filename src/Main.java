import fr.feavy.window.FeaWindow;
import fr.feavy.window.StringData;

public class Main {
    public static void main(String[] args) {
        FeaWindow window = new FeaWindow("Connexion");
        window.startGroupColumn();
        StringData pseudo = window.add("", "Pseudo : ");
        StringData pass = window.add("", "Passe : ", true, true);
        window.add("Connexion", (button) -> {
            System.out.println("Connexion !");
            System.out.println("Pseudo : "+pseudo.value());
            pseudo.set("Salut");
            System.out.println("Pseudo : "+pass.value());
            button.set("Connecté !");
        });
        window.show();
    }
}
