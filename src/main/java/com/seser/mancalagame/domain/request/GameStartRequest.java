package com.seser.mancalagame.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * @author Eser
 * This class is a request DTO for starting a game with given players.
 */
@Builder
@Data
public class GameStartRequest{
    @NotNull(message = "First player name required")
    private String firstPlayer;
    @NotNull(message = "Second player name required")
    private String secondPlayer;
}
