package com.rvrb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.rvrb.game.sprites.Pillar;
import com.rvrb.game.sprites.Submarine;

public class PlayState extends State {
    private static final int PILLAR_SPACING = 200;
    private static final int PILLAR_COUNT = 4;

    private Submarine submarine;
    private Texture bg;
    private Array<Pillar> pillars;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        submarine = new Submarine(50, 150);
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4); // set the camera viewport
        bg = new Texture("underwater2.png");
        pillars = new Array<Pillar>();

        for(int p = 1; p <= PILLAR_COUNT; p++){
            pillars.add(new Pillar(p*(PILLAR_SPACING)+Pillar.PILLAR_WIDTH));
        }
    }

    @Override
    protected void handleInput() {
//        if (Gdx.input.isTouched()) {
//            submarine.surface();
//        } else {
//            submarine.sink();
//        }
        if(Gdx.input.isTouched()){
            submarine.followTouch((Gdx.graphics.getHeight() - Gdx.input.getY())/4);
        } else {
            submarine.holdSteady();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        submarine.update(dt);
        cam.position.x = submarine.getPosition().x + 80;

        for(Pillar pillar : pillars){
            if(cam.position.x - (cam.viewportWidth/2) > pillar.getPosTopPillar().x + pillar.getTopPillar().getWidth()){
                pillar.reposition(pillar.getPosTopPillar().x + ((Pillar.PILLAR_WIDTH + PILLAR_SPACING) * (PILLAR_COUNT-1)));
            }

            if(pillar.collides(submarine.getBounds())){
                gsm.set(new PlayState((gsm)));
            }
        }

        cam.update();
    }

    @Override
    /**
     * renders the sprite batch
     */
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin(); // open the box
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), -100);
        sb.draw(submarine.getTexture(), submarine.getPosition().x, submarine.getPosition().y);
        for(Pillar pillar : pillars){
            sb.draw(pillar.getTopPillar(), pillar.getPosTopPillar().x, pillar.getPosTopPillar().y);
            sb.draw(pillar.getBottomPillar(), pillar.getPosBottomPillar().x, pillar.getPosBottomPillar().y);
        }
        sb.end(); // close the box
    }

    @Override
    public void dispose() {

    }
}
