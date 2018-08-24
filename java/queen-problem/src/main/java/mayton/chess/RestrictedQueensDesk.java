package mayton.chess;

public abstract class RestrictedQueensDesk extends QueensDesk {

    public RestrictedQueensDesk(int size) {
        super(size);
    }

    public abstract boolean tryToSetQueen(int x, int y);

}
