package mayton.chess;

public class RestrictedTorusQueensDesk extends RestrictedQueensDesk {

    private boolean[] cycledHorizontal;
    private boolean[] cycledVertical;
    private boolean[] cycledLeftDiagonal;
    private boolean[] cycledRightDiagonal;

    public RestrictedTorusQueensDesk(int size) {
        super(size);
    }

    @Override
    public boolean tryToSetQueen(int x, int y) {
        return false;
    }

}
