package com.example.chesspgn.wrapper;

public class ChessGame {
    private String eventName;
    private String site;
    private String date;
    private String round;
    private String whitePlayer;
    private String blackPlayer;
    private String result;
    private String whiteElo;
    private String blackElo;
    private String eco;
    private String moves;

    public ChessGame() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(String whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(String blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWhiteElo() {
        return whiteElo;
    }

    public void setWhiteElo(String whiteElo) {
        this.whiteElo = whiteElo;
    }

    public String getBlackElo() {
        return blackElo;
    }

    public void setBlackElo(String blackElo) {
        this.blackElo = blackElo;
    }

    public String getEco() {
        return eco;
    }

    public void setEco(String eco) {
        this.eco = eco;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "eventName='" + eventName + '\'' +
                ", site='" + site + '\'' +
                ", date='" + date + '\'' +
                ", round='" + round + '\'' +
                ", whitePlayer='" + whitePlayer + '\'' +
                ", blackPlayer='" + blackPlayer + '\'' +
                ", result='" + result + '\'' +
                ", whiteElo='" + whiteElo + '\'' +
                ", blackElo='" + blackElo + '\'' +
                ", eco='" + eco + '\'' +
                ", moves='" + moves + '\'' +
                '}';
    }
}
