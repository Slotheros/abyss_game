package com.rvrb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * The Pillar class represents a pair of pillars (one top and one bottom) that appear on the
 * screen in multitude. It maintains the pair's position, gap size, and repositioning functions.
 */
public class Pillar {
    // class constants representing the physical properties of the pillars
    private static final int FLUX = 100;
    private static final int PILLAR_GAP = 120;
    private static final int LOWEST_OPENING = 60;
    public static final int PILLAR_WIDTH = 52;
    public static final int MINIMUM_GAP_SIZE = 48;

    // class variables representing the Pillar's attributes
    private int gapPenalty = 4;
    private int level = 0;
    private Texture topPillar, bottomPillar;
    private Vector2 posTopPillar, posBottomPillar;
    private Random rand;
    private Rectangle boundsTop, boundsBot;
    private boolean scored;

    /**
     * Pillar constructor that initializes the top and bottom pillar
     * @param x - the x coordinate of the starting location of the pillars
     */
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

    /**
     * Accessors and mutator for the top and bottom pillars
     */
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

    /**
     * Repositions the pillar once it goes off screen, to be ready as the next pillar
     * @param x - the x coordinate of the new location for the pillar
     */
    public void reposition(float x){
        posTopPillar.set(x, rand.nextInt(FLUX) + PILLAR_GAP + LOWEST_OPENING);
        posBottomPillar.set(x, posTopPillar.y - PILLAR_GAP + (level * gapPenalty) - bottomPillar.getHeight());
        boundsTop.setPosition(posTopPillar.x, posTopPillar.y);
        boundsBot.setPosition(posBottomPillar.x, posBottomPillar.y);
        scored = false;
    }

    /**
     * As long as the gap doesn't reach the minimum gap size, increase the difficulty
     */
    public void levelUp() {
        if (level * gapPenalty < MINIMUM_GAP_SIZE) {
            level += 1;
        }
    }

    /**
     * Checks if the given rectangle intersects with the top or bottom pillar
     * @param subRect - the rectangle to check for intersection
     * @return - the boolean of whether or not the rectangle intersects with a pillar
     */
    public boolean collides(Rectangle subRect){
        return subRect.overlaps(boundsTop) || subRect.overlaps(boundsBot);
    }

    /**
     * Disposes of pillar textures
     */
    public void dispose() {
        topPillar.dispose();
        bottomPillar.dispose();
    }
}
