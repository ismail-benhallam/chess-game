package com.sqli.chess.validation;

import com.sqli.chess.Player;
import com.sqli.chess.piece.Piece;

public interface PlayerValidator {

    boolean validate(Player currentTurn, Player player, Piece sourcePiece);
}
