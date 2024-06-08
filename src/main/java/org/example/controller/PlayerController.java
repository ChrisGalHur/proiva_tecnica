package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidPlayerException;
import org.example.model.Player;
import org.example.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling all endpoints to manage player endpoints.
 * This class is responsible for handling the incoming requests for the player.
 * - POST /player/register: Register a player.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@RestController
@Slf4j
@RequestMapping("/player")
public class PlayerController {

    //region INJECTIONS
    private final PlayerServiceImpl playerService;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param playerService The player service.
     */
    @Autowired
    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }
    //endregion INJECTIONS

    //region REGISTER
    /**
     * Method to register a player.
     * This method handles POST requests to register a player.
     * - Register a player.
     * - Return the response.
     *
     * @param player The player to register.
     * @return {@code ResponseEntity<String>} The response.
     * @throws InvalidPlayerException To handle the invalid player exception.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Player player) {
        log.info("endpoint /player/register called");

        try {
            playerService.registerPlayer(player.getName());
            return ResponseEntity.ok("Player registered successfully");
        } catch (InvalidPlayerException e) {
            log.error("Error registering player: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error registering player, " + e.getMessage());
        }
    }
    //endregion REGISTER
}
