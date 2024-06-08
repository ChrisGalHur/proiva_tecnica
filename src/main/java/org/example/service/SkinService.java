package org.example.service;

import org.example.dto.SkinDTO;
import org.example.model.Skin;

import java.io.IOException;
import java.util.List;

/**
 * Provides service methods for the skin entity.
 * This interface provides methods:
 * <ul>
 *     <li>Get all skins.</li>
 *     <li>Buy a skin.</li>
 * </ul>
 *
 * @version 1.0
 * @author ChrisGalHur
 */
public interface SkinService {

    public List<SkinDTO> getSkins() throws IOException;
    void buySkin(Skin skin) throws IOException;
}
