package com.dhanushka.bounce.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.dhanushka.bounce.sprites.Ball;
import com.dhanushka.bounce.sprites.SmallBall;
import com.dhanushka.bounce.sprites.utils.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getUserData() == "head" || fixtureB.getUserData() == "head") {
            SmallBall.headHit = true;
        } else if (fixtureA.getUserData() == "bomb" || fixtureB.getUserData() == "bomb") {
            Gdx.app.log("ok", "out");
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(fixtureA.getUserData() == "head" || fixtureB.getUserData() == "head") {
            SmallBall.headHit = false;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
