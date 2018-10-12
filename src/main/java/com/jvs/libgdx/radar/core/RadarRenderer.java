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

    private static final float WORLD_WIDTH = 40f;   // world units
    private static final float WORLD_HEIGHT = 40f;  // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private boolean drawRadar = true;
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
        //if (drawGrid) drawGrid();
        //if (drawCircles) drawCircles();
        //if (drawRectangles) drawRectangles();
        //if (drawPoints) drawPoints();
    }

    private void drawRadar() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.GREEN);

        // draws the arc lines
        renderer.arc(0, 0, 20, 0, 180, 100);
        renderer.arc(0, 0, 15, 0, 180, 100);
        renderer.arc(0, 0, 10, 0, 180, 100);
        renderer.arc(0, 0, 5, 0, 180, 100);

        // draws the angle lines
        renderer.setColor(Color.BLUE);
        renderer.line(-20,0,20,0);
        renderer.line(0,0,20*cos(30 * 0.017453292F),20*sin(30*0.017453292F));
        renderer.line(0,0,20*cos(60 * 0.017453292F),20*sin(60*0.017453292F));
        renderer.line(0,0,20*cos(90 * 0.017453292F),20*sin(90*0.017453292F));
        renderer.line(0,0,20*cos(120 * 0.017453292F),20*sin(120*0.017453292F));
        renderer.line(0,0,20*cos(150 * 0.017453292F),20*sin(150*0.017453292F));
        //renderer.line(0,0,-960*cos(radians(60)),-960*sin(radians(60)));
        //renderer.line(0,0,-960*cos(radians(90)),-960*sin(radians(90)));
        //renderer.line(0,0,-960*cos(radians(120)),-960*sin(radians(120)));
        //renderer.line(0,0,-960*cos(radians(150)),-960*sin(radians(150)));
        //renderer.line(-960*cos(radians(30)),0,960,0);

        renderer.end();
    }

    private void drawLine(){
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.GREEN);


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
