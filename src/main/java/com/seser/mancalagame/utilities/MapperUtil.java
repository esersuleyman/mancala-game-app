package com.seser.mancalagame.utilities;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.response.GameResponse;
import com.seser.mancalagame.domain.response.GameStartResponse;
import com.seser.mancalagame.domain.response.PlayerResponse;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    public MapperUtil() {
    }

    /**
     * This method maps gameEntity object to gameResponseObject for controller layer.
     * @param gameEntity
     * @return
     */
    public static GameResponse mapGameEntityToGameResponse(GameEntity gameEntity) {
        GameResponse gameResponse = GameResponse.builder().build();
        gameResponse.setBoard(gameEntity.getBoard());
        gameResponse.setFirstPlayer(PlayerResponse.builder().
                uuid(gameEntity.getFirstPlayer().getUuid()).
                name(gameEntity.getFirstPlayer().getName()).
                playerType(gameEntity.getFirstPlayer().getPlayerType())
                .build());
        gameResponse.setSecondPlayer(PlayerResponse.builder().
                uuid(gameEntity.getSecondPlayer().getUuid()).
                name(gameEntity.getSecondPlayer().getName()).
                playerType(gameEntity.getSecondPlayer().getPlayerType())
                .build());
        gameResponse.setStatus(gameEntity.getStatus());
        gameResponse.setPlayerTurn(gameEntity.getPlayerTurn());
        gameResponse.setTotalTurn(gameEntity.getTotalTurn());
        gameResponse.setUuid(gameEntity.getUuid());
        gameResponse.setWinnerPlayer(gameEntity.getWinnerPlayer());
        return gameResponse;
    }

    /**
     * This method maps gameEntity object to gameStartResponse for controller layer.
     * @param gameEntity
     * @return
     */
    public static GameStartResponse mapGameEntityToGameStartResponse(GameEntity gameEntity) {
        return GameStartResponse.builder()
                .uuid(gameEntity.getUuid())
                .build();
    }
}
