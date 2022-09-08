package dev.ugamii.whoareyou.config;

import dev.ugamii.whoareyou.WhoAreYou;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class WhoAreYouConfig {
    public static final String ENABLED_OWN_NAME_KEY = "enabled_own_name";
    public static final String ENABLED_OTHER_PLAYERS_NAME_KEY = "enabled_other_players_name";

    public static boolean enabledOwnName = false;
    public static boolean enabledOtherPlayersName = true;

    public static boolean enabledOwnNameDefault = false;
    public static boolean enabledOtherPlayersNameDefault = true;

    public static boolean defaultedBool(String propertyBool, boolean defaultBool) {
        boolean boolFromConfig = propertyBool != null && propertyBool.toLowerCase().equals("true");
        return propertyBool == null ? defaultBool : boolFromConfig;
    }

    public static void writeTo(Properties properties) {
        properties.setProperty(ENABLED_OWN_NAME_KEY, Boolean.toString(enabledOwnName));
        properties.setProperty(ENABLED_OTHER_PLAYERS_NAME_KEY, Boolean.toString(enabledOtherPlayersName));
    }

    public static void readFrom(Properties properties) {
        enabledOwnName = defaultedBool(properties.getProperty(ENABLED_OWN_NAME_KEY), enabledOwnNameDefault);
        enabledOtherPlayersName = defaultedBool(properties.getProperty(ENABLED_OTHER_PLAYERS_NAME_KEY), enabledOtherPlayersNameDefault);
    }

    public static void save() {
        Properties properties = new Properties();
        writeTo(properties);
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("whoareyou.properties");
        if(!Files.exists(configPath)) {
            try {
                Files.createFile(configPath);
            } catch (IOException e) {
                WhoAreYou.LOGGER.error("Failed to create configuration file!");
                e.printStackTrace();
                return;
            }
        }
        try {
            properties.store(Files.newOutputStream(configPath), "Configuration file for Who are you");
        } catch (IOException e) {
            WhoAreYou.LOGGER.error("Failed to write to configuration file!");
            e.printStackTrace();
        }
    }

    public static void load() {
        Properties properties = new Properties();
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("whoareyou.properties");
        if(!Files.exists(configPath)) {
            try {
                Files.createFile(configPath);
                save();
            } catch (IOException e) {
                WhoAreYou.LOGGER.error("Failed to create configuration file!");
                e.printStackTrace();
                return;
            }
        }
        try {
            properties.load(Files.newInputStream(configPath));
        } catch (IOException e) {
            WhoAreYou.LOGGER.error("Failed to read configuration file!");
            e.printStackTrace();
            return;
        }
        readFrom(properties);
    }
}
