package org.example.dto;

import lombok.Data;
import org.example.model.Player;

/**
 * Class representing a skinDTO.
 * This class is responsible for managing the skin attributes outside the application.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Data
public class SkinDTO {

    private int skinId;
    private String name;

    private String type;

    private double price;

    private String color;

    private Player player;
}
