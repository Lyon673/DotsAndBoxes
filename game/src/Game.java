package src;

import java.util.Scanner;

/** IO and GUI controller */
public class Game {

    private Model model;

    private InputSource source;

    private boolean playing;



    public Game(Model model, InputSource source) {
        this.model = model;
        this.source = source;
        this.playing = true;
    }

    boolean playing() {
        return playing;
    }

    void playGame(){
        model.clear();

        int whoToChoose = 0;

        while(playing) {
            if(model.gameOver()) {
                playing = false;
                return;
            }


            model.Print();
            while(whoToChoose == 0) {
                System.out.println("KEY IN:");
                Scanner sc = new Scanner(System.in);
                String cmnd = sc.next();
                System.out.println("ANS:"+cmnd);

                /** to stop while when the player determine which edge to select */
                boolean flag = false;

                switch (cmnd) {
                    case "q":
                        playing = false;
                        return;
                    case "New Game":
                        return;
                    case "\u0069": case "\u006A": case "\u006B": case "\u006C":
                        model.move(cmnd);
                        model.Print();
                        break;
                    case "\u0070":
                        boolean[] success = model.Choose(cmnd);
                        if(success[0]) {
                            model.renew();
                            if(!success[1]){
                                whoToChoose = 1;
                            }
                            flag = true;
                            model.Print();
                        }
                        break;
                    default:
                        break;
                }
                if(flag) {break;}
            }

            while(whoToChoose == 1) {
                System.out.println("KEY IN:");
                Scanner sc = new Scanner(System.in);
                String cmnd = sc.next();
                System.out.println("ANS:"+cmnd);

                boolean flag = false;

                switch (cmnd) {
                    case "Quit":
                        playing = false;
                        return;
                    case "New Game":
                        return;
                    case "\u0077": case "\u0061": case "\u0073": case "\u0064":
                        model.move(cmnd);
                        model.Print();
                        break;
                    case "\u0074":
                        boolean[] success = model.Choose(cmnd);
                        if(success[0]) {
                            model.renew();
                            if(!success[1]){
                                whoToChoose = 0;
                            }
                            flag = true;
                            model.Print();
                        }
                        break;
                    default:
                        break;
                }
                if(flag) {break;}
            }
        }
    }

}
