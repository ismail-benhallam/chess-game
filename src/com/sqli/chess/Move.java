package com.sqli.chess;

import com.sqli.chess.piece.Piece;

public class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;
    private boolean castlingMove = false;

    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
    }

    public void updatePiecesSpots() {
        this.setPieceAtEndSpot(this.getPieceAtStartSpot());
        this.setPieceAtStartSpot(null);
    }

    public Piece getPieceAtStartSpot() {
        return start.getPiece();
    }

    public Piece getPieceAtEndSpot() {
        return end.getPiece();
    }

    public void setPieceAtStartSpot(Piece piece) {
        start.setPiece(piece);
    }

    public void setPieceAtEndSpot(Piece piece) {
        end.setPiece(piece);
    }

    public boolean isCastlingMove() {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }

    public void setPieceKilled(Piece pieceKilled) {
        this.pieceKilled = pieceKilled;
    }
}
