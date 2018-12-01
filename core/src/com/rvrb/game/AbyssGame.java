package com.rvrb.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rvrb.game.states.GameStateManager;
import com.rvrb.game.states.MenuState;

public class AbyssGame extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Into the Abyss";

	private GameStateManager gsm;
	private SpriteBatch batch;
	Texture img;

	@Override
	/**
	 * Creates new instance of the game, starting with the Menu State
	 */
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	/**
	 * Clears the screen and then renders the sprite batch
	 */
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	/**
	 *
	 */
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
