package com.sqli.chess.validation;

import com.sqli.chess.Player;
import com.sqli.chess.piece.Piece;

public class DefaultPlayerValidator implements PlayerValidator {

    public boolean validate(Player currentTurn, Player player, Piece sourcePiece) {
        if (player != currentTurn) {
            return true;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return true;
        }

        return false;
    }
}
