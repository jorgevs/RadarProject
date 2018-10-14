package com.jvs.libgdx.radar;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.jvs.libgdx.radar.screen.game.GameScreen;

public class RadarGame extends Game {
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        this.setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        // dispose the actual com.mygdx.obstacleavoid.screen object
        getScreen().dispose(); // Important!
    }

}
