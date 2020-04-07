import fr.feavy.window.SButton;
import fr.feavy.window.SLabel;
import fr.feavy.window.SimpleFrame;

public class Main2 {
    private static SLabel status = null;
    private static SButton button = null;

    public static void main(String[] args) {
        SimpleFrame window = new SimpleFrame("Connexion");

        window.newLineGroup();
        window.textfield("Pseudo :");
        window.passfield("Passse :");
        window.button("OK");

        window.show();
    }
}
