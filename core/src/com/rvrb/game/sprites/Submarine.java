package com.rvrb.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Submarine {
    private static final int GRAVITY = -200;
    private int surfacing = 1;
    private Vector3 position;
    private Vector3 velocity;
    private Texture submarine;

    public Submarine(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        submarine = new Texture("birdanimation.png");
    }

    public void update(float dt) {

        if (position.y >= 0) {
            velocity.set(0, GRAVITY * surfacing, 0);
        }
        velocity.scl(dt); // scale velocities with dt (frame-rate)
        position.add(0, velocity.y, 0);

        if(position.y < 0) {
            position.y = 0;
        } else if (position.y > Gdx.graphics.getHeight()/4 - submarine.getHeight()) {
            position.y = Gdx.graphics.getHeight()/4 - submarine.getHeight();
        }

        velocity.scl(1/dt);
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
}
