package com.dhanushka.bounce.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.sprites.utils.InteractiveTileObject;

public class Liquid extends InteractiveTileObject {

    public Liquid(World world, TiledMap map, Rectangle bounds) {

        super(world, map, bounds, "water");
    }

    @Override
    public void onHit() {

    }
}
