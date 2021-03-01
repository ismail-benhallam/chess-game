package com.sqli.chess.piece;

import com.sqli.chess.Board;
import com.sqli.chess.Spot;

public class Rook extends Piece {
    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        return false;
    }
}
