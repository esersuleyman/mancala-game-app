package com.seser.mancalagame.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seser.mancalagame.domain.enums.PlayerType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Eser
 * This class is a request DTO for creating a player with given information.
 */
@Builder
@Data
public class PlayerRequest {
    @NotNull(message = "Player uuid required")
    private UUID uuid;
    @NotNull(message = "Player name required")
    private String name;
    @NotNull(message = "Player type required")
    @JsonProperty(value = "player_type")
    private PlayerType playerType;
}
