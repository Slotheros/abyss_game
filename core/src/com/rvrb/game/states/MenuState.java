package com.rvrb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rvrb.game.AbyssGame;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        // if the user presses the screen
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); // checks for user input
    }

    @Override
    /**
     * Opens the SpriteBatch box
     */
    public void render(SpriteBatch sb) {
        sb.begin(); // opens the box
        //sb.draw(background, 0, 0, AbyssGame.WIDTH, AbyssGame.HEIGHT); // draw background
        //sb.draw(playButton, (AbyssGame.WIDTH / 2) - (playButton.getWidth() / 2), AbyssGame.HEIGHT / 2); // draw play button
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // draw background
        sb.draw(playButton, (Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2), Gdx.graphics.getHeight() / 2); // draw play button
        sb.end(); // close the box
    }

    @Override
    /**
     * Disposes resources when transitioning states
     */
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
