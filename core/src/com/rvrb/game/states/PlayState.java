package com.rvrb.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.rvrb.game.sprites.Pillar;
import com.rvrb.game.sprites.Submarine;

public class PlayState extends State {
    private static final int PILLAR_SPACING = 200;
    private static final int PILLAR_COUNT = 4;
    private static final int SCORE_PADDING = 10;


    private Submarine submarine;
    private Texture bg;
    private Array<Pillar> pillars;
    private int score;
    private String scoreString;
    private BitmapFont scoreFont;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        submarine = new Submarine(50, 150);
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4); // set the camera viewport
        bg = new Texture("underwater2.png");
        pillars = new Array<Pillar>();
        score = 0;
        scoreString = "Score: 0";
        scoreFont = new BitmapFont();

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

    public void handlePillars() {
        // loop through all created pillars
        for(Pillar pillar : pillars){
            // if a pillar goes off the left side of the screen, reposition it.
            if(cam.position.x - (cam.viewportWidth/2) > pillar.getPosTopPillar().x + pillar.getTopPillar().getWidth()){
                pillar.reposition(pillar.getPosTopPillar().x + ((Pillar.PILLAR_WIDTH + PILLAR_SPACING) * (PILLAR_COUNT-1)));
            }

            // if the submarine collides with a pillar, end the run.
            if(pillar.collides(submarine.getBounds())){
                endGame();
                return;
            }

            // if the submarine makes it through two pillars, score a point
            if(!pillar.getScored() && submarine.getPosition().x > pillar.getPosTopPillar().x + submarine.getBounds().width) {
                score += 1;
                pillar.setScored(true);
            }
        }
    }

    public void endGame() {
        // save score to shared preferences
        //SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Preferences pref = Gdx.app.getPreferences("Abyss");
        if (pref.getInteger(("high score")) < score) {
            pref.putInteger("high score", score);
        }
        pref.putInteger("points", pref.getInteger("points") + score);
        // commit preferences changes
        pref.flush();

        System.out.println("High score is: " + pref.getInteger("high score"));
        System.out.println("Total points are: " + pref.getInteger("points"));

        // set the new game state
        gsm.set(new GameoverState((gsm), score));
    }

    @Override
    public void update(float dt) {
        handleInput();
        submarine.update(dt);
        cam.position.x = submarine.getPosition().x + 80;
        scoreString = "Score: " + score;
        handlePillars();
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
        scoreFont.setUseIntegerPositions(false);
        scoreFont.setColor(255, 255, 255, 1);
        scoreFont.draw(sb, scoreString, cam.position.x - (cam.viewportWidth / 2) + SCORE_PADDING, cam.position.y + (cam.viewportHeight / 2) - SCORE_PADDING);

        sb.end(); // close the box
    }

    @Override
    public void dispose() {
        bg.dispose();
        submarine.dispose();
        for(Pillar pillar : pillars) {
            pillar.dispose();
        }
        System.out.println("Play State Disposed");
    }
}
