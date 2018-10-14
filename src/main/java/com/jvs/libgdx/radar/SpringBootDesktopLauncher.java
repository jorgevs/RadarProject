package com.jvs.libgdx.radar;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jvs.libgdx.radar.config.GameConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDesktopLauncher implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting RadarGame...");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = GameConfig.GAME_NAME;
        config.useGL30 = true;
        config.width = (int)GameConfig.WIDTH;
        config.height = (int)GameConfig.HEIGHT;
        new LwjglApplication(new RadarGame(), config);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDesktopLauncher.class, args);
    }
}
