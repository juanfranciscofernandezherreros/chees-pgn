package com.example.chesspgn.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChessGameReproducer {

    /**
     * Genera el contenido PGN basado en los movimientos de la base de datos.
     *
     * @param moves Lista de movimientos en orden.
     * @return Contenido PGN como String.
     */
    public String generatePGNFromMoves(List<String> moves) {
        StringBuilder pgnBuilder = new StringBuilder();
        int moveNumber = 1;

        for (int i = 0; i < moves.size(); i++) {
            if (i % 2 == 0) {
                // Nuevo número de turno
                pgnBuilder.append(moveNumber).append(". ");
                moveNumber++;
            }
            // Añadir el movimiento
            pgnBuilder.append(moves.get(i)).append(" ");
        }

        return pgnBuilder.toString().trim();
    }
}
