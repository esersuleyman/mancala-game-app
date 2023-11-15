package com.seser.mancalagame.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seser.mancalagame.domain.enums.PlayerType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @author Eser
 * This class is a request DTO which contains game move actions and current game values.
 */
@Builder
@Data
public class GameMoveRequest {
    @NotNull(message = "Game id required")
    @JsonProperty(value = "uuid")
    private String uuid;
    @JsonProperty(value = "player_type")
    @NotNull(message = "Player type required")
    private PlayerType playerTurn;
    private int Index;
    private int currentStones;
    private boolean isPlayerBoard = Boolean.TRUE;
    private boolean isFinalOnLargePit;
}
