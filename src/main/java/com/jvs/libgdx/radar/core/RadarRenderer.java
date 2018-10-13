package com.jvs.libgdx.radar.core;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jvs.libgdx.radar.common.SampleBase;
import com.jvs.libgdx.radar.utils.GdxUtils;

import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;

public class RadarRenderer extends SampleBase {
    private static final Logger LOGGER = new Logger(RadarRenderer.class.getName(), Logger.DEBUG);

    private static final float WORLD_WIDTH = 800f;   // world units
    private static final float WORLD_HEIGHT = 600f;  // world units

    private static final float RADAR_RADIUS = 250f;
    private static final float RADAR_LINE_LENGTH = 250f;
    private static final int RADAR_VELOCITY = 3;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private int angle = 0;
    private float radarInclination = 0f;

    private boolean increase = true;

    private boolean drawRadar = true;
    private boolean drawLine = true;
    private boolean drawGrid = true;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        // NOTE:  Not centering camera for this sample
        viewport.update(width, height);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();
        renderer.setProjectionMatrix(camera.combined);

        if (drawGrid) drawGrid();
        if (drawLine) drawLine();
        if (drawRadar) drawRadar();

        adjustDirectionAndLimits();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            radarInclination--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            radarInclination++;
        }
    }

    private void adjustDirectionAndLimits(){
        // Adjusts line's direction
        if(increase) angle+=RADAR_VELOCITY;
        if(!increase) angle-=RADAR_VELOCITY;

        // Adjusts the line's limits
        LOGGER.debug("radarInclination: " + radarInclination);
        LOGGER.debug("angle: " + angle);
        if(angle >= 180+radarInclination || angle <= 0+radarInclination){
            increase = !increase;
        }
    }
    private void drawRadar() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.GREEN);

        // draws the arc lines
        renderer.arc(0, 0, RADAR_RADIUS, radarInclination, 180, 100);
        renderer.arc(0, 0, RADAR_RADIUS-100, radarInclination, 180, 100);
        renderer.arc(0, 0, RADAR_RADIUS-200, radarInclination, 180, 100);

        // draws the angle lines
        renderer.setColor(Color.BLUE);
        renderer.line(-RADAR_LINE_LENGTH,0,RADAR_LINE_LENGTH,0);
        renderer.line(0,0,RADAR_LINE_LENGTH*cos(30 * 0.017453292F),RADAR_LINE_LENGTH*sin(30*0.017453292F));
        renderer.line(0,0,RADAR_LINE_LENGTH*cos(60 * 0.017453292F),RADAR_LINE_LENGTH*sin(60*0.017453292F));
        renderer.line(0,0,RADAR_LINE_LENGTH*cos(90 * 0.017453292F),RADAR_LINE_LENGTH*sin(90*0.017453292F));
        renderer.line(0,0,RADAR_LINE_LENGTH*cos(120 * 0.017453292F),RADAR_LINE_LENGTH*sin(120*0.017453292F));
        renderer.line(0,0,RADAR_LINE_LENGTH*cos(150 * 0.017453292F),RADAR_LINE_LENGTH*sin(150*0.017453292F));

        renderer.end();
    }

    private void drawLine(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.GREEN);
        //renderer.arc(0, 0, RADAR_RADIUS, 0, (angle+radarInclination), 100);
        renderer.line(0,0,RADAR_LINE_LENGTH * cos(angle * 0.017453292F),RADAR_LINE_LENGTH * sin(angle * 0.017453292F));
        renderer.end();
    }

    private void drawGrid() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        for (int x = -worldWidth; x < worldWidth; x+=100) {
            renderer.line(x, -worldHeight, x, worldHeight);
        }

        for (int y = -worldHeight; y < worldHeight; y+=100) {
            renderer.line(-worldWidth, y, worldWidth, y);
        }

        renderer.setColor(Color.BLUE);
        renderer.line(-worldWidth, 0f, worldWidth, 0f);
        renderer.line(0f, -worldHeight, 0f, worldHeight);
        renderer.end();
    }
    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.R) drawRadar = !drawRadar;
        if(keycode == Input.Keys.L) drawLine = !drawLine;
        if(keycode == Input.Keys.G) drawGrid = !drawGrid;
        return true;
    }
}
