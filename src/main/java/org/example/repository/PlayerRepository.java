package org.example.repository;

import org.example.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Player entity.
 * This interface is responsible for managing the Player entity in the database.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    /**
     * Check if a player with the given name exists in the database.
     *
     * @param name Name of the player to check.
     * @return True if a player with the given name exists, false otherwise.
     */
    boolean existsByName(String name);

    /**
     * Find a player by name.
     *
     * @param name Name of the player to find.
     * @return Player with the given name.
     */
    Player findByName(String name);
}