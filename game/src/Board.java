package src;

public class Board {
    private int collen;
    private int rowlen;
    private Box[][] BoxArray;
    public Edge[][] colEdge;
    public Edge[][] rowEdge;

    private Side viewPerspective = Side.NORTH;

    private boolean isEdgeEmpty;

    private int EdgeUsed;

    public Board() {
        isEdgeEmpty = false;
        EdgeUsed = 0;

        collen = 6;
        rowlen = 4;
        BoxArray = new Box[collen][rowlen];
        colEdge = new Edge[collen + 1][rowlen];
        rowEdge = new Edge[collen][rowlen + 1];

        InitialEdge(collen+1, rowlen, colEdge);
        InitialEdge(collen, rowlen+1, rowEdge);
        for (int i = 0; i < collen; i++) {
            for (int j = 0; j < rowlen; j++) {
                BoxArray[i][j] = new Box(j, i, colEdge, rowEdge);
            }
        }
    }

    private void InitialEdge(int col, int row, Edge[][] EdgeArray) {
        for (int i = 0; i < col; i ++) {
            for (int j = 0 ; j < row; j++) {
                EdgeArray[i][j] = new Edge();
            }
        }
    }
    public Box box(int col, int row) {
        return BoxArray[col][row];
    }

    public int[] size() {
        return new int[]{collen, rowlen};
    }


    /** return whether any of the elements in EdgeArray is empty*/
    public boolean isEdgeEmpty() {
        return isEdgeEmpty;
    }

    public void setViewingPerspective(Side s) {
        viewPerspective = s;
    }


    public void clear() {
        InitialEdge(collen+1, rowlen, colEdge);
        InitialEdge(collen, rowlen+1, rowEdge);
        for (int i = 0; i < collen; i++) {
            for (int j = 0; j < rowlen; j++) {
                BoxArray[i][j] = new Box(j, i, colEdge, rowEdge);
            }
        }
    }

    public void renewalGameOverFlag() {
        EdgeUsed += 1;
        if(EdgeUsed == (collen+1)*rowlen + (rowlen+1)*collen) {
            isEdgeEmpty = true;
        }
    }
}
