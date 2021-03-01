package com.sqli.chess;

import com.sqli.chess.piece.King;
import com.sqli.chess.piece.Piece;
import com.sqli.chess.repository.MovesRepository;
import com.sqli.chess.validation.PlayerValidator;

public class Game {
    private static final int FIRST_PLAYER_INDEX = 0;
    private static final int SECONDE_PLAYER_INDEX = 1;

    private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;

    private PlayerValidator playerValidator;
    private MovesRepository movesRepository;

    public void initialize(Player p1, Player p2) {
        players[FIRST_PLAYER_INDEX] = p1;
        players[SECONDE_PLAYER_INDEX] = p2;

        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesRepository.clearPlayedMoves();
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) throws IndexOutOfBoundsException {
        Spot startBox = board.getBox(startX, startY);
        Spot endBox = board.getBox(startY, endY);
        Move move = new Move(player, startBox, endBox);
        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getPieceAtStartSpot();
        if (sourcePiece == null) {
            return false;
        }
        // validate player
        if (playerValidator.validate(currentTurn, player, sourcePiece)) {
            return false;
        }
        // validate move?
        if (!sourcePiece.canMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        // kill?
        Piece destPiece = killPieceAtEndSpot(move);

        //castling?
        checkCastling(move, sourcePiece);

        // store the move
        movesRepository.add(move);

        // move piece from the stat box to end box
        move.updatePiecesSpots();

        updateGameStatus(player, destPiece);

        // set the current turn to the other player
        switchCurrentTurn();

        return true;
    }

    private Piece killPieceAtEndSpot(Move move) {
        Piece destPiece = move.getPieceAtEndSpot();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setPieceKilled(destPiece);
        }
        return destPiece;
    }

    private void updateGameStatus(Player player, Piece destPiece) {
        if (destPiece != null && destPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            }
            else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }
    }

    private void checkCastling(Move move, Piece sourcePiece) {
        if (sourcePiece instanceof King && ((King) sourcePiece).isCastlingDone()) {
            move.setCastlingMove(true);
        }
    }

    private void switchCurrentTurn() {
        if (this.currentTurn == players[FIRST_PLAYER_INDEX]) {
            this.currentTurn = players[SECONDE_PLAYER_INDEX];
        } else {
            this.currentTurn = players[FIRST_PLAYER_INDEX];
        }
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}

