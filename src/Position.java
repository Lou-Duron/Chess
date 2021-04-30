public class Position {
    int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position p){
        return this.x == p.x && this.y == p.y;
    }

    public Position(Position p){
        this.x = p.x;
        this.y = p.y;
    }
}
