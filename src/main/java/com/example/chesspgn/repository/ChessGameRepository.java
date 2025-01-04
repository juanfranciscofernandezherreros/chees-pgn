package com.example.chesspgn.repository;

import com.example.chesspgn.model.ChessGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChessGameRepository extends JpaRepository<ChessGameEntity, Long> {
}
