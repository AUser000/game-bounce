package com.dhanushka.bounce.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.sprites.utils.InteractiveTileObject;

public class SmallBallMaker extends InteractiveTileObject {

    public SmallBallMaker(World world, TiledMap map, Rectangle bounds) {

        super(world, map, bounds, "small");
    }

    @Override
    public void onHit() {

    }
}
