package com.rvrb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rvrb.game.AbyssGame;

/**
 * The MenuState class represents the state of the game at the main menu. It contains a title
 * and play button, that when pressed takes the user to the play state.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playButton;
    private Texture title;

    /**
     * MenuState constructor creates the cam and textures for the menu page
     * @param gsm - the game state manager that contains the stack of states
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // set the camera viewport
        background = new Texture("menu.png");
        playButton = new Texture("playbtn.png");
        title = new Texture("abyss_title.png");
    }

    @Override
    /**
     * Called during update, and deals with the user touching the screen
     */
    public void handleInput() {
        // if the user presses the screen
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    /**
     * Calls all functions that need to change or update values
     */
    public void update(float dt) {
        handleInput(); // checks for user input
    }

    @Override
    /**
     * Opens the SpriteBatch box
     */
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // opens the box
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // draw background
        sb.draw(playButton, (Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2), Gdx.graphics.getHeight() / 2 - title.getHeight()); // draw play button
        sb.draw(title, (Gdx.graphics.getWidth() / 2) - (title.getWidth() / 2), Gdx.graphics.getHeight() / 2 + title.getHeight()); // draw title
        sb.end(); // close the box
    }

    @Override
    /**
     * Disposes resources when transitioning states
     */
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("Menu State Disposed");
    }
}
