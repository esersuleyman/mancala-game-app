package com.seser.mancalagame.controller;

import com.seser.mancalagame.domain.request.GameMoveRequest;
import com.seser.mancalagame.domain.request.GameStartRequest;
import com.seser.mancalagame.domain.response.GameResponse;
import com.seser.mancalagame.domain.response.GameStartResponse;
import com.seser.mancalagame.service.impl.GameServiceImpl;
import com.seser.mancalagame.utilities.Constants;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.seser.mancalagame.utilities.Constants.*;

/**
 * @author Eser
 * This class is a rest controller and contains endpoints for REST API.
 */
@RestController
@RequestMapping(Constants.GAME_BASE_URL)
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    private GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }

    /**
     * This method is endpoint for game play and returns an MVC by using thymeleaf.
     * @param uuid
     * @return
     */
    @GetMapping(GAME_PLAY_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Game play screen API", response = ModelAndView.class)
    public ModelAndView gamePlay(@RequestParam String uuid ) {
        ModelAndView modelAndView = new ModelAndView(PLAY_VIEW_NAME);
        modelAndView.addObject(GAME_RESPONSE_DATA_KEY, gameService.getGameById(uuid));
        return modelAndView;
    }

    /**
     * This method is endpoint for game start and communicates with service layer.
     * @param gameStartRequest
     * @return
     */
    @PostMapping(GAME_START_URL)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Game start request API", response = GameStartResponse.class)
    public GameStartResponse start(@Valid @RequestBody GameStartRequest gameStartRequest) {
        logger.debug("GameStartRequest received : {}", gameStartRequest);
        return gameService.start(gameStartRequest.getFirstPlayer(), gameStartRequest.getSecondPlayer());
    }

    /**
     * This method is endpoint for game move update and communicates with service layer.
     * @param moveRequest
     * @return
     */
    @PutMapping(GAME_MOVE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Game update move API", response = GameResponse.class)
    public GameResponse updateMove(@Valid @RequestBody GameMoveRequest moveRequest) {
        logger.debug("GameMoveRequest received : {}", moveRequest);
        return gameService.updateMove(moveRequest);
    }

    /**
     * This method is endpoint for returning a game and communicates with service layer.
     * @param uuid
     * @return
     */
    @GetMapping(GAME_GET_URL)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get game by id API", response = GameResponse.class)
    public GameResponse getGameById(@PathVariable("uuid") String uuid) {
        logger.debug("Game id received : {}", uuid);
        return gameService.getGameById(uuid);
    }

    /**
     * This method is endpoint for deleting all games and communicates with service layer.
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete all games")
    public void delete() {
        gameService.delete();
    }
}
