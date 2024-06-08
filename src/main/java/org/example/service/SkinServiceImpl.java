package org.example.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.SkinDTO;
import org.example.exception.CantShowException;
import org.example.exception.InvalidPurchaseException;
import org.example.model.Player;
import org.example.utils.SkinJsonImpl;
import org.example.model.Skin;
import org.example.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Provides service methods for the skin entity.
 * This class implements the methods from the SkinService interface.
 *
 * @see SkinService
 * @version 1.0
 * @author ChrisGalHur
 */
@Slf4j
@Service
public class SkinServiceImpl implements SkinService{

    //region INJECTIONS
    private final SkinRepository skinRepository;
    private final PlayerService playerService;
    private final SkinJsonImpl skinJson;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param skinRepository The skin repository.
     * @param playerService The player service.
     */
    @Autowired
    public SkinServiceImpl(SkinRepository skinRepository, PlayerService playerService, SkinJsonImpl skinJson) {
        this.skinRepository = skinRepository;
        this.playerService = playerService;
        this.skinJson = skinJson;
    }
    //endregion INJECTIONS

    //region GET SKINS

    /**
     * Get all skins.
     * This method reads the skins from the JSON file and returns the list of skinsDTO.
     * It checks if the list of skins is not available and throws an exception.
     *
     * @return {@code List<SkinDTO>} The list of skins.
     * @throws CantShowException If the list of skins is not available.
     * @see SkinJsonImpl
     */
    @Override
    public List<SkinDTO> getSkins() throws CantShowException {
        List<Skin> skins = skinJson.readSkinsFromJson();
        if (Objects.isNull(skins) || skins.isEmpty()) {
            throw new CantShowException(": The skins are not available");
        }
        //Change skin list to list of DTO
        List<SkinDTO> skinsDTO = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (Skin skin : skins) {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            skinsDTO.add(objectMapper.convertValue(skin, SkinDTO.class));
        }

        return skinsDTO;
    }
    //endregion GET SKINS

    //region BUY SKIN
    /**
     * Buy a skin.
     * This method buys a skin for a player validating the skin and checking:
     * - If the skin is null.
     * - If the list of skins to purchase is not available.
     * - If the skin of to buy is not available.
     * - If the player is already registered.
     * - If the player already has the skin.
     * If the skin is valid, it creates a new skin and assigns it to the player in the database.
     * @param skin The skin containing the skin id.
     * @throws InvalidPurchaseException To handle the invalid purchase exception.
     * @throws IOException To handle the exception when reading the JSON file.
     */
    @Override
    public void buySkin(Skin skin) throws InvalidPurchaseException, IOException {
        if (skin == null) { // Revisamos si el skin es nulo o si el id es 0
            throw new InvalidPurchaseException(": The skin is invalid");
        }

        List<Skin> skins = skinJson.readSkinsFromJson();

        if (skins == null) { // Revisamos si la lista de skins es nula
            throw new InvalidPurchaseException(": The skins are not available");
        }

        Skin skinToBuy = skins.stream().filter(s -> s.getId() == skin.getId()).findFirst().orElse(null);
        if (skinToBuy == null) { // Revisamos si el skin a comprar es nulo
            throw new InvalidPurchaseException(": The skin is not available");
        }

        Player playerToAssign = playerService.findPlayerByName(skin.getName());
        if(playerToAssign == null) { // Revisamos si el jugador es nulo
            throw new InvalidPurchaseException(": The player is not available to buy the skin");
        }


        if(playerToAssign.getSkins().stream().anyMatch(s -> s.getSkinId() == skinToBuy.getId())) { // Revisamos si el jugador ya tiene el skin
            throw new InvalidPurchaseException(": The player already has the skin");
        }

        Skin newSkin = new Skin(skinToBuy); // He utilizado un constructor de copia para mejorar la legibilidad y mantener el id inicial
        playerToAssign.getSkins().add(newSkin);
        newSkin.setPlayer(playerToAssign);
        log.info("Player: " + newSkin.getPlayer().getName() + " bought skin: " + newSkin.getName());
        skinRepository.save(newSkin);
    }
    //endregion BUY SKIN
}
