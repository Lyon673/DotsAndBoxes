package src;


import java.util.Formatter;
import java.util.Objects;
import java.util.Observable;
public class Model{

    private Board board;

    private boolean gameOver;

    public Player Player1;

    public Player Player2;

    /** the para with "tmp" are designed for locate the edge when players key in.*/
    private int tmpcol;
    private int tmprow;

    public Model() {
        board = new Board();
        Player1 = new Player();
        Player2 = new Player();
        gameOver = false;
        tmpcol = 0;
        tmprow = 0;
    }

    /** Player chooses the edge then it will belong to him.
     * the parameter "col" and "row" are a little different from the definition in other place in this project.
     * here the two edgearrays are combined to only one, whose size is (collen+1) times (2*rowlen+1)
     */
    public boolean[] Choose(int col, int row, Player player) {
        boolean[] ans = new boolean[2];
        Edge edge = FindLocation(col, row);
        if(edge.Owner() != null){
            return ans;
        }
        edge.Belongto(player);
        board.renewalGameOverFlag();
        ans[0] = true;
        ans[1] = checkBoxOwner(edge, player);
        return ans;
    }

    private String whichArray(int col, int row) {
        return row % 2 == 0 ? "row" : "col";
    }

    private Edge FindLocation(int col, int row) {
        String whicharray  = whichArray(col, row);
        int[] size = board.size();
        if(whicharray.equals("row")) {
            row = row/2;
            return board.rowEdge[Math.min(col, size[0]-1)][Math.min(row, size[1])];
        } else {
            row = row/2;
            return board.colEdge[Math.min(col, size[0])][Math.min(row, size[1]-1)];
        }
    }


    /** if the edges around a box are all occupied, then it belongs to the one who owns the surrounding edge at last. */
    public boolean checkBoxOwner(Edge e, Player player) {
        Box[] neighbor = e.neighborBox();
        for (int i = 0; i < 2; i++) {
            if(neighbor[i] != null) {
                if(neighbor[i].checkFourEdge()) {
                    neighbor[i].Belongto(player);
                    return true;
                }
            }
        }

        return false;

    }

    /** The following three func act as the flag of gameover */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    private static boolean checkGameOver(Board b) {
        return AnyEmptyEdge(b);
    }

    private static boolean AnyEmptyEdge(Board b) {
        return b.isEdgeEmpty();
    }

    /** return the size of BoxArray, size[0] for col and size[1] for row*/
    public int[] size() {
        return board.size();
    }

    public boolean gameOver() {
        checkGameOver();
        return gameOver;
    }

    /** clear the board and reset the scores of both two players*/
    public void clear() {
        Player1.setzero();
        Player2.setzero();
        gameOver = false;
        board.clear();
    }

    /** the following functions are unfinished.
     * because some of them are related to the realization of GUI.
     * exactly, "move" is finished while "decorate" and "renew" are unfinished.
     */

    /** @func move: to decorate the edge that the player has chosen when playing the game.
     *  players can keep keying in until entering the "Enter"(for Player1) or "Space"(for player2)
     */
    public void move(String s) {

        switch (s){
            case "\u006A": case "\u0061":
                renew(tmpcol, tmprow);
                tmpcol -= 1;
                decorate(tmpcol, tmprow);
                break;
            case "\u0069": case "\u0077":
                renew(tmpcol, tmprow);
                tmprow += 1;
                decorate(tmpcol, tmprow);
                break;
            case "\u006C": case "\u0064":
                renew(tmpcol, tmprow);
                tmpcol += 1;
                decorate(tmpcol, tmprow);
                break;
            case  "\u006B": case "\u0073":
                renew(tmpcol, tmprow);
                tmprow -= 1;
                decorate(tmpcol, tmprow);
                break;
        }
    }

    /** @func renew: change the particular edge from decorated state to normal state
     * @func decorate: change the particular edge from normal state to decorated state
     */
    public void renew(int col, int row){
        Edge edge = FindLocation(col, row);
        edge.setFlag(false);
    };
    public void renew() {
        Edge edge = FindLocation(tmpcol, tmprow);
        edge.setFlag(false);
    }
    public void decorate(int col, int row){
        Edge edge = FindLocation(col, row);
        edge.setFlag(true);
    };



    public boolean[] Choose(String s){
        if(Objects.equals(s, "\u0070")){
            return Choose(tmpcol, tmprow, Player1);
        } else if (Objects.equals(s, "\u0074")) {
            return Choose(tmpcol, tmprow, Player2);
        }
        return new boolean[]{false, false};
    }

    /** Returns the model as a string. */
    public String toString() {
        Formatter out = new Formatter();
        int[] size = board.size();
        out.format("%n[%n");

        boolean flag = true;
        int rowlen = size[1];
        int collen = size[0];

        /** visualize the topside row of edges */
        for (int col = 0; col < collen ; col++) {
            if(board.rowEdge[col][rowlen].Flag()) {
                out.format("\033[0;33m ——\033[0m");
            }
            else if (board.rowEdge[col][rowlen].Owner() == Player1) {
                out.format("\033[0;31m ——\033[0m");
            } else if (board.rowEdge[col][rowlen].Owner() == Player2) {
                out.format("\033[0;34m ——\033[0m");
            } else {
                out.format(" ——");
            }
        }
        out.format("%n");

        /** visualize the edges and boxes except the topside row of edges */
        for (int row = rowlen-1; row >=0; row--) {
            for (int col = 0; col < collen + 1; col++) {
                if(board.colEdge[col][row].Flag()) {
                    out.format("\033[0;33m\u2502\033[0m");
                }
                else if (board.colEdge[col][row].Owner() == Player1) {
                    out.format("\033[0;31m\u2502\033[0m");
                } else if (board.colEdge[col][row].Owner() == Player2) {
                    out.format("\033[0;34m\u2502\033[0m");
                } else {
                    out.format("\u2502");
                }

                if (col != collen) {
                    if (board.box(col, row).Owner() == Player1) {
                        out.format("\033[0;31m\u2588\u2588\033[0m");
                    } else if (board.box(col, row).Owner() == Player2) {
                        out.format("\033[0;34m\u2588\u2588\033[0m");
                    } else {
                        out.format("  ");
                    }
                }
            }

            out.format("%n");

            for (int col = 0; col < collen; col++) {
                if(board.rowEdge[col][row].Flag()) {
                    out.format("\033[0;33m ——\033[0m");
                }
                else if (board.rowEdge[col][row].Owner() == Player1) {
                    out.format("\033[0;31m ——\033[0m");
                } else if (board.rowEdge[col][row].Owner() == Player2) {
                    out.format("\033[0;34m ——\033[0m");
                } else {
                    out.format(" ——");
                }
            }

            out.format("%n");
        }

        String over = gameOver() ? "over" : "not over";
        String winner;
        if(Player1.score()==Player2.score()){
            winner = "None";
        } else {
            winner = Player1.score() > Player2.score() ? "Player1" : "Player2";
        }
        out.format("] %n%n%n(\033[0;31mPlayer1\033[0m's score: %d) (\033[0;34mPlayer2\033[0m's score: %d) %n", Player1.score(), Player2.score());
        if(over.equals("over")){out.format("(The WINNER is %s) %n", winner);}
        out.format("(Game is %s)", over);
        return out.toString();
    }

    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }

    public void Print(){
        for (int i = 0; i < 50; i++) {
            System.out.println("\n");
        }
        System.out.println(this.toString());

    }
}
