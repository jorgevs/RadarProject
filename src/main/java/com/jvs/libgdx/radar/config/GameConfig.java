package com.jvs.libgdx.radar.config;

public class GameConfig {

    public static final String GAME_NAME = "RadarGame";

    public static final float WIDTH = 800f; // pixels
    public static final float HEIGHT = 600f; // pixels

    public static final float HUD_WIDTH = 800f; // world units
    public static final float HUD_HEIGHT = 600f; // world units

    public static final float WORLD_WIDTH = WIDTH / 2f; // world units (400.0f)
    public static final float WORLD_HEIGHT = HEIGHT / 1.5f; // world units (400.0f)

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2; // world units (200.0f)
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2; // world units (200.0f)

    public static final float RADAR_RADIUS = 150f;
    public static final float RADAR_LINE_LENGTH = 150f;
    public static final int RADAR_VELOCITY = 2;

    private GameConfig() {}
}
