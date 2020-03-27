import fr.feavy.window.FeaWindow;
import fr.feavy.window.StringData;

public class Main2 {
    private static StringData status = null;
    private static StringData button = null;

    public static void main(String[] args) {
        FeaWindow window = new FeaWindow("Connexion");

        window.newLineGroup();
        window.textfield("", "Pseudo :");
        window.passfield("", "Passe : ");

        window.button("OK", () -> {

        });

        window.show();
    }
}
