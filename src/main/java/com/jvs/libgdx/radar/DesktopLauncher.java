package com.jvs.libgdx.radar;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jvs.libgdx.radar.config.GameConfig;

public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = GameConfig.GAME_NAME;
        config.useGL30 = true;
        config.width = (int)GameConfig.WIDTH;
        config.height = (int)GameConfig.HEIGHT;
        new LwjglApplication(new RadarGame(), config);
    }
}
