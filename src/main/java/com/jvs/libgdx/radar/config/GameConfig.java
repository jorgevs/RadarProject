package com.jvs.libgdx.radar.config;

public class GameConfig {

    public static final String GAME_NAME = "RadarGame";

    public static final float WIDTH = 800f; // pixels
    public static final float HEIGHT = 600f; // pixels

    public static final float HUD_WIDTH = 800f; // world units
    public static final float HUD_HEIGHT = 600f; // world units

    public static final float WORLD_WIDTH = WIDTH / 40f; // world units (20.0f)
    public static final float WORLD_HEIGHT = HEIGHT / 30f; // world units (20.0f)

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2; // world units (10.0f)
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2; // world units (10.0f)

    public static final float RADAR_RADIUS = WORLD_HEIGHT / 2;//10f;
    public static final float RADAR_LINE_LENGTH = WORLD_HEIGHT / 2;//10f;
    public static final int RADAR_VELOCITY = 1;
    public static final int RADAR_INCLINATION_VELOCITY = 2;

    private GameConfig() {}
}
