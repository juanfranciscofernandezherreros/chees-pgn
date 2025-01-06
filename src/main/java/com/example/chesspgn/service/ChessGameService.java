package com.example.chesspgn.service;

import com.example.chesspgn.wrapper.ChessGame;
import com.example.chesspgn.wrapper.ChessGameWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChessGameService {

    public ChessGameWrapper parseGames(String fileContent) {
        List<ChessGame> games = new ArrayList<>();
        ChessGame currentGame = null;

        String[] lines = fileContent.split("\\r?\\n"); // Manejo de \r y \n

        for (String line : lines) {
            if (line.isBlank()) {
                // Línea en blanco indica el final de un juego
                if (currentGame != null && currentGame.getMoves() != null) {
                    games.add(currentGame); // Añadir el juego actual a la lista
                    currentGame = null; // Reiniciar para el próximo juego
                }
            } else if (line.startsWith("[Event ")) {
                // Inicia un nuevo juego
                if (currentGame != null) {
                    games.add(currentGame); // Guardar el juego anterior si no se ha añadido
                }
                currentGame = new ChessGame(); // Crear un nuevo juego
                currentGame.setEventName(extractValue(line));
            } else if (currentGame != null) {
                // Procesar otros atributos del juego
                if (line.startsWith("[Site ")) {
                    currentGame.setSite(extractValue(line));
                } else if (line.startsWith("[Date ")) {
                    currentGame.setDate(extractValue(line));
                } else if (line.startsWith("[Round ")) {
                    currentGame.setRound(extractValue(line));
                } else if (line.startsWith("[White ")) {
                    currentGame.setWhitePlayer(extractValue(line));
                } else if (line.startsWith("[Black ")) {
                    currentGame.setBlackPlayer(extractValue(line));
                } else if (line.startsWith("[Result ")) {
                    currentGame.setResult(extractValue(line));
                } else if (line.startsWith("[WhiteElo ")) {
                    currentGame.setWhiteElo(extractValue(line));
                } else if (line.startsWith("[BlackElo ")) {
                    currentGame.setBlackElo(extractValue(line));
                } else if (line.startsWith("[ECO ")) {
                    currentGame.setEco(extractValue(line));
                } else if (line.matches("^\\d+\\.\\s?.*")) {
                    // Si la línea contiene jugadas, las agregamos al campo "moves"
                    String moves = currentGame.getMoves() == null ? "" : currentGame.getMoves() + " ";
                    currentGame.setMoves(moves + line.trim());
                }
            }
        }

        // Añadir el último juego si no está vacío
        if (currentGame != null) {
            games.add(currentGame);
        }

        return new ChessGameWrapper(games);
    }

    private String extractValue(String line) {
        return line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
    }
}


