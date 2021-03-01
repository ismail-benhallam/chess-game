package com.sqli.chess.piece;

import com.sqli.chess.Board;
import com.sqli.chess.Spot;

public class Pawn extends Piece {
    // commit 2
    // Commit 3 and not 2
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        return false;
    }
}
