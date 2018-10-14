package com.jvs.libgdx.radar.screen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jvs.libgdx.radar.config.GameConfig;
import com.jvs.libgdx.radar.utils.GdxUtils;

import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;

public class GameRenderer implements Disposable {
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;

    private final GameController gameController;

    public GameRenderer(GameController gameController) {
        this.gameController = gameController;
        init();
    }

    private void init() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        shapeRenderer = new ShapeRenderer();
    }

    public void render(float deltaTime) {
        GdxUtils.clearScreen();
        shapeRenderer.setProjectionMatrix(camera.combined);

        if (gameController.drawGrid) drawGrid();
        if (gameController.drawLine) drawLine();
        if (gameController.drawRadar) drawRadar();
    }

    public void resize(int width, int height) {
        // NOTE:  Not centering camera for this sample
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }


    private void drawRadar() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);

        // draws the arc lines
        shapeRenderer.arc(gameController.getRadar().getLineOrigin_x(), gameController.getRadar().getLineOrigin_y(), GameConfig.RADAR_RADIUS, gameController.getRadar().getLineInclination(), 180, 100);
        shapeRenderer.arc(gameController.getRadar().getLineOrigin_x(), gameController.getRadar().getLineOrigin_y(), GameConfig.RADAR_RADIUS-50, gameController.getRadar().getLineInclination(), 180, 100);
        shapeRenderer.arc(gameController.getRadar().getLineOrigin_x(), gameController.getRadar().getLineOrigin_y(), GameConfig.RADAR_RADIUS-100, gameController.getRadar().getLineInclination(), 180, 100);

        // draws the angle lines
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.line(-GameConfig.RADAR_LINE_LENGTH,0,GameConfig.RADAR_LINE_LENGTH,0);
        shapeRenderer.line(gameController.getRadar().getLineOrigin_x(),gameController.getRadar().getLineOrigin_y(),GameConfig.RADAR_LINE_LENGTH*cos(30 * 0.017453292F),GameConfig.RADAR_LINE_LENGTH*sin(30*0.017453292F));
        shapeRenderer.line(gameController.getRadar().getLineOrigin_x(),gameController.getRadar().getLineOrigin_y(),GameConfig.RADAR_LINE_LENGTH*cos(60 * 0.017453292F),GameConfig.RADAR_LINE_LENGTH*sin(60*0.017453292F));
        shapeRenderer.line(gameController.getRadar().getLineOrigin_x(),gameController.getRadar().getLineOrigin_y(),GameConfig.RADAR_LINE_LENGTH*cos(90 * 0.017453292F),GameConfig.RADAR_LINE_LENGTH*sin(90*0.017453292F));
        shapeRenderer.line(gameController.getRadar().getLineOrigin_x(),gameController.getRadar().getLineOrigin_y(),GameConfig.RADAR_LINE_LENGTH*cos(120 * 0.017453292F),GameConfig.RADAR_LINE_LENGTH*sin(120*0.017453292F));
        shapeRenderer.line(gameController.getRadar().getLineOrigin_x(),gameController.getRadar().getLineOrigin_y(),GameConfig.RADAR_LINE_LENGTH*cos(150 * 0.017453292F),GameConfig.RADAR_LINE_LENGTH*sin(150*0.017453292F));

        shapeRenderer.end();
    }

    private void drawLine(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.arc(0, 0, gameController.getRadar().getLineLength(), 0, gameController.getRadar().getLineAngle(), 100);
        //shapeRenderer.line(gameController.getRadar().getLineOrigin_x(),gameController.getRadar().getLineOrigin_y(), gameController.getRadar().getLineLength() * cos(gameController.getRadar().getLineAngle() * 0.017453292F),gameController.getRadar().getLineAngle() * sin(gameController.getRadar().getLineAngle() * 0.017453292F));
        shapeRenderer.end();
    }

    private void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        int worldWidth = (int) GameConfig.WORLD_WIDTH;
        int worldHeight = (int) GameConfig.WORLD_HEIGHT;

        for (int x = -worldWidth; x < worldWidth; x+=1) {
            shapeRenderer.line(x, -worldHeight, x, worldHeight);
        }

        for (int y = -worldHeight; y < worldHeight; y+=1) {
            shapeRenderer.line(-worldWidth, y, worldWidth, y);
        }

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.line(-worldWidth, 0f, worldWidth, 0f);
        shapeRenderer.line(0f, -worldHeight, 0f, worldHeight);
        shapeRenderer.end();
    }
}
