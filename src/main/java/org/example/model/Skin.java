package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing a skin in the game.
 * This class is responsible for managing the skin attributes in the application.
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Entity
@Table(name = "skins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skin {

    /**
     * Attributes of the skin.
     */
    @Id
    private int id;

    @Column(name = "skin_id")
    private int skinId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type; //Armor, Weapon, Cloak

    @Column(name = "price")
    private double price;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "player")
    private Player player;

    //region COPY CONSTRUCTOR
    /**
     * Copy constructor of the class to create a new instance of the same type mantaining the initial id of the skin.
     *
     * @param skin The skin to copy.
     */
    public Skin(Skin skin) {//No quiero que se copie el id
        this.skinId = skin.id;
        this.name = skin.name;
        this.type = skin.type;
        this.price = skin.price;
        this.color = skin.color;
    }
    //endregion COPY CONSTRUCTOR
}
