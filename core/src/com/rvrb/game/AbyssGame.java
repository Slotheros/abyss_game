package com.rvrb.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rvrb.game.states.GameStateManager;
import com.rvrb.game.states.MenuState;

/**
 * The Abyss Game class extends the Gdx library's Application adapter class and overrides its
 * create, render, and dispose methods. It plays the game music and creates the game state manager
 * that contains the stack of states. It pushes the menu state to start the game.
 */
public class AbyssGame extends ApplicationAdapter {
	// class variables representing the music, game state manager, and sprite batch
	private Music music;
	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	/**
	 * Creates new instance of the game, starting with the Menu State
	 */
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		music = Gdx.audio.newMusic(Gdx.files.internal("Sara_Afonso_-_Underwater_01.mp3"));
		music.setLooping(true);
		music.setVolume(1f);
		music.play();
		System.out.println("playing music:");
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
	 * Disposes of resources
	 */
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
