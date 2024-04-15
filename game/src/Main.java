package src;

/** The main class for the 2048 game. */
public class Main {

    public static void main(String... args) {

        Model model = new Model();

        // unused
        GUI gui;

        gui = new GUI(model);

        GUISource inp = new GUISource(gui);
        // unused

        Game game = new Game(model, inp);

        try {
            while (game.playing()) {
                game.playGame();
            }
        } catch (IllegalStateException exp) {
            System.exit(1);
        }

        System.exit(0);
    }

}
