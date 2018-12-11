package com.rvrb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The Gameover State represents the state of the game when the user loses. It contains a
 * background and a few texts and images, including a return button that takes the user
 * back to the menu state.
 */
public class GameoverState extends State {

    private Texture background;
    private Texture gameoverTitle;
    private Texture restartIcon;
    private String scoreString;
    private BitmapFont scoreFont;
    private String hiscoreString;
    private BitmapFont hiscoreFont;

    /**
     * Gameover constructor creates the cam and textures for the gameover page
     * @param gsm - the game state manager that contains the stack of states
     * @param score - the game score the user just ended on
     */
    public GameoverState(GameStateManager gsm, int score) {
        super(gsm);
        // set the camera viewport
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // image textures
        background = new Texture("menu.png");
        gameoverTitle = new Texture("gameover_title.png");
        restartIcon = new Texture("return.png");

        // text strings and bitmap fonts
        scoreString = "Game Score: " + score;
        hiscoreString = "High Score: " + Gdx.app.getPreferences("Abyss").getInteger("high score");
        scoreFont = new BitmapFont();
        hiscoreFont = new BitmapFont();
    }

    @Override
    /**
     * handleInput is called during update, and deals with the user touching the screen
     */
    public void handleInput() {
        // if the user presses the screen
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
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
     * Renders the images and texts on the page
     */
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // opens the box

        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // draw background
        sb.draw(restartIcon, Gdx.graphics.getWidth() / 2 - restartIcon.getWidth() / 2, cam.position.y - 500); // draw restart button
        sb.draw(gameoverTitle, Gdx.graphics.getWidth() / 2 - gameoverTitle.getWidth() / 2, cam.position.y + 200); // draw gameover title

        // game score text
        scoreFont.setUseIntegerPositions(false);
        scoreFont.setColor(255, 255, 255, 1);
        scoreFont.getData().setScale(8);
        scoreFont.draw(sb, scoreString, Gdx.graphics.getWidth() / 2 - gameoverTitle.getWidth() / 2, cam.position.y + 100);

        // high score text
        hiscoreFont.setUseIntegerPositions(false);
        hiscoreFont.setColor(255, 255, 255, 1);
        hiscoreFont.getData().setScale(8);
        hiscoreFont.draw(sb, hiscoreString, Gdx.graphics.getWidth() / 2 - gameoverTitle.getWidth() / 2, cam.position.y - 50);

        sb.end(); // close the box
    }

    @Override
    /**
     * Disposes resources when transitioning states
     */
    public void dispose() {
        background.dispose();
        restartIcon.dispose();
        gameoverTitle.dispose();
        System.out.println("Menu State Disposed");
    }
}
