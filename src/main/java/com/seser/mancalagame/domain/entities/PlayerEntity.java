package com.seser.mancalagame.domain.entities;

import com.seser.mancalagame.domain.enums.PlayerType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

import static com.seser.mancalagame.utilities.Constants.PLAYER_COLLECTION_NAME;

/**
 * @author Eser
 * This class is an entity for players collection.
 */
@Builder
@Data
@Document(PLAYER_COLLECTION_NAME)
public class PlayerEntity {
    @Id
    private UUID uuid;
    private String name;
    private PlayerType playerType;
}
