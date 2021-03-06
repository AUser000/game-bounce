package com.dhanushka.bounce.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.sprites.utils.InteractiveTileObject;

public class Bombs extends InteractiveTileObject {

    public Bombs(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds, "bomb");
    }

    @Override
    public void onHit() {
        Gdx.app.log("hit", "out");
    }
}
