package com.rvrb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameoverState extends State {

    private Texture background;
    private Texture playButton;
    private String gameoverString = "GAME OVER";
    private BitmapFont gameoverFont;
    private String scoreString;
    private BitmapFont scoreFont;
    private String hiscoreString;
    private BitmapFont hiscoreFont;

    public GameoverState(GameStateManager gsm, int score) {
        super(gsm);
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // set the camera viewport
        background = new Texture("menu.png");
        playButton = new Texture("playbtn.png");
        scoreString = "Game Score: " + score;
        hiscoreString = "High Score: " + Gdx.app.getPreferences("Abyss").getInteger("high score");
        gameoverFont = new BitmapFont();
        scoreFont = new BitmapFont();
        hiscoreFont = new BitmapFont();
    }

    @Override
    public void handleInput() {
        // if the user presses the screen
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
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
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // opens the box
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // draw background
        sb.draw(playButton, Gdx.graphics.getWidth() * 3 / 4, Gdx.graphics.getHeight() / 2 - (playButton.getHeight() / 2)); // draw play button

        gameoverFont.setUseIntegerPositions(false);
        gameoverFont.setColor(255, 255, 255, 1);
        gameoverFont.getData().setScale(10);
        gameoverFont.draw(sb, gameoverString, 100, cam.position.y + 200);

        scoreFont.setUseIntegerPositions(false);
        scoreFont.setColor(255, 255, 255, 1);
        scoreFont.getData().setScale(8);
        scoreFont.draw(sb, scoreString, 100, cam.position.y);

        hiscoreFont.setUseIntegerPositions(false);
        hiscoreFont.setColor(255, 255, 255, 1);
        hiscoreFont.getData().setScale(8);
        hiscoreFont.draw(sb, hiscoreString, 100, cam.position.y - 200);

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
