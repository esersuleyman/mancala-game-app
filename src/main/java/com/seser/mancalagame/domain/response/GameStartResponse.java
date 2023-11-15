package com.seser.mancalagame.domain.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Eser
 * This class is a response DTO for starting a game.
 */
@Builder
@Data
public class GameStartResponse {
    private UUID uuid;
}
