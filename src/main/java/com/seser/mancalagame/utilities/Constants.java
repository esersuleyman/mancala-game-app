package com.seser.mancalagame.utilities;

/**
 * @author Eser
 * This class contains constants for using from another classes.
 */
public class Constants {

    //////////////// URL /////////////////////
    public static final String GAME_BASE_URL = "/game";
    public static final String GAME_GET_URL = "/{uuid}";
    public static final String GAME_START_URL = "/start";
    public static final String GAME_PLAY_URL = "/play";
    public static final String GAME_MOVE_URL = "/move";

    /////////////////// CONSTANTS /////////////////////
    public static final String PLAY_VIEW_NAME = "play";
    public static final String GAME_RESPONSE_DATA_KEY = "GAME_RESPONSE_DATA_KEY";
    public static final int TOTAL_BOARD_PITS = 14;
    public static final int FIRST_PLAYER_SCORE_PIT = 6;
    public static final int SECOND_PLAYER_SCORE_PIT = 13;
    public static final int PITS_PER_ROW = 7;
    public static final int INIT_PIT_VALUE = 6;
    public static final String PLAYER_COLLECTION_NAME = "players";
    public static final String GAME_COLLECTION_NAME = "games";
    public static final String RESPONSE_ERROR_MESSAGE_TEMP = "Response error created : {}";

    private Constants() {

    }
}
