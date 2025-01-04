package com.example.chesspgn.model;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Entity
public class ChessGameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String event;
    private String site;
    private String date;
    private String round;
    private String whitePlayer;
    private String blackPlayer;
    private String result;

    @ElementCollection
    private List<String> moves;
}

