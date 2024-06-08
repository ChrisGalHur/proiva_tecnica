package org.example.repository;

import org.example.model.Player;
import org.example.model.Skin;
import org.springframework.beans.PropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Skin entity.
 * This interface is responsible for managing the Skin entity in the database.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Repository
public interface SkinRepository extends JpaRepository<Skin, String> {

    /**
     * Find a skin by player.
     *
     * @param playerToAssign Player to assign the skin to.
     * @return List of skins assigned to the player.
     */
    List<Skin> findByPlayer(Player playerToAssign);
}
