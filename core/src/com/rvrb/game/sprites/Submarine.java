package com.rvrb.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Submarine {
    private static final int GRAVITY = -250;
    private static final int MOVEMENT = 100;
    private int surfacing = 1;
    private Vector3 position;
    private Vector3 velocity;
    private Texture submarine;
    private Rectangle boundsSub;

    public Submarine(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        submarine = new Texture("submarine.png");
        boundsSub = new Rectangle(x+5, y, submarine.getWidth()-5, submarine.getHeight());
    }

    public void update(float dt) {
        if (position.y >= 0) {
            velocity.set(0, GRAVITY * surfacing, 0);
        }
        velocity.scl(dt); // scale velocities with dt (frame-rate)
        position.add(MOVEMENT * dt, velocity.y, 0);

        if(position.y < 0) {
            position.y = 0;
        } else if (position.y > Gdx.graphics.getHeight()/4 - submarine.getHeight()) {
            position.y = Gdx.graphics.getHeight()/4 - submarine.getHeight();
        }

        velocity.scl(1/dt);
        boundsSub.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return submarine;
    }

    public void surface() {
        surfacing = -1;
    }

    public void sink() {
        surfacing = 1;
    }

    public void followTouch(int yTouchPos){
        System.out.println("yTouchPos: " + yTouchPos);
        System.out.println("subPos: " + position.y);
        System.out.println("sub height" + submarine.getHeight());
        if((int)(position.y)+12 < yTouchPos){
            surfacing = -1;
        }
        else if((int)(position.y) > yTouchPos){
            surfacing = 1;
        }
        else{
            surfacing = 0;
        }
    }

    public void holdSteady(){
        surfacing = 0;
    }

    public Rectangle getBounds(){
        return boundsSub;
    }
}
