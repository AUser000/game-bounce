package com.dhanushka.bounce.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.dhanushka.bounce.Bounce;
import com.dhanushka.bounce.sprites.BigBallMaker;
import com.dhanushka.bounce.sprites.Bombs;
import com.dhanushka.bounce.sprites.Boundary;
import com.dhanushka.bounce.sprites.NxtLvl;
import com.dhanushka.bounce.sprites.SmallBallMaker;

public class WorldCreator {
    public WorldCreator(World world, TiledMap map) {
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        // boundaries
        Gdx.app.log("World creating", "boundaries");
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(PolylineMapObject.class)) {
            Shape shapeforplyline;
            if(object instanceof  PolylineMapObject) {
                shapeforplyline = createPolyLine((PolylineMapObject) object);
                Gdx.app.log("World creating", "working");
            } else {
                Gdx.app.log("World creating", "faild");
                continue;
            }

            bodyDef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bodyDef);
            body.createFixture(shape, 1.0f);
            //shapeforplyline.dispose();
        }

        // boundaries
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(MapObject.class)) {
            if(object instanceof  PolylineMapObject) {
                continue;
            }
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            System.out.println("+");
            new Boundary(world, map, rectangle);
        }
//        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
//            System.out.println("+");
//            bodyDef.type = BodyDef.BodyType.StaticBody;
//            bodyDef.position.set((rectangle.getX() + rectangle.getWidth()/2)/ Bounce.PPM, (rectangle.getY() + rectangle.getHeight()/2)/Bounce.PPM);
//
//            body = world.createBody(bodyDef);
//            shape.setAsBox(rectangle.getWidth()/2/Bounce.PPM, rectangle.getHeight()/2/Bounce.PPM);
//            fixtureDef.shape = shape;
//            body.createFixture(fixtureDef);
//        }

        // smalls
        // small
        for(MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            new SmallBallMaker(world, map, rectangle);
        }

        // big
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            new BigBallMaker(world, map, rectangle);
        }

        // bombs
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            System.out.println("+");
            new Bombs(world, map, rectangle);
        }

        // lives
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            System.out.println("+");
            new Bombs(world, map, rectangle);
        }

        // boundaries
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            System.out.println("+");
            new NxtLvl(world, map, rectangle);
        }

    }

    private static ChainShape createPolyLine(PolylineMapObject polylineMapObject) {
        float[] vertices = polylineMapObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for (int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i*2]/ Bounce.PPM, vertices[i*2 + 1]/ Bounce.PPM);
            Gdx.app.log("Cahinshape creating", "hhh ");
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }

}
