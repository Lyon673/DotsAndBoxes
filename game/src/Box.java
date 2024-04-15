package src;


public class Box {
    private Player belonging = null;
    private Edge[] edges = new Edge[4];

    Box(int row, int col, Edge[][] colEdge, Edge[][] rowEdge){
        /* "row" and "col" represent the position of the box.
         * "belonging" represents the box belongs to which of the player.
         * "edges" represents the four edges around the box in four different directions.
         * 0 means upper one. 1 means the lower one. 2 means the left one. 3 means the right one.
         */

        this.edges[0] = rowEdge[col][row+1];
        this.edges[1] = rowEdge[col][row];
        this.edges[2] = colEdge[col][row];
        this.edges[3] = colEdge[col+1][row];

        for (int i = 0; i < 4; i++) {
            this.edges[i].addNeighbor(this);
        }
    }

    public Edge getEdge(String direction) {
        return switch (direction) {
            case "upper" -> edges[0];
            case "lower" -> edges[1];
            case "left" -> edges[2];
            case null, default -> edges[3];
        };
    }
    
    public void Belongto(Player someone) {
        belonging = someone;
        someone.addscore();
    }

    public Player Owner() {
        return belonging;
    }

    public boolean checkFourEdge(){
        int num = 0;
        for (int i = 0; i < 4; i++) {
            if(edges[i].Owner() != null) {
                num++;
            }
        }
        return num == 4;
    }

}
