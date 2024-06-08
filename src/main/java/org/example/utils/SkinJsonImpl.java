package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ApiConstant;
import org.example.model.Skin;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class to manage skin JSON data.
 * This class provides methods:
 * <ul>
 *     <li>Read skins from JSON.</li>
 * </ul>
 *
 * @version 1.0
 * @author ChrisGalHur
 */
@Service
public class SkinJsonImpl implements SkinJson{

    //region Leer JSON y crear skins

    /**
     * Reads the skins from the JSON file.
     * This method reads the skins from the JSON file and returns them as a list.
     * If the skins are not available, it returns null.
     *
     * @return {@code List<Skin>} The list of skins.
     * @throws IOException To handle the exception.
     */
    public List<Skin> readSkinsFromJson(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Skin> skins = Arrays.asList(objectMapper.readValue(new File(ApiConstant.JSON_FILE_PATH), Skin[].class));
            return skins;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
