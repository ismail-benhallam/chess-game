package com.sqli.chess.repository;

import com.sqli.chess.Move;

import java.util.List;

public class DefaultMovesRepository implements MovesRepository {

    List<Move> playedMoves;

    @Override
    public void clearPlayedMoves() {
        playedMoves.clear();
    }

    @Override
    public boolean add(Move move) {
        return playedMoves.add(move);
    }
}
