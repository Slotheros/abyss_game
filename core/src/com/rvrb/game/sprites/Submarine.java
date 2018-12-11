package com.rvrb.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Submarine class represents a submarine object, which the user plays as. It contains all of
 * the properties for the submarine and manages it's speed.
 */
public class Submarine {
    // class constants representing physics values for the world
    private static final int GRAVITY = -250;
    private static final int MOVEMENT = 100;
    private static final int LEVEL_SCL = 10;
    private static final int GROUND_POS = 46;

    // class variables representing the submarine's attributes
    private int surfacing = 1;
    private int level = 0;
    private Vector3 position;
    private Vector3 velocity;
    private Texture submarine;
    private Rectangle boundsSub;

    /**
     * Submarine constructor that initializes the submarine, it's bounds, position, and velocity
     * @param x - the x coordinate of the submarine's starting location
     * @param y - the y coordinate of the submarine's starting location
     */
    public Submarine(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        submarine = new Texture("submarine.png");
        boundsSub = new Rectangle(x+5, y, submarine.getWidth()-5, submarine.getHeight());
    }

    /**
     * Accessors for private variables
     */
    public Rectangle getBounds(){
        return boundsSub;
    }
    public Vector3 getPosition() {
        return position;
    }
    public Texture getTexture() {
        return submarine;
    }

    /**
     * Changes the position of the submarine, base on it's velocity and change in time
     */
    public void update(float dt) {
        if (position.y >= 0) {
            velocity.set(0, GRAVITY * surfacing, 0);
        }
        velocity.scl(dt); // scale velocities with dt (frame-rate)
        position.add((MOVEMENT + (level * LEVEL_SCL)) * dt, velocity.y, 0);

        if(position.y < GROUND_POS) {
            position.y = GROUND_POS;
        } else if (position.y > Gdx.graphics.getHeight()/4 - submarine.getHeight()) {
            position.y = Gdx.graphics.getHeight()/4 - submarine.getHeight();
        }

        velocity.scl(1/dt);
        boundsSub.setPosition(position.x, position.y);
    }

    /**
     * Sets the surfacing variable to change the velocity to upward
     */
    public void surface() {
        surfacing = -1;
    }

    /**
     * Sets the surfacing variable to change the velocity to downward
     */
    public void sink() {
        surfacing = 1;
    }

    /**
     * Sets the surfacing variable to change the velocity to zero
     */
    public void holdSteady(){
        surfacing = 0;
    }

    /**
     * This is called in the playstate whenever the difficulty is intended to increase
     */
    public void levelUp() {
        level += 1;
    }

    /**
     * The submarine follows the input of the user, sinking or surfacing based on
     * the y position of their finger.
     * @param yTouchPos
     */
    public void followTouch(int yTouchPos){
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

    /**
     * Disposes of the submarine resource
     */
    public void dispose() {
        submarine.dispose();
    }
}
