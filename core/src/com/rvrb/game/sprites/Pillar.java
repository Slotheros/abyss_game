package com.rvrb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Pillar {
    private static final int FLUX = 100;
    private static final int PILLAR_GAP = 120;
    private static final int LOWEST_OPENING = 40;

    private Texture topPillar, bottomPillar;
    private Vector2 posTopPillar, posBottomPillar;
    private Random rand;

    public Pillar(float x) {
        topPillar = new Texture("toptube.png");
        bottomPillar = new Texture("bottomtube.png");
        rand = new Random();

        posTopPillar = new Vector2(x, rand.nextInt(FLUX) + PILLAR_GAP + LOWEST_OPENING);
        posBottomPillar = new Vector2(x, posTopPillar.y - PILLAR_GAP - bottomPillar.getHeight());
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
}
