package src;
public class Edge {
    private Player belonging;
    private  Box[] neighborBox;

    /** "flag" means whether the edge is selected */
    private boolean flag;

    public Edge() {
        this.belonging = null;
        this.neighborBox = new Box[2];
        this.flag = false;
    }

    public void setFlag(boolean b){
        flag = b;
    }

    public boolean Flag(){
        return flag;
    }

    public void addNeighbor (Box b) {
        if(neighborBox[0] == null) {
            neighborBox[0] = b;
        } else {
            neighborBox[1] = b;
        }
    }

    public void Belongto(Player someone) {
        belonging = someone;
    }

    public Player Owner() {
        return belonging;
    }

    public Box[] neighborBox() {
        return neighborBox;
    }

}
