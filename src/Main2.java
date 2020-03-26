import fr.feavy.window.FeaWindow;
import fr.feavy.window.StringData;

public class Main2 {
    private static StringData status = null;
    private static StringData button = null;

    public static void main(String[] args) {
        FeaWindow window = new FeaWindow("Connexion");

        window.startGroupLine();
        window.add("", "Pseudo :");
        window.add("", "Passe : ");

        window.add("OK", () -> {

        });

        window.show();
    }
}
