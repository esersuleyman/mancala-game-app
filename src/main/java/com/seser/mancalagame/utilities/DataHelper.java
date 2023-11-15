package com.seser.mancalagame.utilities;

import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.enums.GameStatus;
import com.seser.mancalagame.domain.enums.PlayerType;
import com.seser.mancalagame.domain.exceptions.GameNotFoundException;
import com.seser.mancalagame.domain.exceptions.InvalidGameStateException;
import com.seser.mancalagame.domain.exceptions.InvalidMoveException;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.domain.request.GameStartRequest;
import com.seser.mancalagame.domain.response.GameResponse;
import com.seser.mancalagame.domain.response.GameStartResponse;
import com.seser.mancalagame.domain.response.PlayerResponse;

import java.util.UUID;

import static com.seser.mancalagame.domain.enums.GameStatus.PROGRESS;
import static com.seser.mancalagame.domain.enums.PlayerType.FIRST;

/**
 * @author Eser
 * This class produces data for service layer and also unit tests.
 */
public class DataHelper {

    public static final UUID GAME_UUID = UUID.randomUUID();
    public static final UUID FIRST_PLAYER_UUID = UUID.randomUUID();
    public static final UUID SECOND_PLAYER_UUID = UUID.randomUUID();
    public static int[] BOARD = {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
    public static final String FIRST_PLAYER = "FIRST PLAYER";
    public static final String SECOND_PLAYER = "SECOND PLAYER";

    public GameEntity gameEntity() {
        return GameEntity.builder()
                .uuid(GAME_UUID)
                .board(BOARD)
                .playerTurn(FIRST)
                .status(GameStatus.START)
                .firstPlayer(firstPlayerEntity())
                .secondPlayer(secondPlayerEntity())
                .winnerPlayer(FIRST_PLAYER)
                .build();
    }

    public GameEntity gameEntityEnd() {
        return GameEntity.builder()
                .uuid(GAME_UUID)
                .board(BOARD)
                .playerTurn(FIRST)
                .status(GameStatus.END)
                .firstPlayer(firstPlayerEntity())
                .secondPlayer(secondPlayerEntity())
                .winnerPlayer(FIRST_PLAYER)
                .build();
    }

    public GameEntity gameEntityForEnd() {
        return GameEntity.builder()
                .uuid(UUID.randomUUID())
                .board(getInitBoardForEnd())
                .playerTurn(FIRST)
                .status(PROGRESS)
                .firstPlayer(firstPlayerEntity())
                .secondPlayer(secondPlayerEntity())
                .build();
    }

    public int[] getInitBoardForEnd() {
        return new int[]{0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 6, 10};
    }

    public int[] getInitBoard() {
        return new int[]{6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0};
    }

    public PlayerEntity firstPlayerEntity() {
        return PlayerEntity.builder()
                .uuid(FIRST_PLAYER_UUID)
                .playerType(FIRST)
                .name(FIRST_PLAYER)
                .build();
    }

    public PlayerEntity secondPlayerEntity() {
        return PlayerEntity.builder()
                .uuid(SECOND_PLAYER_UUID)
                .playerType(PlayerType.SECOND)
                .name(SECOND_PLAYER)
                .build();
    }

    public String gamePlayRequest() {
        return GAME_UUID.toString();
    }

    public GameStartRequest gameStartRequest() {
        return GameStartRequest.builder()
                .firstPlayer(FIRST_PLAYER)
                .secondPlayer(SECOND_PLAYER)
                .build();
    }

    public GameMoveRequest gameMoveRequest() {
        return GameMoveRequest.builder()
                .uuid(GAME_UUID.toString())
                .Index(3)
                .currentStones(5)
                .isFinalOnLargePit(false)
                .playerTurn(FIRST)
                .isPlayerBoard(Boolean.TRUE)
                .build();
    }

    public GameMoveRequest invalidGameMoveRequest() {
        return GameMoveRequest.builder()
                .uuid(GAME_UUID.toString())
                .Index(8)
                .currentStones(5)
                .isFinalOnLargePit(false)
                .playerTurn(FIRST)
                .isPlayerBoard(Boolean.TRUE)
                .build();
    }

    public GameStartResponse gameStartResponse() {
        return GameStartResponse.builder()
                .uuid(GAME_UUID)
                .build();
    }

    public GameResponse gameResponse() {
        return GameResponse.builder()
                .uuid(GAME_UUID)
                .board(BOARD)
                .status(GameStatus.START)
                .playerTurn(FIRST)
                .firstPlayer(firstPlayerResponse())
                .secondPlayer(secondPlayerResponse())
                .winnerPlayer(FIRST_PLAYER)
                .build();
    }

    public PlayerResponse firstPlayerResponse() {
        return PlayerResponse.builder()
                .uuid(FIRST_PLAYER_UUID)
                .playerType(FIRST)
                .name(FIRST_PLAYER)
                .build();
    }

    public PlayerResponse secondPlayerResponse() {
        return PlayerResponse.builder()
                .uuid(SECOND_PLAYER_UUID)
                .playerType(PlayerType.SECOND)
                .name(SECOND_PLAYER)
                .build();
    }

    public GameMoveRequest gameMoveRequest(String uuid, PlayerType type, int index) {
        return GameMoveRequest.builder()
                .uuid(uuid)
                .playerTurn(type)
                .Index(index)
                .build();
    }

    public IllegalArgumentException illegalArgumentException() {
        return new IllegalArgumentException();
    }

    public GameNotFoundException gameNotFoundException() {
        return new GameNotFoundException();
    }

    public InvalidGameStateException invalidGameStateException() {
        return new InvalidGameStateException();
    }

    public InvalidMoveException invalidMoveException() {
        return new InvalidMoveException();
    }


    /**
     * Initialization on demand pattern
     * this pattern is alternative of double check locking pattern
     * which not even support lazy loading but also safe to use in
     * multi-processor distributed instances
     */

    private static class InstanceHolder {
        private static final DataHelper INSTANCE = new DataHelper();

        private InstanceHolder() {

        }
    }


    public static DataHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private DataHelper() {
    }
}

