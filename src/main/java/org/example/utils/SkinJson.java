package org.example.utils;

import org.example.model.Skin;

import java.io.IOException;
import java.util.List;

public interface SkinJson {
    public List<Skin> readSkinsFromJson() throws IOException;
}
