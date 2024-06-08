package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidPlayerException;
import org.example.model.Player;
import org.example.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Provides service methods for the player entity.
 * This class implements the methods from the PlayerService interface.
 *
 * @see PlayerService
 * @version 1.0
 * @author ChrisGalHur
 */
@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService {

    //region INJECTIONS
    private final PlayerRepository playerRepository;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param playerRepository The player repository.
     */
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    //endregion INJECTIONS

    //region REGISTER
    /**
     * Register a player.
     * This method registers a player by name validating the name and checking:
     * - If the name is null or empty.
     * - If the player already exists.
     * If the player is valid, it creates a new player and saves it.
     * Otherwise, it throws an InvalidPlayerException.
     *
     * @param name The name of the player.
     * @throws InvalidPlayerException To handle the invalid player exception.
     */
    @Override
    public void registerPlayer(String name) throws InvalidPlayerException{
        if(name == null || name.isEmpty()) {
            throw new InvalidPlayerException("name cannot be null or empty");
        }else if(playerRepository.existsByName(name)) {
            throw new InvalidPlayerException("player already exists");
        }else{
            Player player = new Player();
            player.setName(name.toLowerCase());
            playerRepository.save(player);
        }
    }
    //endregion REGISTER

    //region FIND PLAYER

    /**
     * Find a player by name.
     * This method calls the findByName method from the player repository to find a player by name.
     * If the player is found, it returns the player.
     * Otherwise, it returns null.
     *
     * @param name The name of the player.
     * @return Player The player found or null.
     */
    @Override
    public Player findPlayerByName(String name) {
        return playerRepository.findByName(name);
    }
    //endregion FIND PLAYER
}
