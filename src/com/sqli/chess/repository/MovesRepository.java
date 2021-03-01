package com.sqli.chess.repository;

import com.sqli.chess.Move;

public interface MovesRepository {

    void clearPlayedMoves();

    boolean add(Move move);
}
