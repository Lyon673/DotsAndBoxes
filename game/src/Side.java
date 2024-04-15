package src;

/** this class is unused
 * It just acts as one of assistant functions for GUI realization
 */

public enum Side {
    /** This module define four directions and the next position in class particular direction.
     */

    NORTH(0, 0, 0, 1),
    EAST(0, 1, 1, 0),
    SOUTH(1, 1, 0, -1),
    WEST(1, 0, -1, 0);

    private int row0, col0, drow, dcol;
    Side(int col0, int row0, int dcol, int drow) {
        this.row0 = row0;
        this.col0 = col0;
        this.drow = drow;
        this.dcol = dcol;
    }

    /** The following method to calculate the row and col is a kind of transformation of coordinates*/
    public int col(int c, int r, int size) {
        return col0 * (size - 1) + c * drow + r * dcol;
    }


    public int row(int c, int r, int size) {
        return row0 * (size - 1) - c * dcol + r * drow;
    }


}
