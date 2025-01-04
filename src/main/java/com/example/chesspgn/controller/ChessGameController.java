package com.example.chesspgn.controller;

import com.example.chesspgn.model.ChessGameEntity;
import com.example.chesspgn.service.ChessGameReproducer;
import com.example.chesspgn.service.ChessGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/chess")
public class ChessGameController {

    @Autowired
    private ChessGameService chessGameService;

    @Autowired
    private ChessGameReproducer chessGameReproducer;

    // Endpoint para guardar una partida de ajedrez en la base de datos
    @PostMapping("/save")
    public ResponseEntity<ChessGameEntity> saveGame(@RequestBody ChessGameEntity chessGame) {
        ChessGameEntity savedGame = chessGameService.saveGame(chessGame);
        return ResponseEntity.ok(savedGame);
    }

    // Endpoint para generar un archivo PGN basado en una partida guardada
    @GetMapping("/{id}/generate-pgn")
    public ResponseEntity<PathResource> generatePGN(@PathVariable Long id) throws IOException {
        // Recuperar la partida desde la base de datos
        ChessGameEntity chessGame = chessGameService.getGameById(id);

        // Generar el contenido PGN
        String pgnContent = chessGameService.generatePGN(chessGame);

        // Guardar el archivo PGN
        String fileName = "chess_game_" + id + ".pgn";
        Path filePath = chessGameService.savePGNToFile(pgnContent, fileName);

        // Preparar el archivo para descarga
        PathResource resource = new PathResource(filePath);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(resource);
    }

    /**
     * Reproduce una partida y genera el formato PGN.
     *
     * @param id El ID de la partida.
     * @return Contenido PGN como respuesta.
     */
    @GetMapping("/{id}/replay")
    public ResponseEntity<String> replayGame(@PathVariable Long id) {
        List<String> moves = chessGameService.getMovesByGameId(id);
        String pgnContent = chessGameReproducer.generatePGNFromMoves(moves);
        return ResponseEntity.ok(pgnContent);
    }
}
