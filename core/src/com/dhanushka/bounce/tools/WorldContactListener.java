package com.dhanushka.bounce.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.dhanushka.bounce.sprites.Ball;
import com.dhanushka.bounce.sprites.utils.KeyValue;
import javafx.util.Pair;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getUserData() == "head" || fixtureB.getUserData() == "head") {
            Ball.headHit = true;
        } else if (fixtureA.getUserData() == "small" || fixtureB.getUserData() == "small") {
            Gdx.app.log("ok", "small");
            Ball.change = true;
            Ball.isBig = true;
        } else if (fixtureA.getUserData() == "big" || fixtureB.getUserData() == "big") {
            Gdx.app.log("ok", "big");
            Ball.change = true;
            Ball.isBig = false;
        }
//        else if(fixtureA.getUserData() == "water" || fixtureB.getUserData() == "water"){
//            Gdx.app.log("hello", "world");
//            if (fixtureA.getBody().getUserData() == "water") {
//                Ball.setFixturePair(new KeyValue(fixtureB, fixtureA));
//                Ball.ballIsInWater = true;
//            } else {
//                Ball.setFixturePair(new KeyValue(fixtureA, fixtureB));
//                Ball.ballIsInWater = true;
//            }
//        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getUserData() == "head" || fixtureB.getUserData() == "head") {
            Gdx.app.log("end contact", " end head");
            Ball.headHit = false;
        }
//        else if(fixtureA.getUserData() == "water" || fixtureB.getUserData() == "water"){
//            Gdx.app.log("end contact", "end head");
//            Ball.ballIsInWater = false;
//        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
