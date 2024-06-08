package org.example.service;

import org.example.model.Player;

/**
 * Provides service methods for the player entity.
 * This interface provides methods:
 * <ul>
 *     <li>Register a player.</li>
 *     <li>Find a player by name.</li>
 * </ul>
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public interface PlayerService {

    void registerPlayer(String name);
    Player findPlayerByName(String name);
}
