package com.seser.mancalagame.utilities;


import com.seser.mancalagame.domain.entities.GameEntity;
import com.seser.mancalagame.domain.entities.PlayerEntity;
import com.seser.mancalagame.domain.enums.PlayerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

/**
 * @author Eser
 * This class contains methods for each operations on game.
 */
public class GameHelper {

    private static final Logger logger = LoggerFactory.getLogger(GameHelper.class);

    /**
     * This method returns initial board which contains 6 stones for each pit and 0 stone for score pits.
     * @return
     */
    public int[] getInitialBoard() {
        logger.info("Initializing board for game");
        int[] board = new int[Constants.TOTAL_BOARD_PITS];
        IntStream.range(0, Constants.TOTAL_BOARD_PITS)
                .filter(index -> (index != Constants.FIRST_PLAYER_SCORE_PIT && index != Constants.SECOND_PLAYER_SCORE_PIT))
                .forEach(index -> board[index] = Constants.INIT_PIT_VALUE);
        return board;
    }

    /**
     * This method returns score pit with given player.
     * @param playerType
     * @return
     */
    public int getLargePitByPlayerType(PlayerType playerType) {
        logger.debug("Player score pit for player type {}", playerType);
        return switch (playerType) {
            case FIRST -> Constants.FIRST_PLAYER_SCORE_PIT;
            case SECOND -> Constants.SECOND_PLAYER_SCORE_PIT;
        };
    }

    /**
     * This method returns max pit index with given player.
     * @param type
     * @return
     */
    public int maxIndexByPlayerType(PlayerType type) {
        return Constants.PITS_PER_ROW + (type.ordinal() * Constants.PITS_PER_ROW) - 2;
    }

    /**
     * This method returns min pit index with given player.
     * @param type
     * @return
     */
    public int minIndexByPlayerType(PlayerType type) {
        return type.ordinal() * Constants.PITS_PER_ROW;
    }

    /**
     * This method returns opponent pit index with given pit index.
     * @param currentIndex
     * @return
     */
    public int getOpponentPitIndex(int currentIndex) {
        return Constants.TOTAL_BOARD_PITS - currentIndex - 2;
    }

    /**
     * This method capture opponent pit stones with given pit index.
     * @param nextIndex
     * @param board
     * @return
     */
    public int captureOpponentStones(int nextIndex, int[] board) {
        int oppositeIndex = getOpponentPitIndex(nextIndex);
        int scoreToAd = ++board[oppositeIndex];
        board[nextIndex] = 0;
        board[oppositeIndex] = 0;
        return scoreToAd;
    }

    /**
     * This method returns opponent player with given player.
     * @param currentPlayer
     * @return
     */
    public PlayerType getOpponentByType(PlayerType currentPlayer) {
        logger.debug("Request opponent against player type {}", currentPlayer);
        return switch (currentPlayer) {
            case FIRST -> PlayerType.SECOND;
            case SECOND -> PlayerType.FIRST;
        };
    }

    /**
     * This method returns player from a game with given player.
     * @param gameEntity
     * @param playerType
     * @return
     */
    public PlayerEntity getPlayerFromGameEntityByType(GameEntity gameEntity, PlayerType playerType) {
        logger.debug("Request opponent against player type {} from game entity", playerType);
        return switch (playerType) {
            case FIRST -> gameEntity.getFirstPlayer();
            case SECOND -> gameEntity.getSecondPlayer();
        };
    }

    /**
     * This method collects stones and return sum of stones.
     * @param playerType
     * @param board
     * @return
     */
    public int collectStones(PlayerType playerType, int[] board) {
        logger.debug("Collect stones against player type : {}", playerType);
        return IntStream
                .rangeClosed(minIndexByPlayerType(playerType), maxIndexByPlayerType(playerType))
                .map(i -> board[i]).sum();
    }

    /**
     * This method fills all pits on the board with given player and value.
     * @param playerType
     * @param board
     * @param number
     */
    public void fillBoard(PlayerType playerType, int[] board, int number) {
        logger.debug("Fill board for player type {} with value {}", playerType, number);
        IntStream.rangeClosed(minIndexByPlayerType(playerType), maxIndexByPlayerType(playerType))
                .forEach(i -> board[i] = number);
    }

    /**
     * This method checks if there are any empty board for each player.
     * @param board
     * @return
     */
    public boolean anyEmptyBoard(int[] board) {
        return checkPlayerBoardEmptyByType(PlayerType.FIRST, board) || checkPlayerBoardEmptyByType(PlayerType.SECOND, board);
    }

    /**
     * This method checks board is empty or not with given player.
     * @param playerType
     * @param board
     * @return
     */
    private boolean checkPlayerBoardEmptyByType(PlayerType playerType, int[] board) {
        logger.debug("Check board empty against player type {}", playerType);
        return IntStream
                .rangeClosed(minIndexByPlayerType(playerType), maxIndexByPlayerType(playerType))
                .noneMatch(index -> board[index] != 0);
    }

    /**
     * Initialization on demand pattern
     * this pattern is alternative of double check locking pattern
     * which not even support lazy loading but also safe to use in
     * multi-processor distributed instances
     */

    private static class InstanceHolder {
        private static final GameHelper INSTANCE = new GameHelper();

        private InstanceHolder() {

        }
    }


    public static GameHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private GameHelper() {

    }
}
