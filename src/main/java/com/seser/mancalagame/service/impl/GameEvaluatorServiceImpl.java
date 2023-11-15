package com.seser.mancalagame.service.impl;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.enums.GameStatus;
import com.seser.mancalagame.domain.enums.PlayerType;
import com.seser.mancalagame.domain.exceptions.InvalidGameStateException;
import com.seser.mancalagame.domain.exceptions.InvalidMoveException;
import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.service.GameEvaluatorService;
import com.seser.mancalagame.utilities.Constants;
import com.seser.mancalagame.utilities.GameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Eser
 * This class contains service functions for evaluating current game values.
 */
@Service
public class GameEvaluatorServiceImpl implements GameEvaluatorService {

    private static final Logger logger = LoggerFactory.getLogger(GameEvaluatorServiceImpl.class);

    private final GameHelper gameHelper = GameHelper.getInstance();

    /**
     * This method validates game status is ended or not.
     * @param gameEntity
     * @return
     */
    @Override
    public boolean validateGameStatus(GameEntity gameEntity) {
        logger.debug("Game {} received for validateGameStatus", gameEntity.getUuid());
        if (gameEntity.getStatus().equals(GameStatus.END)) {
            logger.error("Game {} has ended. Please start a new game.", gameEntity.getUuid());
            throw new InvalidGameStateException(
                    String.format("Game [%s] has ended. Please start a new game.", gameEntity.getUuid()));
        }
        return Boolean.TRUE;
    }

    /**
     * This method validates game move is valid or not.
     * @param moveRequest
     * @param board
     * @return
     */
    @Override
    public void validateMove(GameMoveRequest moveRequest, int[] board) {
        logger.debug("Game {} received for validateMove", moveRequest.getUuid());
        int maxIndex = gameHelper.maxIndexByPlayerType(moveRequest.getPlayerTurn());
        int minIndex = gameHelper.minIndexByPlayerType(moveRequest.getPlayerTurn());
        if (moveRequest.getIndex() > maxIndex || moveRequest.getIndex() < minIndex || board[moveRequest.getIndex()] == 0) {
            logger.error("Index {} is not a valid move.", moveRequest.getIndex());
            throw new InvalidMoveException(String.format("Index %d is not a valid move.", moveRequest.getIndex()));
        }
    }

    /**
     * This method validates score for a game with current state and move actions.
     * @param gameEntity
     * @param moveRequest
     */
    @Override
    public void evaluateScore(GameEntity gameEntity, GameMoveRequest moveRequest) {
        logger.debug("Game {} received for evaluating score", moveRequest.getUuid());
        moveRequest.setCurrentStones(gameEntity.getBoard()[moveRequest.getIndex()]);
        gameEntity.getBoard()[moveRequest.getIndex()] = 0;
        iterateStones(gameEntity, moveRequest);
        gameEntity.setTotalTurn(gameEntity.getTotalTurn() + 1);
        if (!moveRequest.isFinalOnLargePit()) {
            gameEntity.setPlayerTurn(gameHelper.getOpponentByType(moveRequest.getPlayerTurn()));
        }
        evaluateEnd(gameEntity);
    }

    /**
     * This method iterate stones to next pits and evaluate pit rule for each change.
     * @param gameEntity
     * @param moveRequest
     */
    private void iterateStones(GameEntity gameEntity, GameMoveRequest moveRequest) {
        logger.debug("Iterate by current pit stones : {}", moveRequest.getCurrentStones());
        while (moveRequest.getCurrentStones() > 0) {
            moveRequest.setIndex(moveRequest.getIndex() + 1);
            Arrays.stream(MANCALA_RULES).forEach(rule ->
                    rule.evaluate(moveRequest, gameEntity));
        }
    }

    /**
     * This method evaluates game will be ended or not.
     * @param gameEntity
     */
    private void evaluateEnd(GameEntity gameEntity) {
        logger.debug("Game {} received for evaluating end game", gameEntity.getUuid());
        if (gameHelper.anyEmptyBoard(gameEntity.getBoard())) {
            gameEntity.getBoard()[Constants.FIRST_PLAYER_SCORE_PIT] += gameHelper.collectStones(PlayerType.FIRST, gameEntity.getBoard());
            gameEntity.getBoard()[Constants.SECOND_PLAYER_SCORE_PIT] += gameHelper.collectStones(PlayerType.SECOND, gameEntity.getBoard());
            gameEntity.setWinnerPlayer(gameEntity.getBoard()[Constants.FIRST_PLAYER_SCORE_PIT] > gameEntity.getBoard()[Constants.SECOND_PLAYER_SCORE_PIT] ?
                    gameHelper.getPlayerFromGameEntityByType(gameEntity, PlayerType.FIRST).getName() : gameHelper.getPlayerFromGameEntityByType(gameEntity, PlayerType.SECOND).getName());
            fillBoard(gameEntity.getBoard());
            gameEntity.setStatus(GameStatus.END);
            logger.info("Game {} successfully finished.", gameEntity.getUuid());
        }
    }

    /**
     * This method fills board with 0 values.
     * @param board
     */
    private void fillBoard(int[] board) {
        for (PlayerType playerType : Arrays.asList(PlayerType.FIRST, PlayerType.SECOND)) {
            gameHelper.fillBoard(playerType, board, 0);
        }
    }
}
