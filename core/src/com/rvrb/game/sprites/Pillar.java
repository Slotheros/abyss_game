package com.rvrb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Pillar {
    private static final int FLUX = 100;
    private static final int PILLAR_GAP = 120;
    private static final int LOWEST_OPENING = 40;
    public static final int PILLAR_WIDTH = 52;

    private Texture topPillar, bottomPillar;
    private Vector2 posTopPillar, posBottomPillar;
    private Random rand;
    private Rectangle boundsTop, boundsBot;
    private boolean scored;

    public Pillar(float x) {
        topPillar = new Texture("top_pillar.png");
        bottomPillar = new Texture("bot_pillar.png");
        rand = new Random();
        scored = false;

        posTopPillar = new Vector2(x, rand.nextInt(FLUX) + PILLAR_GAP + LOWEST_OPENING);
        posBottomPillar = new Vector2(x, posTopPillar.y - PILLAR_GAP - bottomPillar.getHeight());

        //for collision detection
        boundsTop = new Rectangle(posTopPillar.x+10, posTopPillar.y, topPillar.getWidth()-10, topPillar.getHeight());
        boundsBot = new Rectangle(posBottomPillar.x+10, posBottomPillar.y, bottomPillar.getWidth()-10, bottomPillar.getHeight());
    }

    public Texture getTopPillar() {
        return topPillar;
    }

    public Texture getBottomPillar() {
        return bottomPillar;
    }

    public Vector2 getPosTopPillar() {
        return posTopPillar;
    }

    public Vector2 getPosBottomPillar() {
        return posBottomPillar;
    }

    public Boolean getScored() { return scored; }

    public void setScored(Boolean isScored) { this.scored = isScored; }

    public void reposition(float x){
        posTopPillar.set(x, rand.nextInt(FLUX) + PILLAR_GAP + LOWEST_OPENING);
        posBottomPillar.set(x, posTopPillar.y - PILLAR_GAP - bottomPillar.getHeight());
        boundsTop.setPosition(posTopPillar.x, posTopPillar.y);
        boundsBot.setPosition(posBottomPillar.x, posBottomPillar.y);
        scored = false;
    }

    public boolean collides(Rectangle subRect){
        return subRect.overlaps(boundsTop) || subRect.overlaps(boundsBot);
    }

    public void dispose() {
        topPillar.dispose();
        bottomPillar.dispose();
    }
}
