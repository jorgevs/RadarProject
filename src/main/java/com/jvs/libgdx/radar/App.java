package com.jvs.libgdx.radar;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jvs.libgdx.radar.core.RadarRenderer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Radar";
        config.useGL30 = true;
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new RadarRenderer(), config);
    }
}
