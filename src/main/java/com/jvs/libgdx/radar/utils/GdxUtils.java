package com.jvs.libgdx.radar.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class GdxUtils {

    private GdxUtils() {
    }

    public static void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public static void clearScreen() {
        clearScreen(Color.BLACK);
        //clearScreen(new Color(0.5f, 0, 0, 1.0f));
    }

}
