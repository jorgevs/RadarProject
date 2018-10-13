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

    private static final float RADAR_RADIUS = 250f;  // world units
    private static final float RADAR_LINE_LENGTH = 250f;  // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private float angle = 0f;

    private boolean increase = true;

    private boolean drawRadar = true;
    private boolean drawLine = true;
    //private boolean drawGrid = true;
    //private boolean drawCircles = true;
    //private boolean drawRectangles = true;
    //private boolean drawPoints = true;

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

        if (drawRadar) drawRadar();
        if (drawLine) drawLine();
        //if (drawGrid) drawGrid();
        //if (drawCircles) drawCircles();
        //if (drawRectangles) drawRectangles();
        //if (drawPoints) drawPoints();

        if(increase) angle+=1.5;
        if(!increase) angle-=1.5;

        if(angle >= 180 || angle <= 0){
            //angle = 0;
            increase = !increase;
        }

    }

    private void drawRadar() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.GREEN);

        // draws the arc lines
        renderer.arc(0, 0, RADAR_RADIUS, 0, 180, 100);
        //renderer.arc(0, 0, RADAR_RADIUS-50, 0, 180, 100);
        renderer.arc(0, 0, RADAR_RADIUS-100, 0, 180, 100);
        //renderer.arc(0, 0, RADAR_RADIUS-150, 0, 180, 100);
        renderer.arc(0, 0, RADAR_RADIUS-200, 0, 180, 100);
        //renderer.arc(0, 0, RADAR_RADIUS-250, 0, 180, 100);
        //renderer.arc(0, 0, RADAR_RADIUS-300, 0, 180, 100);

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
        //renderer.arc(0, 0, RADAR_RADIUS, 0, angle, 100);
        renderer.line(0,0,RADAR_LINE_LENGTH * cos(angle * 0.017453292F),RADAR_LINE_LENGTH * sin(angle * 0.017453292F));
        renderer.end();
    }

    private void drawGrid() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        for (int x = -worldWidth; x < worldWidth; x++) {
            renderer.line(x, -worldHeight, x, worldHeight);
        }

        for (int y = -worldHeight; y < worldHeight; y++) {
            renderer.line(-worldWidth, y, worldWidth, y);
        }

        renderer.setColor(Color.BLUE);
        renderer.line(-worldWidth, 0f, worldWidth, 0f);
        renderer.line(0f, -worldHeight, 0f, worldHeight);
        renderer.end();
    }

    private void drawCircles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GREEN);
        renderer.circle(2, 2, 2, 30);
        renderer.circle(-5, -5, 1);
        renderer.end();
    }

    private void drawRectangles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.WHITE);
        renderer.rect(-8, 4, 4, 2);
        renderer.rect(-11, 3, 1, 5);
        renderer.end();
    }

    private void drawPoints() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.CORAL);
        renderer.point(-5, 0, 0);
        renderer.point(5, -3, 0);
        renderer.point(8, 6, 1);
        renderer.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.x(-10, 0, .25f);
        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.R) drawRadar = !drawRadar;
        //if(keycode == Input.Keys.G) drawGrid = !drawGrid;
        //if(keycode == Input.Keys.C) drawCircles = !drawCircles;
        //if(keycode == Input.Keys.R) drawRectangles = !drawRectangles;
        //if(keycode == Input.Keys.P) drawPoints = !drawPoints;

        return true;
    }
}
