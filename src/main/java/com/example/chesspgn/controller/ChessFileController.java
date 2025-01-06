package com.example.chesspgn.controller;

import com.example.chesspgn.dto.FilePathRequest;
import com.example.chesspgn.service.ChessGameService;
import com.example.chesspgn.wrapper.ChessGameWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/chess")
public class ChessFileController {

    @Autowired
    private ChessGameService service;

    @PostMapping("/read")
    public ChessGameWrapper readPgnFile(@RequestBody FilePathRequest request) {
        try {
            String filePath = request.getFilePath();
            Path path = Paths.get(filePath);
            String content = Files.readString(path);
            return service.parseGames(content);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo PGN: " + e.getMessage());
            return null;
        }
    }
}
