package com.jvs.libgdx.radar.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Logger;
import com.jvs.libgdx.radar.config.GameConfig;
import com.jvs.libgdx.radar.entity.Radar;

public class GameController extends InputAdapter {
    private static final Logger LOGGER = new Logger(GameController.class.getName(), Logger.DEBUG);

    private Radar radar;
    private boolean increase = true;

    boolean drawRadar = true;
    boolean drawLine = true;
    boolean drawGrid = true;

    public GameController() {
        radar = new Radar();
        init();
    }

    public Radar getRadar() {
        return radar;
    }

    private void init(){
        Gdx.input.setInputProcessor(this);
    }

    public void update(float deltaTime) {
        updateDirectionAndLimits();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            radar.increaseLineInclination();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            radar.decreaseLineInclination();
        }
    }

    private void updateDirectionAndLimits(){
        // Adjusts line's direction
        if(increase)  radar.increaseLineAngle();
        if(!increase) radar.decreaseLineAngle();

        // Adjusts the line's limits
        //LOGGER.debug("radarInclination: " + radar.getLineInclination());
        //LOGGER.debug("angle: " + radar.getLineAngle());
        if(radar.getLineAngle() > (180) || radar.getLineAngle() < (0)){
            increase = !increase;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.R) drawRadar = !drawRadar;
        if(keycode == Input.Keys.L) drawLine = !drawLine;
        if(keycode == Input.Keys.G) drawGrid = !drawGrid;
        return true;
    }
}
