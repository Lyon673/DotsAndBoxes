package src;

public class Player {
    private int score;

    Player() {
        this.score = 0;
    }

    public void addscore() {
        score+=1;
    }

    public int score() {
        return score;
    }

    public void setzero() {
        score = 0;
    }
}
