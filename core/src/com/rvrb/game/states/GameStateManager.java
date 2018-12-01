package com.rvrb.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states;

    /**
     * Manages the game state as a stack of states
     */
    public GameStateManager() {
        states = new Stack<State>();
    }

    /**
     * Adds a state to the top of the game state stack
     */
    public void push(State state) {
        states.push(state);
    }

    /**
     * Removes the top state off the game state stack
     */
    public void pop() {
        states.pop();
    }

    /**
     * Sets a new state to be added to the top of the game state stack
     */
    public void set(State state) {
        states.pop();
        states.push(state);
    }

    /**
     * Updates the top state in the game state stack
     * @param dt is the change in time between the two renders
     */
    public void update(float dt) {
        states.peek().update(dt);
    }

    /**
     * Renders the sprite batch for the top state in the game state stack
     * @param sb is a sprite batch, which is the container that renders everything to the screen
     */
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
