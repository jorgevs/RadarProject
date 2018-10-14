package com.jvs.libgdx.radar.entity;

import com.jvs.libgdx.radar.config.GameConfig;

public class Radar {
    private float line_origin_x = 0;
    private float line_origin_y = 0;
    private int lineInclination = 0;
    private int lineAngle = 0;
    private float lineLength = GameConfig.RADAR_LINE_LENGTH;

    public float getLineOrigin_x() {
        return line_origin_x;
    }

    public float getLineOrigin_y() {
        return line_origin_y;
    }

    public int getLineInclination() {
        return lineInclination;
    }

    public int getLineAngle() {
        return lineAngle;
    }

    public float getLineLength() {
        return lineLength;
    }

    public void increaseLineAngle() {
        lineAngle += GameConfig.RADAR_VELOCITY;
    }

    public void decreaseLineAngle() {
        lineAngle -= GameConfig.RADAR_VELOCITY;
    }

    public void increaseLineInclination() {
        this.lineInclination++;
    }

    public void decreaseLineInclination() {
        this.lineInclination--;
    }
}
