public class Action {
    Square start, end;
    Piece piece;
    Piece promoted;

    public Action(Square start, Square end){
        this.start = start;
        this.end = end;
    }

    public Action(Square start, Square end, Piece piece){
        this.start = start;
        this.end = end;
        this.piece = piece;
    }

    public Action(Square start, Square end, Piece piece, Piece promoted){
        this.start = start;
        this.end = end;
        this.piece = piece;
        this.promoted = promoted;
    }
}
