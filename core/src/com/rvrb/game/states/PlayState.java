package com.rvrb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rvrb.game.sprites.Pillar;
import com.rvrb.game.sprites.Submarine;

public class PlayState extends State {

    private Submarine submarine;
    private Texture bg;
    private Pillar pillar;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        submarine = new Submarine(50, 300);
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4); // set the camera viewport
        bg = new Texture("bg.png");
        pillar = new Pillar(100);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            submarine.surface();
        } else {
            submarine.sink();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        submarine.update(dt);
    }

    @Override
    /**
     * renders the sprite batch
     */
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // open the box
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(submarine.getTexture(), submarine.getPosition().x, submarine.getPosition().y);
        sb.draw(pillar.getTopPillar(), pillar.getPosTopPillar().x, pillar.getPosTopPillar().y);
        sb.draw(pillar.getBottomPillar(), pillar.getPosBottomPillar().x, pillar.getPosBottomPillar().y);
        sb.end(); // close the box
    }

    @Override
    public void dispose() {

    }
}
