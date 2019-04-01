package com.dhanushka.bounce.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.sprites.utils.InteractiveTileObject;

public class BigBallMaker extends InteractiveTileObject {

    public BigBallMaker(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds, "big");
    }

    @Override
    public void onHit() {
        // ball push to big
    }
}
