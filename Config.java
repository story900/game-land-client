package com.gameland.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("gameland-hypermenu.json");

    public boolean showFps = true;
    public boolean showCoordinates = true;
    public boolean showKeystrokes = false;
    public boolean compactHud = false;
    public boolean purpleTheme = true;

    public static Config current = new Config();

    public static void load() {
        try {
            if (Files.exists(FILE)) {
                Config loaded = GSON.fromJson(Files.readString(FILE), Config.class);
                if (loaded != null) current = loaded;
            }
        } catch (Exception ignored) {
            current = new Config();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(current));
        } catch (IOException ignored) {
        }
    }
}
