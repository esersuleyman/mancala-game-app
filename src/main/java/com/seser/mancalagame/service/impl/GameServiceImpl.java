package com.seser.mancalagame.service.impl;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.exceptions.GameNotFoundException;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.domain.response.GameResponse;
import com.seser.mancalagame.domain.response.GameStartResponse;
import com.seser.mancalagame.repository.GameRepository;
import com.seser.mancalagame.service.GameService;
import com.seser.mancalagame.utilities.GameHelper;
import com.seser.mancalagame.utilities.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.seser.mancalagame.domain.enums.GameStatus.PROGRESS;
import static com.seser.mancalagame.domain.enums.GameStatus.START;
import static com.seser.mancalagame.domain.enums.PlayerType.FIRST;
import static com.seser.mancalagame.domain.enums.PlayerType.SECOND;
import static java.util.Optional.ofNullable;

/**
 * @author Eser
 * This class contains service functions for game domain.
 */
@Service
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    private final GameHelper gameHelper = GameHelper.getInstance();

    private GameRepository repository;

    private PlayerServiceImpl playerService;

    private GameEvaluatorServiceImpl evaluatorService;

    private MapperUtil mapperUtil;

    public GameServiceImpl(GameRepository repository, PlayerServiceImpl playerService, GameEvaluatorServiceImpl evaluatorService, MapperUtil mapperUtil) {
        this.repository = repository;
        this.playerService = playerService;
        this.evaluatorService = evaluatorService;
        this.mapperUtil = mapperUtil;
    }

    /**
     * This method starts a new game with given players.
     * @param firstPlayer
     * @param secondPlayer
     * @return
     */
    @Override
    public GameStartResponse start(String firstPlayer, String secondPlayer) {
        PlayerEntity firstPlayerEntity = playerService.create(firstPlayer, FIRST);
        PlayerEntity secondPlayerEntity = playerService.create(secondPlayer, SECOND);
        logger.info("Creating game against players");
        GameEntity startedGameEntity = repository.save(GameEntity
                .builder()
                .uuid(UUID.randomUUID())
                .firstPlayer(firstPlayerEntity)
                .secondPlayer(secondPlayerEntity)
                .status(START)
                .playerTurn(FIRST)
                .board(gameHelper.getInitialBoard())
                .build());
        return mapperUtil.mapGameEntityToGameStartResponse(startedGameEntity);
    }

    /**
     * This method validates a move and updates game with a given move request which contains actions.
     * @param moveRequest
     * @return
     */
    @Override
    public GameResponse updateMove(GameMoveRequest moveRequest) {
        logger.debug("Game {} received for updateMove", moveRequest.getUuid());
        GameEntity game = repository.findByUuid(UUID.fromString(moveRequest.getUuid()));
        game.setStatus(PROGRESS);
        evaluatorService.validateMove(moveRequest, game.getBoard());
        GameEntity updatedGameEntity = updateMove(game, moveRequest);
        return mapperUtil.mapGameEntityToGameResponse(updatedGameEntity);
    }

    /**
     * This method evaluates score and update existing game with new dto object.
     * @param gameEntity
     * @param moveRequest
     * @return
     */
    private GameEntity updateMove(GameEntity gameEntity, GameMoveRequest moveRequest) {
        evaluatorService.evaluateScore(gameEntity, moveRequest);
        return create(gameEntity);
    }

    /**
     * This method saves/updates an existing game entity with new information.
     * @param gameEntity
     * @return
     */
    @Override
    public GameEntity create(GameEntity gameEntity) {
        logger.debug("Game {} received for Saving/Updating", gameEntity.getUuid());
        return repository.save(gameEntity);
    }

    /**
     * This method return a game with given id.
     * @param gameId
     * @return
     */
    @Override
    public GameResponse getGameById(String gameId) {
        logger.debug("Game {} received for getGameById", gameId);
        GameEntity foundGameEntity = repository.findByUuid(UUID.fromString(gameId));
        ofNullable(foundGameEntity).ifPresentOrElse(game -> evaluatorService.validateGameStatus(game),
                () -> {
                    throw new GameNotFoundException(String.format("Game id [%s] not found", gameId));
                });
        return mapperUtil.mapGameEntityToGameResponse(foundGameEntity);
    }

    /**
     * This method delete all existing games from db.
     */
    @Override
    public void delete() {
        repository.deleteAll();
    }


}
