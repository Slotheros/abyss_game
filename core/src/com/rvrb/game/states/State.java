package com.rvrb.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * The abstract class state is extended by all other state classes. It represents one of the
 * several states that the game can be in, and has classes that update the content in the state,
 * handle the input of the user, render the objects on that screen, and dispose of resources.
 */
public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    /**
     * State constructor creates the game state and cam for it
     * @param gsm - represents the game state manager that contains the stack of states
     */
    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();

    }

    /**
     * Public functions that all states will extend in their class
     */
    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();


}
