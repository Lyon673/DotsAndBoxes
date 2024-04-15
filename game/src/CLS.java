package src;

import java.io.IOException;

/** this class is designed to clear screen whenever the board changes*/

public class CLS {
    public static void cls(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}