package com.example.chesspgn.wrapper;

import java.util.List;

public class ChessGameWrapper {
    private List<ChessGame> games;

    public ChessGameWrapper() {
    }

    public ChessGameWrapper(List<ChessGame> games) {
        this.games = games;
    }

    public List<ChessGame> getGames() {
        return games;
    }

    public void setGames(List<ChessGame> games) {
        this.games = games;
    }
}
