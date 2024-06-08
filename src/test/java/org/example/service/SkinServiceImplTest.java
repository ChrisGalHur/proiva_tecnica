package org.example.service;

import org.example.dto.SkinDTO;
import org.example.exception.CantShowException;
import org.example.model.Player;
import org.example.model.Skin;
import org.example.utils.SkinJsonImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SkinServiceImplTest {

    @Mock
    private SkinJsonImpl skinJsonImpl;
    
    @InjectMocks
    private SkinServiceImpl skinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSkins_shouldReturnSkinDTOList_whenSkinsAreAvailable() throws CantShowException {
        Skin skin1 = new Skin(1, 1, "Dragon Scale Armor", "Armor", 1000.0, "Red", new Player());
        Skin skin2 = new Skin(2, 2, "Elven Bow", "Weapon", 800.0, "Green", new Player());

        List<Skin> skins = Arrays.asList(skin1, skin2);

        when(skinJsonImpl.readSkinsFromJson()).thenReturn(skins);

        when(skinJsonImpl.readSkinsFromJson()).thenReturn(Collections.emptyList());

        // Act
        List<SkinDTO> result = skinService.getSkins();

        // Assert
        assertNotNull(result);
        assertEquals(13, result.size());
        assertEquals("Dragon Scale Armor", result.get(0).getName());
        assertEquals("Elven Bow", result.get(1).getName());

        verify(skinJsonImpl, times(1)).readSkinsFromJson();
    }

    @Test
    void getSkins_shouldThrowCantShowException_whenSkinsAreNotAvailable() {
        // Arrange
        when(skinJsonImpl.readSkinsFromJson()).thenReturn(Collections.emptyList());

        // Act & Assert
        CantShowException exception = assertThrows(CantShowException.class, () -> {
            skinService.getSkins();
        });

        assertEquals(": The skins are not available", exception.getMessage());
        verify(skinJsonImpl, times(1)).readSkinsFromJson();
    }
}