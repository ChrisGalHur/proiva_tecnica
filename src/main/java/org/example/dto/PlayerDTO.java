package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a playerDTO.
 * This class is responsible for managing the player attributes outside the application.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO {

    private String name;

    private int id;
}
