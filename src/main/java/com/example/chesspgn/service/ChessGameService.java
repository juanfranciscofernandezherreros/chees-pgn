package com.example.chesspgn.service;

import com.example.chesspgn.exception.GameNotFoundException;
import com.example.chesspgn.model.ChessGameEntity;
import com.example.chesspgn.repository.ChessGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ChessGameService {

    @Autowired
    private ChessGameRepository chessGameRepository;

    /**
     * Guarda una partida de ajedrez en la base de datos.
     *
     * @param game La entidad que representa la partida.
     * @return La entidad guardada.
     */
    public ChessGameEntity saveGame(ChessGameEntity game) {
        return chessGameRepository.save(game);
    }

    /**
     * Recupera una partida de ajedrez por su ID.
     *
     * @param id El ID de la partida.
     * @return La entidad correspondiente a la partida.
     * @throws RuntimeException Si la partida no se encuentra.
     */
    public ChessGameEntity getGameById(Long id) {
        return chessGameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + id + " not found"));
    }

    /**
     * Genera el contenido PGN para una partida de ajedrez.
     *
     * @param chessGame La entidad que representa la partida.
     * @return El contenido del archivo PGN.
     */
    public String generatePGN(ChessGameEntity chessGame) {
        StringBuilder pgnBuilder = new StringBuilder();

        // Encabezados del PGN
        pgnBuilder.append("[Event \"").append(chessGame.getEvent()).append("\"]\n");
        pgnBuilder.append("[Site \"").append(chessGame.getSite()).append("\"]\n");
        pgnBuilder.append("[Date \"").append(chessGame.getDate()).append("\"]\n");
        pgnBuilder.append("[Round \"").append(chessGame.getRound()).append("\"]\n");
        pgnBuilder.append("[White \"").append(chessGame.getWhitePlayer()).append("\"]\n");
        pgnBuilder.append("[Black \"").append(chessGame.getBlackPlayer()).append("\"]\n");
        pgnBuilder.append("[Result \"").append(chessGame.getResult()).append("\"]\n\n");

        // Movimientos
        int moveNumber = 1;
        for (int i = 0; i < chessGame.getMoves().size(); i++) {
            if (i % 2 == 0) {
                pgnBuilder.append(moveNumber).append(". ");
                moveNumber++;
            }
            pgnBuilder.append(chessGame.getMoves().get(i)).append(" ");
        }

        return pgnBuilder.toString().trim();
    }

    /**
     * Guarda el contenido PGN en un archivo.
     *
     * @param pgnContent El contenido PGN a guardar.
     * @param fileName El nombre del archivo.
     * @return La ruta del archivo guardado.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public Path savePGNToFile(String pgnContent, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, pgnContent.getBytes());
        return path;
    }

    public List<String> getMovesByGameId(Long gameId) {
        ChessGameEntity game = getGameById(gameId);
        return game.getMoves();
    }
}
