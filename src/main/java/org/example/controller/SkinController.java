package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.SkinDTO;
import org.example.exception.CantShowException;
import org.example.exception.InvalidPurchaseException;
import org.example.model.Skin;
import org.example.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for handling all endpoints to manage skin endpoints.
 * This class is responsible for handling the incoming requests for the skin.
 * - GET /skins/available: Returns a list of all skins available to buy.
 * - POST /skins/buy: Buy a skin.
 */
@Controller
@Slf4j
@RequestMapping("/skins")
public class SkinController {

    //region INJECTIONS
    private final SkinService skinService;

    /**
     * Constructor of the class to inject the dependencies.
     *
     * @param skinService The skin service.
     */
    @Autowired
    public SkinController(SkinService skinService) {
        this.skinService = skinService;
    }
    //endregion INJECTIONS

    //region AVAILABLE
    /**
     * Method to get all available skins.
     * This method handles GET requests to get all available skins.
     * - Get all available skins.
     * - Return the response with the list of skins.
     *
     * @return {@code ResponseEntity<List<Skin>>} The response with the list of skins.
     * @throws Exception To handle any exception.
     */
    @GetMapping("/available")
    public ResponseEntity<List<SkinDTO>> avaibleSkins() {
        log.info("endpoint /skins/avaible called");

        try {
            List<SkinDTO> skinsDTO = skinService.getSkins();
            return new ResponseEntity<>(skinsDTO, HttpStatus.OK);
        } catch (CantShowException | IOException e) {
            log.error("Error getting avaible skins: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //endregion AVAILABLE

    //region BUY
    /**
     * Method to buy a skin.
     * This method handles POST requests to buy a skin.
     * - Buy a skin.
     * - Return the response with the result.
     *
     * @param skin The skin to buy. Contains the skin id and the player id.
     * @return {@code ResponseEntity<String>} The response with the result of the purchase.
     * @throws InvalidPurchaseException To handle the invalid purchase exception.
     */
    @PostMapping("/buy")
    public ResponseEntity<String> buySkin(@RequestBody Skin skin) {
        log.info("endpoint /skins/buy called");

        try {
            skinService.buySkin(skin);
            return new ResponseEntity<>("Skin bought", HttpStatus.OK);
        } catch (InvalidPurchaseException | IOException e) {
            log.error("Error buying skin: " + e.getMessage());
            return ResponseEntity.badRequest().body("Error buying skin" + e.getMessage());
        }
    }
    //endregion BUY
}
