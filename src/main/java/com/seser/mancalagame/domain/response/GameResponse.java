package com.seser.mancalagame.domain.response;

import com.seser.mancalagame.domain.enums.GameStatus;
import com.seser.mancalagame.domain.enums.PlayerType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Eser
 * This class is a response DTO for returning information of game.
 */
@Builder
@Data
public class GameResponse {
    private UUID uuid;
    private GameStatus status;
    private int[] board;
    private PlayerType playerTurn;
    private int totalTurn;
    private PlayerResponse firstPlayer;
    private PlayerResponse secondPlayer;
    private String winnerPlayer;
}
