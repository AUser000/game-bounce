package com.dhanushka.bounce.sprites.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.Bounce;

public abstract class InteractiveTileObject {

    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;
    protected Fixture fixture;

    protected InteractiveTileObject(World world, TiledMap map, Rectangle bounds) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth()/2)/ Bounce.PPM, (bounds.getY() + bounds.getHeight()/2)/Bounce.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth()/2/ Bounce.PPM, bounds.getHeight()/2/Bounce.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
    }

    protected InteractiveTileObject(World world, TiledMap map, Rectangle bounds, String userdata) {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth()/2)/ Bounce.PPM, (bounds.getY() + bounds.getHeight()/2)/Bounce.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox(bounds.getWidth()/2/ Bounce.PPM, bounds.getHeight()/2/Bounce.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(userdata);
    }

    public abstract void onHit();

}
