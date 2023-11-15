package com.seser.mancalagame.domain.entities;

import com.seser.mancalagame.domain.enums.GameStatus;
import com.seser.mancalagame.domain.enums.PlayerType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

import static com.seser.mancalagame.utilities.Constants.GAME_COLLECTION_NAME;

/**
 * @author Eser
 * This class is an entity for games collection.
 */
@Builder
@Data
@Document(GAME_COLLECTION_NAME)
public class GameEntity {
    @Id
    private UUID uuid;
    private GameStatus status;
    private int[] board;
    private int totalTurn;
    private PlayerType playerTurn;
    private PlayerEntity firstPlayer;
    private PlayerEntity secondPlayer;
    private String winnerPlayer;
}
