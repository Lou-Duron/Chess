public class Action {
    Square start, end;
    Piece piece;

    public Action(Square start, Square end, Piece piece){
        this.start = start;
        this.end = end;
        this.piece = piece;
    }
}
