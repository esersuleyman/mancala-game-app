package com.seser.mancalagame.domain.response;


import com.seser.mancalagame.domain.enums.PlayerType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * @author Eser
 * This class is a response DTO for creating a player.
 */
@Builder
@Data
public class PlayerResponse {
    private UUID uuid;
    private String name;
    private PlayerType playerType;
}
