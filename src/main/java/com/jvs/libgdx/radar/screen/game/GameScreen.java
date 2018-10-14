package com.jvs.libgdx.radar.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Logger;
import com.jvs.libgdx.radar.RadarGame;

public class GameScreen extends ScreenAdapter {
    private static final Logger LOGGER = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private final RadarGame radarGame;

    private GameController gameController;
    private GameRenderer gameRenderer;

    public GameScreen(RadarGame radarGame) {
        super();
        this.radarGame = radarGame;
    }

    @Override
    public void show() {
        gameController = new GameController();
        gameRenderer = new GameRenderer(gameController);
    }

    @Override
    public void render(float deltaTime) {
        gameController.update(deltaTime);
        gameRenderer.render(deltaTime);

        /*if(controller.isGameOver()){
            game.setScreen(new MenuScreen(game));
        }*/

    }

    @Override
    public void resize(int width, int height) {
        gameRenderer.resize(width, height);
    }

    @Override
    public void hide() {
        // NOTE: screens don't dispose automatically
        dispose();
    }

    @Override
    public void dispose() {
        //renderer.dispose();
    }

}
