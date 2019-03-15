package com.dhanushka.bounce.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.sprites.utils.States;

public class Ball {

    private World world;
    TextureRegion ballpng;
    public Body ball;
    boolean notBounced = false;
    float previouse;
    States stateY;
    States stateX;
    boolean canBounce;
    TiledMap map;
    boolean small;
    //Array<Body> bodies = new Array<Body>(world.getBodyCount());

    public Ball(World world) {

    }

    private void defineBall() {

    }

    public void handleInputs(float dt) {

    }

    public void update(float dt) {

    }

    public static void out() {

    }

    public void changeBall() {

    }

}
